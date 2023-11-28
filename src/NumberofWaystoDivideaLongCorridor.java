import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

// ! Solution - https://youtu.be/YOTjCd4Eyhc?si=ihHAR-LFjIVmgtGk

public class NumberofWaystoDivideaLongCorridor {

    final int mod = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        // return mySolution(corridor.toCharArray()); //! gives TLE on last 2 cases
        return hintSolution(corridor.toCharArray());
    }

    
    private int hintSolution(char[] arr) {
        int[][] memo = new int[arr.length][3];
       for (int[] r : memo) {
        Arrays.fill(r, -1);
       }

        return count(0, 0, arr, memo);
    }



    private int count(int index, int seats, char[] arr, int[][] memo) {
        
        //base case:
        if (index>=arr.length) {
            //seats can only take up vals 0,1,2
            if (seats<2) {
                return 0; //invalid section
            } 
            else{
                return 1; //valid section/way
            }
        }


        //memoization
        if (memo[index][seats]!=-1) {
            return memo[index][seats];
        }


        int ways = 0;
        if (seats==2) {
            
            if (arr[index]=='S') {
                ways = count(index, 0, arr, memo); //give up curr index
            }
            else {
                ways = (count(index, 0, arr, memo) + count(index+1, seats, arr, memo))%mod;
            }

        }
        else if(seats<2) {

            if (arr[index]=='S') {
                ways = count(index+1, seats+1, arr, memo); 
            }
            else{
                ways = count(index+1, seats, arr, memo); 
            }

        }
        else{
            throw new IllegalStateException("Number of seats is > 2");
        }


        memo[index][seats] = ways%mod;

        return ways%mod;


    }



    private int mySolution(char[] charArray) {
        // ! Gives TLE
        int[] memo = new int[charArray.length];
        Arrays.fill(memo, -1);
        return recurse(charArray, 0, memo) % mod;
    }

    private int recurse(char[] arr, int start, int[] memo) {

        if (start >= arr.length) {
            return 1;
        }

        if (memo[start] != -1) {
            return memo[start] % mod;
        }

        int seats = 0;
        int numberOfWays = 0;
        for (int i = start; i < arr.length; i++) {
            if (arr[i] == 'S') {
                seats++;
            }

            if (seats == 2) {
                numberOfWays = (numberOfWays + (recurse(arr, i + 1, memo) % mod)) % mod;
            }
            if (seats > 2) {
                break;
            }
        }

        memo[start] = numberOfWays % mod;

        return numberOfWays % mod;
    }

}
