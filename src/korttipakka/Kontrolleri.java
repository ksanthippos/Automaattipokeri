package korttipakka;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.media.AudioClip;

import java.net.URL;


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
        paaAsettelu.setPrefSize(670, 350);
        BorderPane ylaOsa = new BorderPane();
        BorderPane keskiOsa = new BorderPane();
        BorderPane alaOsa = new BorderPane();

        ylaOsa.setPrefSize(670, 40);
        ylaOsa.setStyle("-fx-background-color: white;");
        ylaOsa.setPadding(new Insets(5, 10, 5, 10));
        keskiOsa.setPadding(new Insets(0, 10, 0, 10));
        alaOsa.setPadding(new Insets(10, 10, 10, 10));

        HBox kortit = new HBox();
        HBox napit = new HBox();
        HBox ylaTekstit = new HBox();

        String punainenReuna = "-fx-border-color: yellow; -fx-border-width: 0 2 2 0";
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

        Button nappiJaa = new Button();
        Button nappiTarkista = new Button();
        Button nappiVaihda = new Button();
        Button nappiPanos = new Button();
        nappiJaa.setText("Uusi käsi");
        nappiTarkista.setText("Tarkista käsi");
        nappiVaihda.setText("Lukitse ja vaihda");
        nappiPanos.setText("Panos");
        nappiJaa.setFont(Font.font("Monospaced"));
        nappiTarkista.setFont(Font.font("Monospaced"));
        nappiVaihda.setFont(Font.font("Monospaced"));
        nappiPanos.setFont(Font.font("Monospaced"));
        nappiJaa.setStyle("-fx-background-color: lime;");
        nappiTarkista.setStyle("-fx-background-color: gold;");
        nappiVaihda.setStyle("-fx-background-color: coral;");
        nappiPanos.setStyle("-fx-background-color: cyan;");

        napit.getChildren().addAll(nappiJaa, nappiTarkista, nappiVaihda, nappiPanos);
        napit.setSpacing(40);


        nappiTarkista.setDisable(true);
        nappiVaihda.setDisable(true);

        TextArea tekstiKentta = new TextArea();
        tekstiKentta.setPrefSize(220, 28);
        tekstiKentta.setFont(Font.font("Monospaced", 12));

        Label tekstiKrediitit = new Label();
        tekstiKrediitit.setText("Krediitit: " + logiikka.getKrediitit());
        tekstiKrediitit.setFont(Font.font("Monospaced", 15));

        Label tekstiPanos = new Label();
        tekstiPanos.setText("Panos: " + logiikka.getPanos());
        tekstiPanos.setFont(Font.font("Monospaced", 15));

        Label tekstiPisteet = new Label();
        tekstiPisteet.setText("Pisteet: " + logiikka.getPisteet());
        tekstiPisteet.setFont(Font.font("Monospaced", 15));

        ylaTekstit.getChildren().addAll(tekstiKrediitit, tekstiPanos, tekstiPisteet, tekstiKentta);
        ylaTekstit.setSpacing(30);


        alaOsa.setCenter(napit);
        ylaOsa.setCenter(ylaTekstit);

        // ************************************

        // äääniefektit



        AudioClip jaaKortti = new AudioClip(getClass().getResource("/aanet/cardPlace2.wav").toString());
        AudioClip valitseKortti = new AudioClip(getClass().getResource("/aanet/cardSlide1.wav").toString());
        AudioClip poistaKortit = new AudioClip(getClass().getResource("/aanet/cardPlace4.wav").toString());
        AudioClip korotaPanos = new AudioClip(getClass().getResource("/aanet/chipsStack1.wav").toString());


        // ***********************************
        // pelinappien toiminnot
        // **********************************

        nappiJaa.setOnAction(e -> {

            jaaKortti.play();

            tekstiKentta.clear();
            nappiJaa.setDisable(true);
            nappiVaihda.setDisable(false);
            nappiTarkista.setDisable(false);
            nappiPanos.setDisable(true);
            kortit.setDisable(false);

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


            // uuden käden jakaminen
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
            tekstiPisteet.setText("Pisteet: " + logiikka.getPisteet());

        });

        nappiTarkista.setOnAction(e -> {

            nappiVaihda.setDisable(true);
            nappiTarkista.setDisable(true);
            nappiJaa.setDisable(false);
            nappiPanos.setDisable(false);

            // käden tarkistaminen
            tekstiKentta.appendText(logiikka.tarkistaKasi() + "\n");


            // pelikäden palautus ja nollaus, valittujen nollaus
            for (int i = 0; i < 5; i++)
                pakka.lisaaKortti(kasi.lyoEkaKortti());

            kasi.getKortit().clear();
            kasi.getValitut().clear();

            // poistokäden palautus ja nollaus
            for (Kortti kortti: kasi.getPoistetut())
                pakka.lisaaKortti(kortti);

            kasi.getPoistetut().clear();
            for (Kortti kortti: pakka.getKortit())
                kortti.setValittu(false);


            // varmistus, ettei panos ylitä krediittien määrää ja tilanteen päivitys
            logiikka.setPanos();
            tekstiPanos.setText("Panos: " + logiikka.getPanos());

            tekstiKrediitit.setText("Krediitit: " + logiikka.getKrediitit());
            tekstiPisteet.setText("Pisteet: " + logiikka.getPisteet());

            if (logiikka.getKrediitit() <= 0) {
                tekstiKentta.appendText("Peli loppui!");
                nappiVaihda.setDisable(true);
                nappiTarkista.setDisable(true);
                nappiJaa.setDisable(true);
                nappiPanos.setDisable(true);
            }

        });

        nappiVaihda.setOnAction(e -> {

            poistaKortit.play();

            nappiVaihda.setDisable(true);
            nappiJaa.setDisable(true);
            nappiTarkista.setDisable(false);
            kortit.setDisable(true);

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


            // jaotellaan käden kortit valittuihin ja poistettaviin
            for (Kortti kortti: kasi.getKortit()) {
                if (kortti.getValittu())
                    kasi.lisaaValittu(kortti);
                else
                    kasi.lisaaPoistettu(kortti);
            }

            //tyhjennetään käsi, lisätään sinne lukitut kortit ja täytetään loput pakasta
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
            korotaPanos.play();
            tekstiPanos.setText("Panos: " + logiikka.getPanos());
        });





        // **********************************

        // korttien klikkaaminen
        kortti1.setOnMouseClicked(e -> {
            valitseKortti.play();
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
            valitseKortti.play();
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
            valitseKortti.play();
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
            valitseKortti.play();
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
            valitseKortti.play();
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
