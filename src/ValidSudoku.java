import java.util.ArrayList;
import java.util.HashSet;

public class ValidSudoku {

    private static boolean mySolution(char[][] board) {

        boolean isValid = true;

        // check rows
        ArrayList<Character> al = new ArrayList<Character>(9);
        HashSet<Character> set = new HashSet<Character>(9);
        for (char[] row : board) {

            al.clear();
            set.clear();
            for (int i = 0; i < row.length; i++) {
                if (row[i] == '.') {
                    continue;
                }

                al.add(row[i]);
                set.add(row[i]);

                if (set.size() != al.size()) {
                    isValid = false;
                    return isValid;
                }
            }
        }

        // check columns
        for (int c = 0; c < board[0].length; c++) {

            al.clear();
            set.clear();
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] == '.') {
                    continue;
                }

                al.add(board[r][c]);
                set.add(board[r][c]);

                if (set.size() != al.size()) {
                    isValid = false;
                    return isValid;
                }
            }
        }

        // check 3x3 sections
        for (int toAddRow = 0; toAddRow < board.length; toAddRow += 3) {
            for (int toAddCol = 0; toAddCol < board.length; toAddCol += 3) {

                al.clear();
                set.clear();
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (board[r + toAddRow][c + toAddCol] == '.') {
                            continue;
                        }

                        al.add(board[r + toAddRow][c + toAddCol]);
                        set.add(board[r + toAddRow][c + toAddCol]);

                        if (set.size() != al.size()) {
                            isValid = false;
                            return isValid;
                        }

                    }
                }

            }
        }

        return isValid;
    }

    public static void main(String[] args) {

        char[][] board;

        board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        System.out.println(mySolution(board));

        board = new char[][] { { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        System.out.println(mySolution(board));
    }

}