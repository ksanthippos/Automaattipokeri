package korttipakka;

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


    public Logiikka(Käsi kasi) {
        this.arvonTarkistaja = new ArvonTarkistaja();
        this.maanTarkistaja = new MaanTarkistaja();
        this.kasi = kasi;
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

        // tulokset
        if (pari)
            return "Pari!";
        else if (kaksiParia)
            return "Kaksi paria!";
        else if (kolmoset)
            return "Kolmoset!";
        else if (neloset)
            return "Neloset!";
        else if (taysKasi)
            return "Täyskäsi!";
        else if (suora && !vari)
            return "Suora!";
        else if (vari && !suora)
            return "Väri!";
        else if (vari && suora) {
            if (samatMaat.get(0).getMaa() == Maa.HERTTA)
                return "WAU! Herttavärisuora!";

            return "Värisuora!";
        }
        else if (eiMitaan)
            return "Ei mitään.";


        return "Tapahtui virhe, käden arvoa voitu laskea.";

    }


}
