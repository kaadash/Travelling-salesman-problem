package geneticTSP;
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
        @Override
        public String toString(){
            return getX()+", "+getY();
        }
}
