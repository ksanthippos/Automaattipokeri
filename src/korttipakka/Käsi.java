package korttipakka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Käsi implements Arvioitava {

    private List<Kortti> kasi;
    private List<Kortti> valitut;
    private List<Kortti> poistetut;
    private int ylaRaja;


    public Käsi(int ylaraja) {
        this.kasi = new ArrayList<>();
        this.valitut = new ArrayList<>();
        this.poistetut = new ArrayList<>();
        this.ylaRaja = ylaraja;
    }

    public void nostaKortti(Kortti kortti) {
        if (kasi.size() == ylaRaja)
            return;

        kasi.add(kortti);
    }


    public Kortti lyoEkaKortti() {

        Kortti lyotava = kasi.get(0);
        kasi.remove(kasi.get(0));
        return lyotava;
    }

    public void asetaPaallimmaiseksi(Kortti kortti) {
        kasi.add(0, kortti);
    }

    public int getKoko() {
        return (int)(kasi.stream().count());
    }

    public List<Kortti> getKortit() { return kasi; }

    public void lisaaValittu(Kortti kortti) { valitut.add(kortti); }

    public List<Kortti> getValitut() {
        return valitut;
    }


    public void lisaaPoistettu(Kortti kortti) {
        poistetut.add(kortti);
    }

    public List<Kortti> getPoistetut() { return poistetut; }


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





}
