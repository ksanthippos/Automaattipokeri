package korttipakka;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.stage.Stage;



public class Kayttoliittyma extends Application {


    public void start(Stage ikkuna) {

        // päänäkymä
        BorderPane paaNakyma = new BorderPane();
        paaNakyma.setPrefSize(650, 350);

        // pelilauta ja kontrollit
        Kontrolleri kontrolleri = new Kontrolleri();
        paaNakyma.setCenter(kontrolleri.getPeliAlue());


        ikkuna.setScene(new Scene(paaNakyma));
        ikkuna.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
