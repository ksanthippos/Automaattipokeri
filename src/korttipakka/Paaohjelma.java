package korttipakka;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Paaohjelma {

    public static void main(String[] args) {

        // ***** RAY-automaattia imitoiva pokeripeli tekstikäyttölitttymällä *****
        /*
        Puuttuu vielä:
        - pelaajan rahat ja panokset
        - tuplaus

        * */

        Pakka korttipakka = new Pakka();
        Käsi kasi = new Käsi(5);    // käden ylärajana 5
        ArvonTarkistaja arvonTarkistaja = new ArvonTarkistaja();
        MaanTarkistaja maanTarkistaja = new MaanTarkistaja();


        // nostetaan 5 korttia satunnaisesti, kunnes painetaan q
        while (true) {


            boolean eiMitaan = false;
            boolean pari = false;
            boolean kaksiParia = false;
            boolean kolmoset = false;
            boolean neloset = false;
            boolean taysKasi = false;
            boolean suora = false;
            boolean vari = false;

            System.out.println("Pakassa kortit:");
            korttipakka.getKortit().stream().forEach(kortti -> System.out.println(kortti));


            System.out.println("Jaa käsi painamalla nappia, komennolla q lopettaa:");
            Scanner lukija = new Scanner(System.in);
            String input = lukija.nextLine();

            if (input.equals("q")) {
                System.out.println("Kiitos hei!");
                break;
            }

            // korttien jakaminen
            korttipakka.sekoitaPakka();
            while (kasi.getKoko() < kasi.getYlaRaja()) {
                kasi.nostaKortti(korttipakka.jaaKortti());
            }

            // ässän arvo 14, jos kädessä kuningas
            for (int i = 0; i < kasi.getKoko(); i++) {
                if (kasi.getKortit().get(i).getArvo() == Arvo.KUNKKU)
                    kasi.setAssienArvot(14);
            }

            System.out.println("Kädessä kortit:");
            System.out.println(kasi);
            System.out.println("Mitkä kortit vaihdetaan? Valitse numeroista 0 - 4, ylin kortti listassa = 0, alin = 4.");
            System.out.println("Jos et halua vaihtaa, niin valitse -1.");

            while (true) {
                System.out.println("Anna komento:");
                int valinta = Integer.valueOf(lukija.nextLine());

                if (valinta == -1)
                    break;
                kasi.getKortit().remove(valinta);
                kasi.nostaKortti(korttipakka.jaaKortti());

                System.out.println("Vaihdon jälkeen kädessä kortit:");
                System.out.println(kasi);

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


            // tulokset
            System.out.println("Kädestä löytyy: ");
            if (pari)
                System.out.println("Pari!");
            else if (kaksiParia)
                System.out.println("Kaksi paria!");
            else if (kolmoset)
                System.out.println("Kolmoset!");
            else if (neloset)
                System.out.println("Neloset!");
            else if (taysKasi)
                System.out.println("Täyskäsi!");
            else if (suora && !vari)
                System.out.println("Suora!");
            else if (vari && !suora)
                System.out.println("Väri!");
            else if (vari && suora) {
                if (samatMaat.get(0).getMaa() == Maa.HERTTA)
                    System.out.println("WAU! Herttavärisuora!");

                System.out.println("Värisuora!");
            }
            else if (eiMitaan)
                System.out.println("Ei mitään.");



            // arvojen resetointi
            for (Kortti kortti: kasi.getKortit())
                korttipakka.lisaaKortti(kortti);

            kasi.getKortit().clear();
            kasi.setAssienArvot(1);

        }

    }


}
