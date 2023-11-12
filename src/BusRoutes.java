import java.util.*;

public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source==target) {
            return 0;
        }

        HashMap<Integer, List<Integer>> routeBusMap = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                addToMap(routeBusMap, routes[i][j], i);
            }
        }

        Queue<Integer> busesQ = new LinkedList<>();
        boolean[] isBusVisted = new boolean[routes.length];
        
        //add all the buses that pass thru "source"
        List<Integer> busesThatPassThruSource = routeBusMap.get(source);
        for (Integer tmpBus : busesThatPassThruSource) {
            busesQ.add(tmpBus);
            isBusVisted[tmpBus] = true;
        }

        //now use Level order traversal-type solution to visit every bust that comes at the stop source

        int depth = 1;
        while (!busesQ.isEmpty()) {
            
            
            int qInitialSize = busesQ.size();

            for (int tmp1 = 0; tmp1 < qInitialSize; tmp1++) {

                int bus = busesQ.poll();
                isBusVisted[bus] = true;

                for (int i = 0; i < routes[bus].length; i++) {
                    if (routes[bus][i]==target) {
                        return depth;
                    }

                    List<Integer> busesThatPassThruThisRoute = routeBusMap.get(routes[bus][i]);

                    for (Integer busOnThisRoute : busesThatPassThruThisRoute) {
                        if (!isBusVisted[busOnThisRoute]) {
                            busesQ.add(busOnThisRoute);
                            isBusVisted[busOnThisRoute] = true;
                        }
                    }
                    
                }
            }

            depth++;
        }

        


        return -1;
    }

    private void addToMap(HashMap<Integer,List<Integer>> routeBusMap, int key, int bus){

        if (routeBusMap.containsKey(key)) {
            routeBusMap.get(key).add(bus);
        } else {
            List<Integer> busesList = new ArrayList<>();
            busesList.add(bus);
            routeBusMap.put(key, busesList);
        }

    }

}


