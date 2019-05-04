package korttipakka;

import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kortti implements Arvioitava {

    private Arvo arvo;
    private Maa maa;
    private int assanArvo;
    private ImageView kuva;
    private boolean valittu;

    public Kortti(Maa maa, Arvo arvo) {
        this.maa = maa;
        this.arvo = arvo;
        this.assanArvo = 1; // ässän oletusarvo
        this.valittu = false;
    }


    public void setKuva(Image kuvaTiedosto) {

        this.kuva = new ImageView(kuvaTiedosto);
        kuva.setFitWidth(120);
        kuva.setPreserveRatio(true);
        kuva.setSmooth(true);
        kuva.setCache(true);

    }

/*    public void merkkaa(boolean arvo) {
        if (arvo)
            kuva.setOpacity(0.7);

        kuva.setOpacity(1.0);
    }*/

    public ImageView getKuva() {
        return kuva;
    }

    public void setAssanArvo(int arvo) {
        assanArvo = arvo;
    }

    public Arvo getArvo() {
        return arvo;
    }

    public Maa getMaa() {
        return maa;
    }

    public void setValittu(boolean arvo) { valittu = arvo; }

    public boolean getValittu() { return valittu; }

    public int annaArvo() {

        switch (arvo) {
            case KAKKONEN:
                return 2;
            case KOLMONEN:
                return 3;
            case NELONEN:
                return 4;
            case VIITONEN:
                return 5;
            case KUUTONEN:
                return 6;
            case SEISKA:
                return 7;
            case KASI:
                return 8;
            case YSI:
                return 9;
            case KYMPPI:
                return 10;
            case JATKA:
                return 11;
            case AKKA:
                return 12;
            case KUNKKU:
                return 13;
            case ASSA:
                return assanArvo;
            default:
                return 0;
        }

    }

    public int annaMaa() {

        switch (maa) {
            case PATA:
                return 1;
            case RUUTU:
                return 2;
            case RISTI:
                return 3;
            case HERTTA:
                return 4;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return maa + " " + arvo;
    }





    // Yksittäistä korttia ei tarvinne vertailla tästä luokasta käsin?
/*    @Override
    public int compareTo(Kortti kortti) {
        if (this.annaArvo() == kortti.annaArvo())           // korttien arvot samoja
            return 0;
        else if (this.annaMaa() == kortti.annaMaa())        // maat samoja
            return 0;

        else
            return this.annaArvo() - kortti.annaArvo();     // korttien arvot eivät samoja



    }*/
}
