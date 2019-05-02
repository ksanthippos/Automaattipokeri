package korttipakka;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pakka {

    private List<Kortti> pakka;
    private List<Maa> maat;
    private List<Arvo> arvot;
    private List<Image> kuvat;


    public Pakka() {

        this.pakka = new ArrayList<>();
        this.maat = new ArrayList<>();
        this.arvot = new ArrayList<>();
        this.kuvat = new ArrayList<>();

        maat.add(Maa.RISTI);
        maat.add(Maa.RUUTU);
        maat.add(Maa.HERTTA);
        maat.add(Maa.PATA);

        arvot.add(Arvo.KAKKONEN);
        arvot.add(Arvo.KOLMONEN);
        arvot.add(Arvo.NELONEN);
        arvot.add(Arvo.VIITONEN);
        arvot.add(Arvo.KUUTONEN);
        arvot.add(Arvo.SEISKA);
        arvot.add(Arvo.KASI);
        arvot.add(Arvo.YSI);
        arvot.add(Arvo.KYMPPI);
        arvot.add(Arvo.JATKA);
        arvot.add(Arvo.AKKA);
        arvot.add(Arvo.KUNKKU);
        arvot.add(Arvo.ASSA);

        // kortin luominen ja lisääminen pakkaan
        for (int i = 0; i < maat.size(); i++) {
            for (int j = 0; j < arvot.size(); j++) {
                pakka.add(new Kortti(maat.get(i), arvot.get(j)));
            }
        }

        // kuvien lataaminen
        Image C2 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\2C.png");
        Image D2 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\2D.png");
        Image H2 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\2H.png");
        Image S2 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\2S.png");
        Image C3 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\3C.png");
        Image D3 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\3D.png");
        Image H3 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\3H.png");
        Image S3 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\3S.png");
        Image C4 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\4C.png");
        Image D4 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\4D.png");
        Image H4 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\4H.png");
        Image S4 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\4S.png");
        Image C5 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\5C.png");
        Image D5 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\5D.png");
        Image H5 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\5H.png");
        Image S5 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\5S.png");
        Image C6 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\6C.png");
        Image D6 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\6D.png");
        Image H6 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\6H.png");
        Image S6 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\6S.png");
        Image C7 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\7C.png");
        Image D7 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\7D.png");
        Image H7 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\7H.png");
        Image S7 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\7S.png");
        Image C8 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\8C.png");
        Image D8 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\8D.png");
        Image H8 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\8H.png");
        Image S8 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\8S.png");
        Image C9 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\9C.png");
        Image D9 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\9D.png");
        Image H9 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\9H.png");
        Image S9 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\9S.png");
        Image C10 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\10C.png");
        Image D10 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\10D.png");
        Image H10 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\10H.png");
        Image S10 = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\10S.png");
        Image CJ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\JC.png");
        Image DJ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\JD.png");
        Image HJ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\JH.png");
        Image SJ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\JS.png");
        Image CQ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\QC.png");
        Image DQ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\QD.png");
        Image HQ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\QH.png");
        Image SQ = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\QS.png");
        Image CK = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\KC.png");
        Image DK = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\KD.png");
        Image HK = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\KH.png");
        Image SK = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\KS.png");
        Image CA = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\AC.png");
        Image DA = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\AD.png");
        Image HA = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\AH.png");
        Image SA = new Image("file:C:\\Users\\Don Miguel\\IdeaProjects\\Automaattipokeri\\src\\kuvat\\AS.png");

        kuvat.add(C2);
        kuvat.add(C3);
        kuvat.add(C4);
        kuvat.add(C5);
        kuvat.add(C6);
        kuvat.add(C7);
        kuvat.add(C8);
        kuvat.add(C9);
        kuvat.add(C10);
        kuvat.add(CJ);
        kuvat.add(CQ);
        kuvat.add(CK);
        kuvat.add(CA);
        kuvat.add(D2);
        kuvat.add(D3);
        kuvat.add(D4);
        kuvat.add(D5);
        kuvat.add(D6);
        kuvat.add(D7);
        kuvat.add(D8);
        kuvat.add(D9);
        kuvat.add(D10);
        kuvat.add(DJ);
        kuvat.add(DQ);
        kuvat.add(DK);
        kuvat.add(DA);
        kuvat.add(H2);
        kuvat.add(H3);
        kuvat.add(H4);
        kuvat.add(H5);
        kuvat.add(H6);
        kuvat.add(H7);
        kuvat.add(H8);
        kuvat.add(H9);
        kuvat.add(H10);
        kuvat.add(HJ);
        kuvat.add(HQ);
        kuvat.add(HK);
        kuvat.add(HA);
        kuvat.add(S2);
        kuvat.add(S3);
        kuvat.add(S4);
        kuvat.add(S5);
        kuvat.add(S6);
        kuvat.add(S7);
        kuvat.add(S8);
        kuvat.add(S9);
        kuvat.add(S10);
        kuvat.add(SJ);
        kuvat.add(SQ);
        kuvat.add(SK);
        kuvat.add(SA);


        
        // kuva kortille
        for (int i = 0; i < kuvat.size(); i++)
            this.getKortit().get(i).setKuva(kuvat.get(i));


    }

    public List<Kortti> getKortit() { return pakka; }

    public void sekoitaPakka() {
        Collections.shuffle(pakka);
    }

    public Kortti jaaKortti() {
        Kortti jaettava = pakka.get(0); // päällimmäinen kortti
        pakka.remove(pakka.get(0));
        return jaettava;
    }

    public void lisaaKortti(Kortti kortti) {
        if (pakka.size() <= 52)
            pakka.add(kortti);  // laittaa kortin pakan pohjalle
    }


    public String toString() {
        StringBuilder kortit = new StringBuilder();
        pakka.forEach(kortti -> kortit.append(kortti + "\n"));

        return kortit.toString();
    }
}
