package korttipakka;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;

import javafx.stage.Stage;



public class Kayttoliittyma extends Application {


    public void start(Stage ikkuna) {

        // valikot ja niiden toiminnallisuus
        MenuBar menuBar = new MenuBar();

        Menu menuPeli = new Menu("Peli");
        MenuItem uusiPeli = new MenuItem("Uusi peli");
        MenuItem lopetaPeli = new MenuItem("Lopeta");
        menuPeli.getItems().addAll(uusiPeli, lopetaPeli);

        Menu menuOhje = new Menu(("Ohje"));
        MenuItem peliOhje = new MenuItem("Peliohjeet");
        MenuItem peliTiedot = new MenuItem("Tiedot..");
        menuOhje.getItems().addAll(peliOhje, peliTiedot);

        menuBar.getMenus().addAll(menuPeli, menuOhje);

        uusiPeli.setOnAction(e -> {
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

        });

        peliTiedot.setOnAction(e -> {

        });


        // päänäkymä
        BorderPane paaNakyma = new BorderPane();
        //paaNakyma.setPrefSize(650, 350);

        // pelilauta ja kontrollit
        Kontrolleri kontrolleri = new Kontrolleri();
        paaNakyma.setCenter(kontrolleri.getPeliAlue());
        paaNakyma.setTop(menuBar);


        ikkuna.setScene(new Scene(paaNakyma));
        ikkuna.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
