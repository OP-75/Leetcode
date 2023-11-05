import java.util.Arrays;
import java.util.LinkedList;

public class FindTheWinnerOfAnArrayGame {
    public int getWinner(int[] arr, int k) {
        //use LinkedList Datastructure
        return mySolution(arr, k);
    }

    private int mySolution(int[] arr, int k) {

        if (k>=arr.length) {
            int max = arr[0];
            for (int i : arr) {
                max = i>max? i:max;
            }
            return max;
        }

        LinkedList<Integer> myLL = new LinkedList<>();
        for (int i : arr) {
            myLL.add(i);
        }

        int winnerCount = 0, winner = -1;

        while (winnerCount<k) {
            int first = myLL.getFirst();
            int second = myLL.get(1);

            int currWinner = getWinnerBetweenTwo(first, second, myLL);

            if (currWinner!=winner) {
                winner = currWinner;
                winnerCount = 1;
            }
            else{
                winnerCount++;
            }
        }

        return winner;
    }

    private int getWinnerBetweenTwo(int first, int second, LinkedList<Integer> myLL) {
        if (first>second) {
            
            myLL.remove(1); //ie remove second
            myLL.addLast(second);

            return first;
        } else {
            
            myLL.removeFirst();
            myLL.addLast(first);

            return second;
        }
    }
}
