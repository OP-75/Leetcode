import java.util.ArrayList;
import java.util.HashMap;

class TimeMap {

    HashMap<Integer, HashMap<String, String>> timeKeyMap = null;
    // stores time - {hm of 2 strs}

    ArrayList<Integer> timestampsList = null;
    // stores time stamps, will always be in acending order since timestmaps are
    // always increasing

    public TimeMap() {
        timeKeyMap = new HashMap<>();
        timestampsList = new ArrayList<>();
    }

    public void set(String key, String value, int timestamp) {
        if (timeKeyMap.containsKey(timestamp)) {
            HashMap<String, String> hm = timeKeyMap.get(timestamp);
            hm.put(key, value);
        } else {

            HashMap<String, String> hm = new HashMap<>();

            if (!timestampsList.isEmpty()) {
                int preTimestamp = timestampsList.get(timestampsList.size() - 1);
                HashMap<String, String> preHm = timeKeyMap.get(preTimestamp);
                hm.putAll(preHm);
                // the hm at at time i+1 countains all the mapping at time i too, and so on
            }

            hm.put(key, value); // if some value occurs at time i & that same val occurs at time i+1 the value
                                // at time i+1 will be over written
            timeKeyMap.put(timestamp, hm);
        }
        timestampsList.add(timestamp);
    }

    public String get(String key, int timestamp) {

        String value = "";

        // do binary search on timestampsList
        int down = 0, up = timestampsList.size() - 1;
        int mid;
        int size = timestampsList.size();
        while (up >= 0 && down <= size - 1 && up >= down) {

            mid = (up + down) / 2;

            // check if mid is the biggest timestamp that is <=timestamp
            if (timestampsList.get(mid) <= timestamp
                    && (mid + 1 > size - 1 || timestampsList.get(mid + 1) > timestamp)) {
                HashMap<String, String> hm = timeKeyMap.get(timestampsList.get(mid));
                if (hm.containsKey(key)) {
                    value = hm.get(key);
                    return value;
                }
                else{
                    value = "";
                    return value;
                }
            } else if (timestampsList.get(mid) > timestamp) {
                up = mid - 1;
            } else if (timestampsList.get(mid) <= timestamp) { // ie mid_timestamp is <= timestamp but its not the
                                                               // biggest prev_timestamp <= given timestamp
                down = mid + 1;
            }

        }

        return value;

    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

public class TimeBasedKeyValueStore {

    public static void main(String[] args) {

        TimeMap timeMap = new TimeMap();
        timeMap.set("a","bar",1);
        timeMap.set("x","b",3);
        System.out.println(timeMap.get("b", 3));
        timeMap.set("foo","bar2",4);
        System.out.println(timeMap.get("foo",4));
        System.out.println(timeMap.get("foo",5));



    }

}
