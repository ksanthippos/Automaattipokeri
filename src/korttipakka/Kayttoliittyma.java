package korttipakka;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Kayttoliittyma extends Application {


    public void start(Stage ikkuna) {

        BorderPane paaAsettelu = new BorderPane();
        BorderPane ylaOsa = new BorderPane();
        BorderPane keskiOsa = new BorderPane();
        BorderPane alaOsa = new BorderPane();
        HBox kortit = new HBox();
        HBox napit = new HBox();
        String punainenReuna = "-fx-border-color: red;";
        String vihreaReuna = "-fx-border-color: green;";

        paaAsettelu.setPrefSize(700, 400);


        // ********* kortit ************
        Pane kortti1 = new Pane();
        Pane kortti2 = new Pane();
        Pane kortti3 = new Pane();
        Pane kortti4 = new Pane();
        Pane kortti5 = new Pane();

        Pakka pakka = new Pakka();
        Käsi kasi = new Käsi(5);

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

        jaaKortit.setText("Jaa uusi käsi");
        alaOsa.setCenter(jaaKortit);

        // ************************************

        // ******* toiminnallisuus *************

        jaaKortit.setOnAction(e -> {

            // nollaus
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

            if (jaaKortit.getText().equals("Jaa uusi käsi")) {
                jaaKortit.setText("Vaihda valitut kortit");

                pakka.sekoitaPakka();

                if (kortti1.getStyle().equals(punainenReuna)) {
                    pakka.lisaaKortti(kasi.lyoKortti(kasi.getKortit().get(0)));
                    kasi.nostaKortti(pakka.jaaKortti());
                    kortti1.getChildren().add(kasi.getKortit().get(0).getKuva());

                }


                else {
                    // ei yhtäkään valittuna, vaihdetaan kaikki
                    for (int i = 0; i < 5; i++)
                        kasi.nostaKortti(pakka.jaaKortti());

                    kortti1.getChildren().add(kasi.getKortit().get(0).getKuva());
                    kortti2.getChildren().add(kasi.getKortit().get(1).getKuva());
                    kortti3.getChildren().add(kasi.getKortit().get(2).getKuva());
                    kortti4.getChildren().add(kasi.getKortit().get(3).getKuva());
                    kortti5.getChildren().add(kasi.getKortit().get(4).getKuva());
                }

            }

            else if (jaaKortit.getText().equals("Vaihda valitut kortit")) {
                jaaKortit.setText("Jaa uusi käsi");

                for (int i = 0; i < 5; i++)
                    pakka.lisaaKortti(kasi.lyoEkaKortti());

                tekstiKentta.clear();
                pakka.sekoitaPakka();

            }

        });


        // korttien klikkaaminen
        kortti1.setOnMouseClicked(e -> {
            if (kortti1.getStyle().equals(vihreaReuna)) {
                kortti1.setStyle(punainenReuna);
            }
            else if (kortti1.getStyle().equals(punainenReuna))
                kortti1.setStyle(vihreaReuna);
        });
        kortti2.setOnMouseClicked(e -> {
            if (kortti2.getStyle().equals(vihreaReuna))
                kortti2.setStyle(punainenReuna);
            else if (kortti2.getStyle().equals(punainenReuna))
                kortti2.setStyle(vihreaReuna);
        });
        kortti3.setOnMouseClicked(e -> {
            if (kortti3.getStyle().equals(vihreaReuna))
                kortti3.setStyle(punainenReuna);
            else if (kortti3.getStyle().equals(punainenReuna))
                kortti3.setStyle(vihreaReuna);
        });
        kortti4.setOnMouseClicked(e -> {
            if (kortti4.getStyle().equals(vihreaReuna))
                kortti4.setStyle(punainenReuna);
            else if (kortti4.getStyle().equals(punainenReuna))
                kortti4.setStyle(vihreaReuna);
        });
        kortti5.setOnMouseClicked(e -> {
            if (kortti5.getStyle().equals(vihreaReuna))
                kortti5.setStyle(punainenReuna);
            else if (kortti5.getStyle().equals(punainenReuna))
                kortti5.setStyle(vihreaReuna);
        });


        // ************************************
        //  *********** pääasettelu ***********

        paaAsettelu.setTop(ylaOsa);
        paaAsettelu.setCenter(keskiOsa);
        paaAsettelu.setBottom(alaOsa);
        paaAsettelu.setStyle("-fx-background-color: green");

        ikkuna.setScene(new Scene(paaAsettelu));
        ikkuna.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
