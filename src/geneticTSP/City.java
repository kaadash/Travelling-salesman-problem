package geneticTSP;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.ArrayList;

public class City {
    private int x;
    private int y;
    private boolean visited;
    private String name;

    // Constructs a randomly placed city
    public City(){
        this.x = (int)(Math.random()*500);
        this.y = (int)(Math.random()*500);
        this.visited = false;
    }

    public City(String name){
        this.x = (int)(Math.random()*500);
        this.y = (int)(Math.random()*500);
        this.name = name;
        this.visited = false;
    }

    public City(City city) {
        this.x = city.getX();
        this.y = city.getY();
        this.name = city.getName();
        this.visited = city.isVisited();
    }

    // Constructs a city at chosen x, y location
    public City(int x, int y){
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }

    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }

    // Gets the distance to given city
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

        return distance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean isVisited) {
        this.visited = isVisited;
    }

    public static void generateCitiesView(ArrayList<City> cities, ArrayList <StackPane> buttons, int number, int translate) {
        for (int i = 0; i < number; i++) {
            StringBuilder sb = new StringBuilder();
            String name = sb.append(i).toString();
            cities.add(new City(name));
            Text text = new Text(name + " (" + cities.get(i).toString() + ")");
            text.setFill(Color.WHITE);
            text.setBoundsType(TextBoundsType.VISUAL);
            Sphere circle = new Sphere(40, 40);
            final PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.web("#444444"));
            circle.setMaterial(material);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(circle, text);
            TourManager.addCity(cities.get(i));
            buttons.add(stackPane);
            buttons.get(i).setTranslateX(cities.get(i).getX() + translate);
            buttons.get(i).setTranslateY(cities.get(i).getY() + translate);
        }
    }
    public static void generateCitiesPaths(ArrayList<City> cities, ArrayList<StackPane> buttons, ArrayList<Integer> minPathValue) {

    }
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}

