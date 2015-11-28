package geneticTSP;

import com.sun.javafx.runtime.SystemProperties;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import sun.security.provider.SHA;

import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        ArrayList <City> cities = new ArrayList<City>();
        ArrayList <StackPane> buttons = new ArrayList<StackPane>();
        ArrayList <Label> labels = new ArrayList<Label>();

        int numberOfCity = 9;
        City.generateCitiesView(cities, buttons, numberOfCity, 200);

        long startNeighbour = System.currentTimeMillis();
        TSPNearestNeighbour neighbourAlgorithm = new TSPNearestNeighbour(cities);

        String path = neighbourAlgorithm.findPath();
        long stopNeighbour = System.currentTimeMillis();

        System.out.println("Algorytm najblizszego sasiada\n Ilość miast: " + numberOfCity + "\nCzas: " + (stopNeighbour-startNeighbour));

        long startGenet = System.currentTimeMillis();
        Population pop = new Population(50, true);
        int initialDistance = pop.getFittest().getDistance();

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 300; i++) {
            pop = GA.evolvePopulation(pop);
        }
        long stopGenet = System.currentTimeMillis();

        System.out.println("Algorytm genetyczny\n Ilość miast: " + numberOfCity + "\nCzas: " + (stopGenet-startGenet));
        ArrayList<City> prefix = new ArrayList<City>();
        Pair pair = new Pair();


        long startBrute = System.currentTimeMillis();
        pair = TSPBruteForce.permutation(prefix, cities, pair, numberOfCity);
        long stopBrute = System.currentTimeMillis();
        System.out.println("Algorytm brute force\n Ilość miast: " + numberOfCity + "\nCzas: " + (stopBrute - startBrute));

        Label initialDistanceLabel = new Label("Długość bazowa wynosi: " + initialDistance);
        Label geneticAlgorithmLength = new Label("Wynik dla genetycznego algorytmu: " + pop.getFittest().getDistance());
        Label bruteForcePathLength = new Label("Długość trasy dla metody brute force: " + (pair).getValueMinPath());
        Label nearestPathLength = new Label("Długość trasy dla metody najbliższego sąsiada: " + neighbourAlgorithm.getWholeDistance());
        Label orderResultOfGeneticAlgorithm = new Label("Kolejność dla genetycznego algorytmu: " + pop.getFittest());
        Label orderResultOfNearest = new Label("Kolejność dla algorytmu najbliższego sąsiada: " + path);
        Label bruteForcePath = new Label("Kolejność dla algorytmu brute force: " + pair.getMinPath());

        labels.add(initialDistanceLabel);
        labels.add(nearestPathLength);
        labels.add(geneticAlgorithmLength);
        labels.add(bruteForcePathLength);
        labels.add(orderResultOfGeneticAlgorithm);
        labels.add(orderResultOfNearest);
        labels.add(bruteForcePath);

        int step = 10;

        for (Label item : labels) {
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
        root.getChildren().add(bruteForcePath);

        primaryStage.setTitle("Traveling Salesman Problem");
        primaryStage.setScene(new Scene(root, 1200, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
