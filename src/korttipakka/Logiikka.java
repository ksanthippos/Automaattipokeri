package korttipakka;

import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Logiikka {


    private ArvonTarkistaja arvonTarkistaja;
    private MaanTarkistaja maanTarkistaja;
    private Käsi kasi;

    private boolean eiMitaan;
    private boolean pari;
    private boolean kunkkuPari;
    private boolean assaPari;
    private boolean kaksiParia;
    private boolean kolmoset;
    private boolean neloset;
    private boolean taysKasi;
    private boolean suora;
    private boolean vari;

    private double krediitit;
    private double panos;
    private double panoksenYlaraja;
    private int pisteet;


    public Logiikka(Käsi kasi) {
        this.arvonTarkistaja = new ArvonTarkistaja();
        this.maanTarkistaja = new MaanTarkistaja();
        this.kasi = kasi;
        this.krediitit = 10.0;
        this.panos = 1.0;
        this.panoksenYlaraja = 5.0;
        this.pisteet = 0;
    }

    public void korotaPanosta() {
        if (panoksenYlaraja > krediitit)
            panoksenYlaraja = krediitit;

        if (panos == panoksenYlaraja)
            panos = 0;

        panos = panos + 1.0;
    }

    public void setPanos() {
        if (panos > krediitit)
            panos = krediitit;
    }

    public double getPanos() {
        return panos;
    }

    public void setKrediitit(double maara) {
        if (maara < 0 && krediitit + maara < 0)
            krediitit = 0;
        else
            krediitit = krediitit + maara;
    }

    public double getKrediitit() {
        return krediitit;
    }

    public void annaPisteet() {
        pisteet = pisteet + (int) krediitit * (int) panos;
    }

    public void annaPisteet(int maara) {
        pisteet = pisteet + maara;

    }

    public int getPisteet() {
        return pisteet;
    }


    public String tarkistaKasi() {

        eiMitaan = false;
        pari = false;
        kunkkuPari = false;
        assaPari = false;
        kaksiParia = false;
        kolmoset = false;
        neloset = false;
        taysKasi = false;
        suora = false;
        vari = false;

        AudioClip voitonMaksu = new AudioClip(getClass().getResource("/aanet/chipsHandle6.wav").toString());
        AudioClip eiVoittoa = new AudioClip(getClass().getResource("/aanet/cardShove4.wav").toString());
        AudioClip havioHuokaus = new AudioClip(getClass().getResource("/aanet/gameOver.wav").toString());


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

        // käsi vielä varmuuden vuoksi järjestykseen siten, että ässä ekana, jos ei kunkkua kädessä
        boolean tsekkaaAssat = true;

        for (Kortti k: kasi.getKortit()) {
            if (k.getArvo() == Arvo.KUNKKU) {
                tsekkaaAssat = false;
                break;
            }
        }
        if (tsekkaaAssat) {
            // siirretään ässä ekaksi kortiksi
            Collections.swap(kasi.getKortit(), 3, 4);
            Collections.swap(kasi.getKortit(), 2, 3);
            Collections.swap(kasi.getKortit(), 1, 2);
            Collections.swap(kasi.getKortit(), 0, 1);
        }


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

        // pitää lisätä vielä kunkku- ja ässäpari, joista saa pisteet!

        // parit, kolmoset, neloset, täyskäsi
        else if (samatArvot.size() == 2) {
            if (samatArvot.get(0).annaArvo() == 13)
                kunkkuPari = true;
            else if (samatArvot.get(0).annaArvo() == 1 || samatArvot.get(0).annaArvo() == 14)
                assaPari = true;
            else
                pari = true;
        }
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

        // *****************************
        // tulosten käsittely
        // *****************************

        if (pari) {
            if (krediitit == 0) {
                havioHuokaus.play();
                return "Pari.\nPeli loppui!";
            }
            eiVoittoa.play();
            return "Pari.\nEt voittanut mitään.";
        }
        else if (kunkkuPari) {
            setKrediitit(1.0 * panos);
            annaPisteet();
            voitonMaksu.play();
            return "Kunkkupari.\nVoitit " + 1.0 * panos + " krediittiä!";
        }
        else if (assaPari) {
            setKrediitit(1.0 * panos);
            annaPisteet();
            voitonMaksu.play();
            return "Ässäpari.\nVoitit " + 1.0 * panos + " krediittiä!";
        }
        else if (kaksiParia) {
            setKrediitit(2.0 * panos);
            annaPisteet();
            voitonMaksu.play();
            return "Kaksi paria.\nVoitit " + 2.0 * panos + " krediittiä!";
        }
        else if (kolmoset) {
            setKrediitit(3.0 * panos);
            annaPisteet();
            voitonMaksu.play();
            return "Kolmoset.\nVoitit " + 3.0 * panos + " krediittiä!";
        }
        else if (neloset) {
            krediitit += 5.0 * panos;
            annaPisteet();
            voitonMaksu.play();
            return "Neloset.\nVoitit " + 5.0 * panos + " krediittiä!";
        }
        else if (taysKasi) {
            krediitit += 5.0 * panos;
            annaPisteet();
            voitonMaksu.play();
            return "Täyskäsi.\nVoitit " + 5.0 * panos + " krediittiä!";
        }
        else if (suora && !vari) {
            krediitit += 6.0 * panos;
            annaPisteet();
            voitonMaksu.play();
            return "Suora.\nVoitit " + 6.0 * panos + " krediittiä!";
        }
        else if (vari && !suora) {
            krediitit += 7.0 * panos;
            annaPisteet();
            voitonMaksu.play();
            return "Väri.\nVoitit " + 7.0 * panos + " krediittiä!";
        }
        else if (vari && suora) {
            if (samatMaat.get(0).getMaa() == Maa.HERTTA) {
                krediitit += 10.0 * panos;
                annaPisteet(150);
                voitonMaksu.play();
                return "HERTTAVÄRISUORA!.\nVoitit " + 10.0 * panos + " krediittiä!";
            }
            else {
                krediitit += 8.0 * panos;
                annaPisteet(75);
                voitonMaksu.play();
                return "Värisuora!.\nVoitit " + 8.0 * panos + " krediittiä!";
            }
        }
        else if (eiMitaan) {
            if (krediitit == 0) {
                havioHuokaus.play();
                return "Ei mitään.\nPeli loppui!";
            }
            eiVoittoa.play();
            return "Ei mitään.";
        }


        return "";


    }


}
