package korttipakka;

import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Logiikka {

    private ArvonTarkistaja arvonTarkistaja;
    private MaanTarkistaja maanTarkistaja;
    private Käsi kasi;

    private boolean eiMitaan;
    private boolean pari;
    private boolean kaksiParia;
    private boolean kolmoset;
    private boolean neloset;
    private boolean taysKasi;
    private boolean suora;
    private boolean vari;

    private double krediitit;
    private double panos;


    public Logiikka(Käsi kasi) {
        this.arvonTarkistaja = new ArvonTarkistaja();
        this.maanTarkistaja = new MaanTarkistaja();
        this.kasi = kasi;
        this.krediitit = 10.0;
        this.panos = 1.0;
    }

    public void korotaPanosta() {
        if (panos == 5.0)
            panos = 0;

        panos = panos + 1.0;
    }

    public double getPanos() {
        return panos;
    }

    public void setKrediitit(double maara) {
        if (krediitit - maara <= 0)
            krediitit = 0;

        krediitit = krediitit + maara;
    }

    public double getKrediitit() {
        return krediitit;
    }



    public String tarkistaKasi() {

        eiMitaan = false;
        pari = false;
        kaksiParia = false;
        kolmoset = false;
        neloset = false;
        taysKasi = false;
        suora = false;
        vari = false;

        AudioClip voitonMaksu = new AudioClip("file:src/aanet/chipsHandle6.wav");

        // jos kädessä kuningas, niin ässän arvo 14
        for (int i = 0; i < kasi.getKoko(); i++) {
            if (kasi.getKortit().get(i).getArvo() == Arvo.KUNKKU)
                kasi.setAssienArvot(14);
        }

        // samat maat listaan
        Collections.sort(kasi.getKortit(), maanTarkistaja);
        List<Kortti> samojaMaita =  new ArrayList<>();
        for (int i = 1; i < kasi.getKortit().size(); i++) {
            if (maanTarkistaja.compare(kasi.getKortit().get(i), kasi.getKortit().get(i - 1)) == 0) {
                samojaMaita.add(kasi.getKortit().get(i));
                samojaMaita.add(kasi.getKortit().get(i - 1));
            }
        }

        // samat arvot listaan
        Collections.sort(kasi.getKortit(), arvonTarkistaja);
        List<Kortti> samojaArvoja =  new ArrayList<>();
        for (int i = 1; i < kasi.getKortit().size(); i++) {
            if (arvonTarkistaja.compare(kasi.getKortit().get(i), kasi.getKortit().get(i - 1)) == 0) {
                samojaArvoja.add(kasi.getKortit().get(i));
                samojaArvoja.add(kasi.getKortit().get(i - 1));
            }
        }

        // poistetaan molemmista listoista tuplakappaleet, jos on
        List<Kortti> samatArvot = samojaArvoja.stream().distinct().collect(Collectors.toList());
        List<Kortti> samatMaat = samojaMaita.stream().distinct().collect(Collectors.toList());


        // ***** KÄDEN TARKISTAMINEN *****

        // suora
        boolean onSuora = true;
        for (int i = 0; i < kasi.getKortit().size() - 1; i++) {
            if (kasi.getKortit().get(i).annaArvo() - kasi.getKortit().get(i + 1).annaArvo() != -1) {
                onSuora = false;
                break;
            }
        }
        if (onSuora)
            suora = true;

        // parit, kolmoset, neloset, täyskäsi
        else if (samatArvot.size() == 2)
            pari = true;
        else if (samatArvot.size() == 3)
            kolmoset = true;
        else if (samatArvot.size() == 4) {
            if (samatArvot.get(0).annaArvo() == samatArvot.get(2).annaArvo())
                neloset = true;
            else
                kaksiParia = true;
        }
        else if (samatArvot.size() == 5) {
            if (samatArvot.get(0).annaArvo() == samatArvot.get(2).annaArvo() && samatArvot.get(3).annaArvo() == samatArvot.get(4).annaArvo() ||
                    samatArvot.get(0).annaArvo() == samatArvot.get(1).annaArvo() && samatArvot.get(2).annaArvo() == samatArvot.get(4).annaArvo())
                taysKasi = true;
        }
        else
            eiMitaan = true;

        // väri
        int hertat = 0;
        int padat = 0;
        int ruudut = 0;
        int ristit = 0;
        for (int i = 0; i < samatMaat.size(); i++) {
            if (samatMaat.get(i).getMaa() == Maa.HERTTA)
                hertat++;
            else if (samatMaat.get(i).getMaa() == Maa.RISTI)
                ristit++;
            else if (samatMaat.get(i).getMaa() == Maa.RUUTU)
                ruudut++;
            else if (samatMaat.get(i).getMaa() == Maa.PATA)
                padat++;
        }
        if (hertat == 5 || padat == 5 || ristit == 5 || ruudut == 5)
            vari = true;

        else
            eiMitaan = true;


        kasi.setAssienArvot(1);     // resetoidaan ässien arvot oletukseksi

        // tulosten käsittely

        /* voittosummat:

         * pari ei voittoa
         * kaksi paria 1.0
         * kolmoset 2.0
         * neloset 3.0
         * suora 3.0
         * väri 4.0
         * värisuora 8.0
         * herttavärisuora 10.0
         * */

        if (pari) {
            if (krediitit == 0)
                return "Pari.\nPeli loppui!";
            return "Pari.\nEt voittanut mitään.";
        }
        else if (kaksiParia) {
            setKrediitit(1.0 * panos);
            voitonMaksu.play();
            return "Kaksi paria.\nVoitit " + 1.0 * panos + " krediittiä!";
        }
        else if (kolmoset) {
            setKrediitit(2.0 * panos);
            voitonMaksu.play();
            return "Kolmoset.\nVoitit " + 2.0 * panos + " krediittiä!";
        }
        else if (neloset) {
            krediitit += 3.0 * panos;
            voitonMaksu.play();
            return "Neloset.\nVoitit " + 3.0 * panos + " krediittiä!";
        }
        else if (taysKasi) {
            krediitit += 3.0 * panos;
            voitonMaksu.play();
            return "Täyskäsi.\nVoitit " + 3.0 * panos + " krediittiä!";
        }
        else if (suora && !vari) {
            krediitit += 4.0 * panos;
            voitonMaksu.play();
            return "Suora.\nVoitit " + 4.0 * panos + " krediittiä!";
        }
        else if (vari && !suora) {
            krediitit += 5.0 * panos;
            voitonMaksu.play();
            return "Väri.\nVoitit " + 5.0 * panos + " krediittiä!";
        }
        else if (vari && suora) {
            if (samatMaat.get(0).getMaa() == Maa.HERTTA) {
                krediitit += 10.0 * panos;
                voitonMaksu.play();
                return "HERTTAVÄRISUORA!.\nVoitit " + 10.0 * panos + " krediittiä!";
            }
            else {
                krediitit += 8.0 * panos;
                voitonMaksu.play();
                return "Värisuora!.\nVoitit " + 8.0 * panos + " krediittiä!";
            }
        }
        else if (eiMitaan)
            return "Ei mitään.";


        return "";

    }


}
