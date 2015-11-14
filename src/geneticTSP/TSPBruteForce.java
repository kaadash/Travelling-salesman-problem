/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        int size = listaMiast.size();
        if(size == 0) {
//            System.out.println("Wyjście z rekurencji ");
            for (int i = 0; i < prefix.size() - 1; i++) {
                City city = prefix.get(i+1);
                currentPathValue += (int)prefix.get(i).distanceTo(city);
//                System.out.print(" -> " + currentPathValue);
            }
            minPathValue.add(currentPathValue);
//            if(currentPathValue < minPathValue) {
//                minPathValue = currentPathValue;
//            }
//            System.out.println(minPathValue);
//            minPathValues.add(currentPathValue);
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
//        System.out.println(minPathValue);
        return minPathValue;
    }
}