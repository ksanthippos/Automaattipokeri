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

        // päänäkymä
        BorderPane paaNakyma = new BorderPane();
        paaNakyma.setPrefSize(650, 300);

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
