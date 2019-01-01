package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tusable {

    public static void main(String[] args) {

        System.out.println(Tusable.createHmap(5));
    }


    public static Map<String, List> createHmap(int n){
        Map<String, List> hmap = new HashMap<String, List>();
        List<String> list;

        for (int i = 0; i < n-2; i++) {
            list = new ArrayList<String>();
            for (int j = 0; j < n ; j++) {
                list.add(0, randomStr(n));
            }
            hmap.put(randomStr(n-2).toUpperCase(), list);
        }

        return hmap;
    }

    public static String randomStr(int n){
        String str = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < n; i++) {
            str += randomUpperLower(alphabet.charAt((int)(Math.random()*(alphabet.length())) ));
        }

        return str;

    }

    public static String randomUpperLower(char c){
        return String.valueOf(((int)(Math.random()*2)) == 1 ? Character.toUpperCase(c) : c );
    }




}
