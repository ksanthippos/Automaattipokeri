package korttipakka;

import java.util.Comparator;

public class ArvonTarkistaja implements Comparator<Kortti> {


    public int compare(Kortti A, Kortti B) {

        if (A.getArvo() == B.getArvo())
            return A.annaArvo() - B.annaArvo();
        else
            return A.getArvo().ordinal() - B.getArvo().ordinal();
    }


}
