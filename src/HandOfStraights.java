import java.util.*;

public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // return mySolution(hand, groupSize);
        return neetcodeSolution(hand, groupSize);
    }

    private void addToHM(int card, Map<Integer, Integer> map){

        if (map.containsKey(card)) {
            map.put(card, map.get(card) + 1);
        }
        else{
            map.put(card, 1);
        }

    }
    private void removeFromHM(int card, Map<Integer, Integer> map){

        if (map.containsKey(card)) {
            int cardCount = map.get(card);
            if (cardCount==1) {
                map.remove(card);
            } else {
                map.put(card, cardCount-1);
            }
        }
        else{
            throw new IllegalAccessError("Hashmap doesnt contain the card: "+card);
        }

    }

    private boolean mySolution(int[] hand, int groupSize) {
    
        if (groupSize==1) {
            return true;
        }
        if(hand.length%groupSize!=0){
            return false;
        }

        HashMap<Integer, Integer> cardCountMap = new HashMap<>();

        for (int card : hand) {
            addToHM(card, cardCountMap);
        }


        int size = hand.length;

        while (size>0) {
            
            HashSet<Integer> cards = new HashSet<>(cardCountMap.keySet());

            int startCard = -1;

            for (Integer i : cards) {
                if (cards.contains(i-1)) {
                    continue;
                }
                else{
                    startCard = i;
                    break;
                }
            }
            
            //if startcard is 1 & grpsize = 3 then grp will be [1,2,3] ie last card = startcard + grpSize - 1 = 1 + 3 - 1 = 3
            boolean isGroupCompleted = true;
            for (int i = startCard; i <= (startCard+groupSize)-1; i++) { 
                if(cardCountMap.containsKey(i)){
                    removeFromHM(i, cardCountMap);
                }
                else{
                    isGroupCompleted = false;
                    return false;
                }
            }

            if (!isGroupCompleted) {
                break;
            }
            else{
                size = size - groupSize;
            }
        }



        return cardCountMap.isEmpty(); //if it is empty then is N straight hand


    }
    private boolean neetcodeSolution(int[] hand, int groupSize) {
    
        if (groupSize==1) {
            return true;
        }
        if(hand.length%groupSize!=0){
            return false;
        }

        TreeMap<Integer, Integer> cardCountMap = new TreeMap<Integer, Integer>();

        for (int card : hand) {
            addToHM(card, cardCountMap);
        }


        int size = hand.length;

        while (size>0) {
            

            int startCard = cardCountMap.firstKey();
            
            //if startcard is 1 & grpsize = 3 then grp will be [1,2,3] ie last card = startcard + grpSize - 1 = 1 + 3 - 1 = 3
            boolean isGroupCompleted = true;
            for (int i = startCard; i <= (startCard+groupSize)-1; i++) { 
                if(cardCountMap.containsKey(i)){
                    removeFromHM(i, cardCountMap);
                }
                else{
                    isGroupCompleted = false;
                    return false;
                }
            }

            if (!isGroupCompleted) {
                break;
            }
            else{
                size = size - groupSize;
            }
        }



        return cardCountMap.isEmpty(); //if it is empty then is N straight hand


    }

    
}
