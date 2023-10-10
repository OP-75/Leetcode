public class BestTimetoBuyandSellStockwithCooldown {

    public int maxProfit(int[] prices) {
        // return mySolution(prices);
        return striverSolution(prices, 0, true, new int[2][prices.length]);
    }


    private int striverSolution(int[] prices, int i, boolean canBuy, int[][] memo){

        if (i>=prices.length) {
            return 0;
        }

        if (canBuy &&  memo[1][i]!=0) {
            return  memo[1][i];
        }
        else if(!canBuy && memo[0][i]!=0){
            return memo[0][i];
        }

        if (canBuy) {
            //now u have 2 choices go to nxt day & decide or buy now (when u buy u are taking money out from profit)
            int currMaxProfit = Math.max(striverSolution(prices, i+1, true, memo), -1*prices[i]+striverSolution(prices, i+1, false, memo));
            memo[1][i] = currMaxProfit; //if canBuy is true then put ans in 1st array
            return currMaxProfit;
        }
        else{
            //now u have 2 choices go to nxt day & decide or sell now 
            int currMaxProfit = Math.max(striverSolution(prices, i+1, false, memo), prices[i]+striverSolution(prices, i+2, true, memo));
            memo[0][i] = currMaxProfit; //if canBuy is false then put ans in 0th array
            return currMaxProfit;
        }

    }

    
    private int mySolution(int[] prices){
        int[][] memo = new int[prices.length][prices.length+1];
        return recursiveDP(prices, 0, -1, memo);
    }

    
    private int recursiveDP(int[] prices, int i, int prevBuyPriceIndx, int[][] memo){

        if (i>=prices.length) {
            return 0;
        }

        if (prevBuyPriceIndx!=-1 && memo[i][prevBuyPriceIndx]!=0) {
            return memo[i][prevBuyPriceIndx];
        }
        if (prevBuyPriceIndx==-1 && memo[i][memo[i].length-1]!=0) {
            //when priceIndx = -1 the profit is stored at the last indx of memo[i]
            return memo[i][memo[i].length-1];
        }

        if (prevBuyPriceIndx!=-1) {
            //either hold or sell now
            int ans =  Math.max(recursiveDP(prices, i+1, prevBuyPriceIndx, memo), (prices[i] - prices[prevBuyPriceIndx]) + recursiveDP(prices, i+2, -1, memo));
            memo[i][prevBuyPriceIndx] = ans;
            return ans;
        }
        if (prevBuyPriceIndx==-1) {
            int max = 0;
            
            //either buy now or go to nxt stock & decide
            max = Math.max(recursiveDP(prices, i+1, -1, memo), recursiveDP(prices, i+1, i, memo));
            
            memo[i][memo[i].length-1] = max;
            return max;
        }

        return 0;

    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithCooldown o = new BestTimetoBuyandSellStockwithCooldown();
        System.out.println(o.maxProfit(new int[]{1,2,4}));
        System.out.println(o.maxProfit(new int[]{2,1,4}));
        System.out.println(o.maxProfit(new int[]{1,2,3,0,2}));
    }

    
    

}
