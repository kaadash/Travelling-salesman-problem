package geneticTSP;

import java.util.ArrayList;

/**
 * Created by komputerszatana on 27.11.15.
 */
public class Pair {
    private ArrayList<Integer> integersArrayList = new ArrayList<>();
    private ArrayList<String> stringsArrayList = new ArrayList<>();
    void addInteger(Integer integer) {
        integersArrayList.add(integer);
    }
    void addString(String string) {
        stringsArrayList.add(string);
    }
    Integer getValueMinPath() {
        Integer min = Integer.MAX_VALUE;
        for(int i = 0; i < integersArrayList.size(); i++) {
            if(integersArrayList.get(i) < min) min = integersArrayList.get(i);
        }
        return min;
    }
    String getMinPath() {
        Integer min = Integer.MAX_VALUE;
        int number = 0;
        for(int i = 0; i < integersArrayList.size(); i++) {
            if(integersArrayList.get(i) < min) {
                min = integersArrayList.get(i);
                number = i;
            }
        }
        return stringsArrayList.get(number).substring(3);
    }

}