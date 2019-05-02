package korttipakka;

import java.util.Comparator;

public class MaanTarkistaja implements Comparator<Kortti> {


    public int compare(Kortti A, Kortti B) {

        if (A.getMaa() == B.getMaa())
            return A.annaMaa() - B.annaMaa();
        else
            return A.getMaa().ordinal() - B.getMaa().ordinal();
    }


}
