package geneticTSP;

import com.sun.javafx.runtime.SystemProperties;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        ArrayList <City> cities = new ArrayList<City>();
        ArrayList <Button> buttons = new ArrayList<Button>();
        ArrayList<Label> labels = new ArrayList<Label>();

        int numberOfCity = 10;

        City.generateCitiesView(cities, buttons, numberOfCity, 200);
        TSPNearestNeighbour neighbourAlgorithm = new TSPNearestNeighbour(cities);
        String path = neighbourAlgorithm.findPath();

        Population pop = new Population(50, true);
        int initialDistance = pop.getFittest().getDistance();

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 300; i++) {
            pop = GA.evolvePopulation(pop);
        }

        ArrayList<City> prefix = new ArrayList<City>();
        ArrayList<Integer> minPathValue = new ArrayList<Integer>();
        minPathValue = TSPBruteForce.permutation(prefix, cities, minPathValue);

        Label initialDistanceLabel = new Label("Długość bazowa wynosi: " + initialDistance);
        Label geneticAlgorithmLength = new Label("Wynik dla genetycznego algorytmu: " + pop.getFittest().getDistance());
        Label bruteForcePathLength = new Label("Długość trasy dla metody brute force: " + Collections.min(minPathValue));
        Label nearestPathLength = new Label("Długość trasy dla metody najbliższego sąsiada: " + neighbourAlgorithm.getWholeDistance());
        Label orderResultOfGeneticAlgorithm = new Label("Kolejność dla genetycznego algorytmu: " + pop.getFittest());
        Label orderResultOfNearest = new Label("Kolejność dla algorytmu najbliższego sąsiada: " + path);

        labels.add(initialDistanceLabel);
        labels.add(nearestPathLength);
        labels.add(geneticAlgorithmLength);
        labels.add(bruteForcePathLength);
        labels.add(orderResultOfGeneticAlgorithm);
        labels.add(orderResultOfNearest);

        int step = 0;

        for (Label item: labels) {
            item.setTranslateY(step);
            step += 25;
        }

        root.getChildren().addAll(buttons);
        root.getChildren().add(initialDistanceLabel);
        root.getChildren().add(geneticAlgorithmLength);
        root.getChildren().add(bruteForcePathLength);
        root.getChildren().add(orderResultOfGeneticAlgorithm);
        root.getChildren().add(orderResultOfNearest);
        root.getChildren().add(nearestPathLength);

        primaryStage.setTitle("Traveling Salesman Problem");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
