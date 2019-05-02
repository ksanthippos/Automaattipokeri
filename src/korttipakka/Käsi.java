package korttipakka;

import java.util.ArrayList;
import java.util.List;

public class Käsi implements Arvioitava {

    private List<Kortti> kasi;
    private int ylaRaja;

    public Käsi(int ylaraja) {
        this.kasi = new ArrayList<>();
        this.ylaRaja = ylaraja;
    }

    public void nostaKortti(Kortti kortti) {
        if (kasi.size() == ylaRaja)
            return;

        kasi.add(kortti);
    }

    public Kortti lyoKortti(Kortti kortti) {

        Kortti lyotava = kortti;
        if (kasi.contains(kortti)) {
            kasi.remove(kortti);
            return lyotava;
        }

        return null;
    }

    public Kortti lyoEkaKortti() {

        Kortti lyotava = kasi.get(0);
        kasi.remove(kasi.get(0));
        return lyotava;
    }

    public int getKoko() {
        return (int)(kasi.stream().count());
    }

    public int getYlaRaja() {
        return ylaRaja;
    }

    public List<Kortti> getKortit() { return kasi; }


    // ässien arvot pitää voida asettaa luokan ulkopuolelta
    public void setAssienArvot(int arvo) {

        kasi.stream()
                .filter(kortti -> kortti.getArvo().equals(Arvo.ASSA))
                .forEach(kortti -> kortti.setAssanArvo(arvo));
    }

    public int annaArvo() {

        int summa = kasi.stream()
            .map(kortti -> kortti.annaArvo())
            .reduce(0, (edellinenSumma, kortti) -> edellinenSumma + kortti);

        return summa;
    }

    public String toString() {

        StringBuilder kortit = new StringBuilder();
        kasi.forEach(kortti -> kortit.append(kortti + "\n"));

        return kortit.toString();
    }

    // Tarviikohan näitä mihinkään?

    /*    public Kortti lyoSatunnainenKortti() {

        Collections.shuffle(kasi);
        Kortti lyotava = kasi.get(0);
        kasi.remove(kasi.get(0));
        return lyotava;
    }


    }*/



}
