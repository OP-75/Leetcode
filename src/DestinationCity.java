import java.util.*;

public class DestinationCity {
    public String destCity(List<List<String>> paths) {

        HashSet<String> cities = new HashSet<>();

        for (int i = 0; i < paths.size(); i++) {

            List<String> path = paths.get(i);

            String cityA = path.get(0);
            String cityB = path.get(1);

            // since there is a path outgoing from cityA dont add it to hash map
            cities.add(cityB);
        }

        // now remove every city with outgoing path
        for (int i = 0; i < paths.size(); i++) {

            List<String> path = paths.get(i);

            String cityA = path.get(0);

            // since there is a path outgoing from cityA dont add it to hash map
            cities.remove(cityA);
        }

        Iterator<String> it = cities.iterator();
        String city = null;
        if (it.hasNext()) {
            city = it.next();
        }

        return city;

    }

}
