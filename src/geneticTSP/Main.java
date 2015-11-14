package geneticTSP;

import com.sun.javafx.runtime.SystemProperties;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        int indicator = 100;

        ArrayList <City> cities = new ArrayList<City>();
        List <Button> buttons = new ArrayList<Button>();
        Label result = new Label("Wynik: ");
        Label initialDistance = new Label(": ");
        Label orderResult = new Label();
        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            String name = sb.append(i).toString();
            cities.add(new City(name));
            TourManager.addCity(cities.get(i));
            buttons.add(new Button(name));
            buttons.get(i).setTranslateX(cities.get(i).getX() + indicator);
            buttons.get(i).setTranslateY(cities.get(i).getY() + indicator);
        }
        TSPNearestNeighbour neighbourAlgorithm = new TSPNearestNeighbour(cities);
        String path = neighbourAlgorithm.findPath();

        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 300; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        result.setText("Wynik: " + pop.getFittest().getDistance());
        orderResult.setTranslateY(100);
        orderResult.setText("Kolejność: " + pop.getFittest());

        ArrayList<City> prefix = new ArrayList<City>();
        ArrayList<Integer> minPathValue = new ArrayList<Integer>();
        minPathValue = TSPBruteForce.permutation(prefix, cities, minPathValue);
        System.out.print("Brute min value:   " + Collections.min(minPathValue));
        root.getChildren().addAll(buttons);
        root.getChildren().add(result);
        root.getChildren().add(orderResult);
//        primaryStage.setTitle("Traveling Salesman Problem");
//        primaryStage.setScene(new Scene(root, 1000, 1000));
//        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
