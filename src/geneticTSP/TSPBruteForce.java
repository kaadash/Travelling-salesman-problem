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

    public static ArrayList<Integer> permutation(ArrayList<City> prefix, ArrayList<City> listaMiast, ArrayList<Integer> minPathValue){
        Integer currentPathValue = 0;
        //String currentPath = "";
        int size = listaMiast.size();
        if(size == 0) {
            for (int i = 0; i < prefix.size() - 1; i++) {
                City city = prefix.get(i+1);
                currentPathValue += (int)prefix.get(i).distanceTo(city);
                //currentPath += (String)prefix.get(i);
            }
            minPathValue.add(currentPathValue);
            //paths.add(currentPath);
        }
        else {
            for (int i = 0; i < size; i++) {
                ArrayList<City> listaMiastCopy = cloneList(listaMiast);
                City miastoDoDodania = listaMiastCopy.get(i);
                listaMiastCopy.remove(i);
                ArrayList<City> prefixClone = cloneList(prefix);
                prefixClone.add(miastoDoDodania);
                permutation(prefixClone, listaMiastCopy, minPathValue);
            }
        }
        return minPathValue;
    }
}