package korttipakka;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;



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
        HBox ylaTekstit = new HBox();
        String punainenReuna = "-fx-border-color: red; -fx-border-width: 0 2 2 0";
        String vihreaReuna = "-fx-border-color: green;";



        // ********* korttien pohjat ************
        Pane kortti1 = new Pane();
        Pane kortti2 = new Pane();
        Pane kortti3 = new Pane();
        Pane kortti4 = new Pane();
        Pane kortti5 = new Pane();


        kortit.getChildren().addAll(kortti1, kortti2, kortti3, kortti4, kortti5);
        kortit.setSpacing(10);
        keskiOsa.setBottom(kortit);


        // *******************************

        // ********** ylä- ja alaosa **********
        TextArea tekstiKentta = new TextArea();
        tekstiKentta.setPrefSize(220, 25);
        tekstiKentta.setFont(Font.font("Monospaced", 12));


        Button nappiJaa = new Button();
        Button nappiTarkista = new Button();
        Button nappiVaihda = new Button();
        Button nappiPanos = new Button();
        nappiJaa.setText("Uusi käsi");
        nappiTarkista.setText("Tarkista käsi");
        nappiVaihda.setText("Lukitse ja vaihda");
        nappiPanos.setText("Panos");
        napit.getChildren().addAll(nappiJaa, nappiTarkista, nappiVaihda, nappiPanos);
        napit.setSpacing(30);

        nappiTarkista.setDisable(true);
        nappiVaihda.setDisable(true);


        Label tekstiKrediitit = new Label();
        tekstiKrediitit.setStyle("-fx-color: yellow;");
        tekstiKrediitit.setText("Krediitit: 10.0");
        tekstiKrediitit.setFont(Font.font("Monospaced", 18));

        Label tekstiPanos = new Label();
        tekstiPanos.setStyle("-fx-color: yellow;");
        tekstiPanos.setText("Panos: 1.0");
        tekstiPanos.setFont(Font.font("Monospaced", 18));

        ylaTekstit.getChildren().addAll(tekstiKrediitit, tekstiPanos);
        ylaTekstit.setSpacing(50);

        alaOsa.setLeft(napit);
        alaOsa.setRight(tekstiKentta);

        ylaOsa.setStyle("-fx-background-color: white;");
        ylaOsa.setCenter(ylaTekstit);

        // ************************************



        // ***********************************
        // erilliset napit eri toiminnoille
        // **********************************

        nappiJaa.setOnAction(e -> {

            tekstiKentta.clear();
            nappiJaa.setDisable(true);
            nappiVaihda.setDisable(false);
            nappiTarkista.setDisable(false);
            nappiPanos.setDisable(true);


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

            logiikka.setKrediitit(-logiikka.getPanos());
            tekstiKrediitit.setText("Krediitit: " + logiikka.getKrediitit());

        });

        nappiTarkista.setOnAction(e -> {

            nappiVaihda.setDisable(true);
            nappiTarkista.setDisable(true);
            nappiJaa.setDisable(false);
            nappiPanos.setDisable(false);

            // käden tarkistaminen
            tekstiKentta.appendText(logiikka.tarkistaKasi() + "\n");

            for (int i = 0; i < 5; i++)
                pakka.lisaaKortti(kasi.lyoEkaKortti());


            kasi.getKortit().clear();
            kasi.nollaaValitut();

            tekstiKrediitit.setText("Krediitit: " + logiikka.getKrediitit());

            if (logiikka.getKrediitit() <= 0) {
                tekstiKentta.appendText("Peli loppui!");
                nappiVaihda.setDisable(true);
                nappiTarkista.setDisable(true);
                nappiJaa.setDisable(true);
                nappiPanos.setDisable(true);
            }


        });

        nappiVaihda.setOnAction(e -> {

            nappiVaihda.setDisable(true);
            nappiJaa.setDisable(true);
            nappiTarkista.setDisable(false);


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


            // tyhjennetään käsi, lisätään sinne lukitut kortit ja täytetään loput pakasta
            kasi.lisaaValitut();
            kasi.getKortit().clear();
            for (Kortti kortti: kasi.getValitut())
                kasi.nostaKortti(kortti);

            while (kasi.getKortit().size() < 5)
                kasi.nostaKortti(pakka.jaaKortti());


            kortti1.getChildren().add(kasi.getKortit().get(0).getKuva());
            kortti2.getChildren().add(kasi.getKortit().get(1).getKuva());
            kortti3.getChildren().add(kasi.getKortit().get(2).getKuva());
            kortti4.getChildren().add(kasi.getKortit().get(3).getKuva());
            kortti5.getChildren().add(kasi.getKortit().get(4).getKuva());


        });

        nappiPanos.setOnAction(e -> {
            logiikka.korotaPanosta();
            tekstiPanos.setText("Panos: " + logiikka.getPanos());
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
