// ! Incomplete/Not working
public class MinimumNumberOfOperationsToMakeXAndYEqual {

    public int minimumOperationsToMakeEqual(int x, int y) {

        if (x > y) {
            return reduceX(x, y, true);
        } else {
            return y - x;
        }

    }

    private int reduceX(int x, int y, boolean canIncrement) {

        if (x <= y) {
            return y - x;
        }

        int decrementOps = Integer.MAX_VALUE, incrementOps = Integer.MAX_VALUE;

        for (int i = 0; i <= 11; i++) {
            // decrementing here
            if ((x - i) == y) {
                decrementOps = Math.min(decrementOps, i);
            }
            if ((x - i) % 5 == 0) {
                decrementOps = Math.min(decrementOps, i + 1 + reduceX((x - i) / 5, y, false));
            }
            if ((x - i) % 11 == 0) {
                decrementOps = Math.min(decrementOps, i + 1 + reduceX((x - i) / 11, y, false));
            }
        }

        if (true) {
            for (int i = 0; i <= 11; i++) {
                // incrementing here
                if ((x + i) % 5 == 0) {
                    incrementOps = Math.min(incrementOps, i + 1 + reduceX((x + i) / 5, y, false));
                }
                if ((x + i) % 11 == 0) {
                    incrementOps = Math.min(incrementOps, i + 1 + reduceX((x + i) / 11, y, false));
                }
            }
        }

        int minOps = Math.min(incrementOps, decrementOps);
        return minOps;

    }

    public static void main(String[] args) {
        MinimumNumberOfOperationsToMakeXAndYEqual o = new MinimumNumberOfOperationsToMakeXAndYEqual();
        System.out.println(o.minimumOperationsToMakeEqual(18, 1));
    }
}
