package geneticTSP;

import java.util.ArrayList;

/**
 * Created by kadash on 04.11.15.
 */
public class TourManager {
    private static ArrayList destinationCities = new ArrayList<City>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}
