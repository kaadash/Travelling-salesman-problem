package geneticTSP;

import java.util.ArrayList;

public class TSPBruteForce {

    public static ArrayList<City> cloneList(ArrayList<City> list) {
        ArrayList<City> clone = new ArrayList<City>(list.size());
        for(City item: list){
            clone.add(new City(item));
        }
        return clone;
    }

    public static Pair permutation(ArrayList<City> prefix, ArrayList<City> listaMiast, Pair pairs, int numberOfCities){

        Integer currentPathValue = 0;
        String path = "";
        int size = listaMiast.size();
        if(size == 0) {
            for (int i = 0; i < prefix.size() - 1; i++) {
                City city = prefix.get(i+1);
                currentPathValue += (int)prefix.get(i).distanceTo(city);
                path += " -> " + prefix.get(i).getName();

            }
            path += " -> " + prefix.get(numberOfCities-1).getName();
            City city = prefix.get(0);
            currentPathValue += (int)prefix.get(numberOfCities-1).distanceTo(city);
            pairs.addInteger(currentPathValue);
            pairs.addString(path);
        }
        else {
            for (int i = 0; i < size; i++) {
                ArrayList<City> listaMiastCopy = cloneList(listaMiast);
                City miastoDoDodania = listaMiastCopy.get(i);
                listaMiastCopy.remove(i);
                ArrayList<City> prefixClone = cloneList(prefix);
                prefixClone.add(miastoDoDodania);
                permutation(prefixClone, listaMiastCopy, pairs, numberOfCities);
            }
        }

        return pairs;
    }
}