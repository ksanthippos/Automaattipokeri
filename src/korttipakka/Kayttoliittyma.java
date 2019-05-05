package korttipakka;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;

import javafx.scene.media.AudioClip;
import javafx.stage.Stage;



public class Kayttoliittyma extends Application {


    public void start(Stage ikkuna) {

        AudioClip plarays = new AudioClip("file:src/aanet/cardFan1.wav");

        // valikot ja niiden toiminnallisuus
        MenuBar menuBar = new MenuBar();

        Menu menuPeli = new Menu("Peli");
        MenuItem uusiPeli = new MenuItem("Uusi peli");
        MenuItem lopetaPeli = new MenuItem("Lopeta");
        menuPeli.getItems().addAll(uusiPeli, lopetaPeli);

        Menu menuOhje = new Menu(("Ohje"));
        MenuItem peliOhje = new MenuItem("Ohjeet");
        MenuItem peliLahteet = new MenuItem("Lähdemateriaali");
        MenuItem peliTiedot = new MenuItem("Pelin tiedot");
        menuOhje.getItems().addAll(peliOhje, peliLahteet, peliTiedot);

        menuBar.getMenus().addAll(menuPeli, menuOhje);

        uusiPeli.setOnAction(e -> {
            plarays.play();
            BorderPane paaNakyma = new BorderPane();
            Kontrolleri kontrolli = new Kontrolleri();
            paaNakyma.setTop(menuBar);
            paaNakyma.setCenter(kontrolli.getPeliAlue());
            ikkuna.setScene(new Scene(paaNakyma));
            ikkuna.show();
        });

        lopetaPeli.setOnAction(e -> {
            System.exit(0);
        });

        peliOhje.setOnAction(e -> {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Peliohjeet");
            info.setContentText("Tämä peli imitoi raha-automaattipokereita, eli varsinaista tietokonevastustajaa ei ole. " +
                                "Pelaajalle jaetaan 5 kortin käsi, josta voi vaihtaa yhden kerran 1 - 4 korttia pois. Tämän " +
                                "jälkeen tarkista käsi - toiminnolla jaetaan mahdollinen voitonmaksu. " +
                                "Pelaaja voi muuttaa pelin panosta välillä 1.0 - 5.0 krediittiä, mikä vaikuttaa suoraan " +
                                "verrannollisesti mahdolliseen voitonmaksuun (panos x voitonmaksu).\n\n" +
                                "Peli päättyy, kun krediitit ovat loppu.\n\n" +
                                "Hyviä pelejä!");
            info.showAndWait();

        });

        peliLahteet.setOnAction(e -> {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Lähdemateriaali");
            info.setContentText("Kasinoääniefektien lähde:\nhttps://opengameart.org/, lisenssillä CC0 1.0.\n\n" +
                                "Pelin päättymisääniefektin lähde: https://freesound.org/people/ebcrosby/sounds/333496/, \n" +
                                "tekijänä ebcrosby lisenssillä CC BY 3.0.");
            info.showAndWait();
        });

        peliTiedot.setOnAction(e -> {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("MR-Automaattipokeri v.1.0");
            info.setContentText("(c) Mikael Rauhala, 2019");
            info.showAndWait();

        });


        // päänäkymä
        BorderPane paaNakyma = new BorderPane();
        //paaNakyma.setPrefSize(650, 350);

        // pelilauta ja kontrollit
        Kontrolleri kontrolleri = new Kontrolleri();
        paaNakyma.setCenter(kontrolleri.getPeliAlue());
        paaNakyma.setTop(menuBar);

        plarays.play();
        ikkuna.setScene(new Scene(paaNakyma));
        ikkuna.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
