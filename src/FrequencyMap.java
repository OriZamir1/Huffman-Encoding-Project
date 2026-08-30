import java.util.HashMap;


public class FrequencyMap {
    public static HashMap<Character, Integer> buildFrequencyMap(String text) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i<text.length();i++){
            char c = text.charAt(i);
            if (map.containsKey(c)){
                map.put(c,map.get(c) +1);
            }else{
                map.put(c,1);
            }
        }
        return map;
    }
}
