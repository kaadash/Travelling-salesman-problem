package geneticTSP;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class TSPNearestNeighbour
{
    private int numberOfCities;
    private ArrayList<City> citiesList = new ArrayList<City>();
    private String path;
    private int wholeDistance;
    public TSPNearestNeighbour(ArrayList<City> citiesList ){
        this.citiesList = citiesList;
        this.wholeDistance = 0;
    }
    public String findPath () {
        ArrayList <City> visited = new ArrayList<City>();
        int min = Integer.MAX_VALUE;
        int numberOfCities = this.citiesList.size();
        visited.add(citiesList.get(0));
        citiesList.get(0).setVisited(true);
        while(visited.size() != numberOfCities) {
            int i = 0;
            int currentCityIndex = visited.size() - 1;
            int indexOfMinDist = -1;
            min = Integer.MAX_VALUE;
            City minPathCity = new City();
            while(i < numberOfCities) {
                double distance = visited.get(currentCityIndex).distanceTo(citiesList.get(i));
                if( distance > 0 && !citiesList.get(i).isVisited()) {
                    if(min > distance){
                        min = (int)distance;
                        minPathCity = citiesList.get(i);
                        indexOfMinDist = i;
                    }
                }
                i++;
            }
            citiesList.get(indexOfMinDist).setVisited(true);
            visited.add(minPathCity);
            this.wholeDistance += min;
        }

        for (int j = 0; j < visited.size(); j++) {
            this.path += " -> " + visited.get(j).getName();
        }
        return this.path;
    }

    public int getWholeDistance() {
        return wholeDistance;
    }
}
