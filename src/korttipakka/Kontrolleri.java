package korttipakka;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Kontrolleri {

    private Logiikka logiikka;
    private Käsi kasi;
    private Pakka pakka;

    public Kontrolleri() {
        this.pakka = new Pakka();
        this.kasi = new Käsi(5);
        this.logiikka = new Logiikka(this.kasi);
    }

    public Parent getPeliAlue() {

        // ******** pelialueen osat *************
        BorderPane paaAsettelu = new BorderPane();
        BorderPane ylaOsa = new BorderPane();
        BorderPane keskiOsa = new BorderPane();
        BorderPane alaOsa = new BorderPane();
        HBox kortit = new HBox();
        HBox napit = new HBox();
        String punainenReuna = "-fx-border-color: red;";
        String vihreaReuna = "-fx-border-color: green;";



        // ********* korttien pohjat ************
        Pane kortti1 = new Pane();
        Pane kortti2 = new Pane();
        Pane kortti3 = new Pane();
        Pane kortti4 = new Pane();
        Pane kortti5 = new Pane();


        kortit.getChildren().addAll(kortti1, kortti2, kortti3, kortti4, kortti5);
        kortit.setSpacing(10);
        keskiOsa.setCenter(kortit);


        // *******************************

        // ********** ylä- ja alaosa **********
        Label teksti1 = new Label();
        TextArea tekstiKentta = new TextArea();
        tekstiKentta.setPrefSize(130, 150);
        tekstiKentta.setFont(Font.font("Monospaced", 12));

        ylaOsa.setLeft(teksti1);
        ylaOsa.setRight(tekstiKentta);

        Button jaaKortit = new Button();
        Button tarkistaKasi = new Button();
        Button vaihdaKortit = new Button();
        jaaKortit.setText("Uusi käsi");
        tarkistaKasi.setText("Tarkista käsi");
        vaihdaKortit.setText("Vaihda valitut");
        napit.getChildren().addAll(jaaKortit, tarkistaKasi, vaihdaKortit);
        napit.setSpacing(20);

        tarkistaKasi.setDisable(true);
        vaihdaKortit.setDisable(true);


        alaOsa.setCenter(napit);


        // ************************************

        // ******* toiminnallisuus *************

        //jaaKortit.setText("Jaa uusi käsi");

/*        jaaKortit.setOnAction(e -> {

            // reunojen nollaus
            kortti1.setStyle(vihreaReuna);
            kortti2.setStyle(vihreaReuna);
            kortti3.setStyle(vihreaReuna);
            kortti4.setStyle(vihreaReuna);
            kortti5.setStyle(vihreaReuna);


            kortti1.getChildren().clear();
            kortti2.getChildren().clear();
            kortti3.getChildren().clear();
            kortti4.getChildren().clear();
            kortti5.getChildren().clear();

            // nappi vaihtaa kortit
            if (jaaKortit.getText().equals("Jaa uusi käsi")) {
                jaaKortit.setText("Vaihda valitut kortit");

                pakka.sekoitaPakka();

                // ei valittu mitään
                if (kasi.getValitut().size() == 0) {

                    for (int i = 0; i < 5; i++)
                        kasi.nostaKortti(pakka.jaaKortti());

                    kortti1.getChildren().add(kasi.getKortit().get(0).getKuva());
                    kortti2.getChildren().add(kasi.getKortit().get(1).getKuva());
                    kortti3.getChildren().add(kasi.getKortit().get(2).getKuva());
                    kortti4.getChildren().add(kasi.getKortit().get(3).getKuva());
                    kortti5.getChildren().add(kasi.getKortit().get(4).getKuva());
                }

                else {

                    kasi.getKortit().clear();
                    for (int i = 0; i < kasi.getValitut().size(); i++)
                        kasi.nostaKortti(kasi.getValitut().get(i));

                    for (int i = 0; i < kasi.getValitut().size() - kasi.getKortit().size(); i++)
                        kasi.nostaKortti(pakka.jaaKortti());


                    kortti1.getChildren().add(kasi.getKortit().get(0).getKuva());
                    kortti2.getChildren().add(kasi.getKortit().get(1).getKuva());
                    kortti3.getChildren().add(kasi.getKortit().get(2).getKuva());
                    kortti4.getChildren().add(kasi.getKortit().get(3).getKuva());
                    kortti5.getChildren().add(kasi.getKortit().get(4).getKuva());

                }

                // käden tarkistaminen
                tekstiKentta.appendText(logiikka.tarkistaKasi());

            }

            // nappi jakaa kokonaan uuden käden
            else if (jaaKortit.getText().equals("Vaihda valitut kortit")) {

                // käden vaihtaminen uuteen
                jaaKortit.setText("Jaa uusi käsi");

                for (int i = 0; i < 5; i++)
                    pakka.lisaaKortti(kasi.lyoEkaKortti());

                tekstiKentta.clear();
                pakka.sekoitaPakka();
                kasi.getKortit().clear();
                kasi.nollaaValitut();

            }

        });*/


        // ***********************************
        // erilliset napit eri toiminnoille
        // **********************************

        jaaKortit.setOnAction(e -> {

            jaaKortit.setDisable(true);
            vaihdaKortit.setDisable(false);
            tarkistaKasi.setDisable(false);

            // reunojen nollaus
            kortti1.setStyle(vihreaReuna);
            kortti2.setStyle(vihreaReuna);
            kortti3.setStyle(vihreaReuna);
            kortti4.setStyle(vihreaReuna);
            kortti5.setStyle(vihreaReuna);


            kortti1.getChildren().clear();
            kortti2.getChildren().clear();
            kortti3.getChildren().clear();
            kortti4.getChildren().clear();
            kortti5.getChildren().clear();

            pakka.sekoitaPakka();

            for (int i = 0; i < 5; i++)
                kasi.nostaKortti(pakka.jaaKortti());

            kortti1.getChildren().add(kasi.getKortit().get(0).getKuva());
            kortti2.getChildren().add(kasi.getKortit().get(1).getKuva());
            kortti3.getChildren().add(kasi.getKortit().get(2).getKuva());
            kortti4.getChildren().add(kasi.getKortit().get(3).getKuva());
            kortti5.getChildren().add(kasi.getKortit().get(4).getKuva());

        });

        tarkistaKasi.setOnAction(e -> {

            vaihdaKortit.setDisable(true);
            tarkistaKasi.setDisable(true);
            jaaKortit.setDisable(false);

            // käden tarkistaminen
            tekstiKentta.appendText(logiikka.tarkistaKasi() + "\n");

            for (int i = 0; i < 5; i++)
                pakka.lisaaKortti(kasi.lyoEkaKortti());


            kasi.getKortit().clear();
            kasi.nollaaValitut();

        });

        vaihdaKortit.setOnAction(e -> {

            // if kädestä kortti 1 valittu --> lukitaan jotenkin Pane kortti1 ja jaetaan muut


        });





        // **********************************

        // korttien klikkaaminen
        kortti1.setOnMouseClicked(e -> {
            if (kortti1.getStyle().equals(vihreaReuna)) {
                kortti1.setStyle(punainenReuna);
                kasi.getKortit().get(0).setValittu(true);
            }
            else if (kortti1.getStyle().equals(punainenReuna)) {
                kortti1.setStyle(vihreaReuna);
                kasi.getKortit().get(0).setValittu(false);
            }
        });
        kortti2.setOnMouseClicked(e -> {
            if (kortti2.getStyle().equals(vihreaReuna)) {
                kortti2.setStyle(punainenReuna);
                kasi.getKortit().get(1).setValittu(true);
            }
            else if (kortti2.getStyle().equals(punainenReuna)) {
                kortti2.setStyle(vihreaReuna);
                kasi.getKortit().get(1).setValittu(false);
            }
        });
        kortti3.setOnMouseClicked(e -> {
            if (kortti3.getStyle().equals(vihreaReuna)) {
                kortti3.setStyle(punainenReuna);
                kasi.getKortit().get(2).setValittu(true);

            }
            else if (kortti3.getStyle().equals(punainenReuna)) {
                kortti3.setStyle(vihreaReuna);
                kasi.getKortit().get(2).setValittu(false);
            }
        });
        kortti4.setOnMouseClicked(e -> {
            if (kortti4.getStyle().equals(vihreaReuna)) {
                kortti4.setStyle(punainenReuna);
                kasi.getKortit().get(3).setValittu(true);

            }
            else if (kortti4.getStyle().equals(punainenReuna)) {
                kortti4.setStyle(vihreaReuna);
                kasi.getKortit().get(3).setValittu(false);
            }
        });
        kortti5.setOnMouseClicked(e -> {
            if (kortti5.getStyle().equals(vihreaReuna)) {
                kortti5.setStyle(punainenReuna);
                kasi.getKortit().get(4).setValittu(true);
            }
            else if (kortti5.getStyle().equals(punainenReuna)) {
                kortti5.setStyle(vihreaReuna);
                kasi.getKortit().get(4).setValittu(false);
            }
        });


        // ************************************
        //  *********** pääasettelu ***********

        paaAsettelu.setTop(ylaOsa);
        paaAsettelu.setCenter(keskiOsa);
        paaAsettelu.setBottom(alaOsa);
        paaAsettelu.setStyle("-fx-background-color: green");


        return paaAsettelu;

    }






}
