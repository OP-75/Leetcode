import java.util.Stack;
import java.util.TreeMap;

class Car{
    int pos;
    int speed;
    double ttt; //ttt = time to reach target

    Car(int pos, int speed, double ttt){
        this.pos = pos;
        this.speed =speed;
        this.ttt = ttt;
    }
}

class Solution {

    private void sortBothArr(int[] position, int[] speed){
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < position.length; i++) {
            map.put(position[i], speed[i]);
        }

        int i = 0;
        for (int key_pos : map.keySet()) {
            int speed_val = map.get(key_pos);
            position[i] = key_pos;
            speed[i] = speed_val;
            i++;
        }
    }

    private int mySolution(int target, int[] position, int[] speed) {
        
        int ans = 0;
        int n = position.length;

        Stack<Car> st = new Stack<>();

        //sorting both position and speed arr according to position array
        sortBothArr(position, speed);

        for (int i = 0; i < n; i++) {

            Car currCar = new Car(position[i], speed[i], (double)(target-position[i])/(double)speed[i]);

            if (st.isEmpty()) {
                st.push(currCar);
                continue;
            }

            Car topCar = st.peek();
            
            if (topCar.ttt <= currCar.ttt) { //ie top car is faster and behind curr car
                while (!st.isEmpty() && currCar.pos > topCar.pos && topCar.ttt <= currCar.ttt) {
                    st.pop();
                    if (st.isEmpty()) {
                        break;
                    }
                    topCar = st.peek();
                }
                
            }

            st.push(currCar);  //put the curr car ie slower car in stack

             
        }
        
        ans = st.size();
        return ans;
    }

    private int neetcodeSolution(int target, int[] position, int[] speed){
        
        int ans = 0;
        int n = position.length;

        Stack<Car> st = new Stack<>();

        //sorting both position and speed arr according to position array
        sortBothArr(position, speed);

        for (int i = n-1; i >=0 ; i--) {

            Car currCar = new Car(position[i], speed[i], (double)(target-position[i])/(double)speed[i]);

            if (st.isEmpty()) {
                st.push(currCar);
                continue;
            }

            Car topCar = st.peek();
            
            if (currCar.ttt <= topCar.ttt) { 
                continue; //ie curr car is faster ie it will collide with top car and become fleet that travels with speed = topCar.speed
            }

            st.push(currCar);  //put the curr car ie slower car in stack

             
        }
        
        ans = st.size();
        return ans;
    }







    public int carFleet(int target, int[] position, int[] speed){
        // return mySolution(target, position, speed);
        return neetcodeSolution(target, position, speed);
    }
}


public class CarFleet {
    public static void main(String[] args) {
        Solution o = new Solution();

        System.out.println(o.carFleet(16, new int[]{11,14,13,6}, new int[]{2,2,6,7}));
        System.out.println(o.carFleet(12, new int[]{10,8,0,5,3}, new int[]{2,4,1,1,3}));
    }    
}
