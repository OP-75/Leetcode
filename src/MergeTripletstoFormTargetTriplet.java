public class MergeTripletstoFormTargetTriplet {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        return greedySolution(triplets, target);
    }

    private boolean greedySolution(int[][] triplets, int[] target){

        boolean[] canMakeTarget = new boolean[target.length];

        for (int[] triplet : triplets) {
            
            if (triplet[0]==target[0] && triplet[1]<=target[1] && triplet[2]<=target[2]) {
                canMakeTarget[0] = true;
            }
            if (triplet[1]==target[1] && triplet[0]<=target[0] && triplet[2]<=target[2]) {
                canMakeTarget[1] = true;
            }
            if (triplet[2]==target[2] && triplet[1]<=target[1] && triplet[0]<=target[0]) {
                canMakeTarget[2] = true;
            }

            if (canMakeTarget[0] && canMakeTarget[1] && canMakeTarget[2]) {
                return true;
            }

        }

        if (canMakeTarget[0] && canMakeTarget[1] && canMakeTarget[2]) {
            return true;
        }
        else{
            return false;
        }

    }
}
