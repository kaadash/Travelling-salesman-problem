package geneticTSP;

import javafx.scene.control.Button;

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

        public static void generateCitiesView(ArrayList<City> cities, ArrayList <Button> buttons, int number, int translate) {
            for (int i = 0; i < number; i++) {
                StringBuilder sb = new StringBuilder();
                String name = sb.append(i).toString();
                cities.add(new City(name));
                TourManager.addCity(cities.get(i));
                buttons.add(new Button(name));
                buttons.get(i).setTranslateX(cities.get(i).getX() + translate);
                buttons.get(i).setTranslateY(cities.get(i).getY() + translate);
            }
        }
        @Override
        public String toString(){
            return getX()+", "+getY();
        }
}
