import java.util.Scanner;
import java.util.Random;

public class Main {
    public static int[] setup() {
        // This method sets up four random piles of sticks (from 4 to 8) in an array indexed from 0 to 3 and returns the array.
        Random r = new Random();
        int[] piles = new int[4];

        for (int c = 0; c < piles.length; c++) {
            piles[c] = r.nextInt(5) + 4;
        }

        return piles;
    }

    public static void Display(int[] P) {
        // Receives an array of integers representing piles of sticks and displays the number of sticks for each pile.
        for (int c = 1; c <= 4; c++) {
            System.out.print("Pile " + c + " = " + P[c - 1] + "\t");
        }
        System.out.println("");
    }

    public static void Player1turn(int[] P) {
        // This method prompts the user for a pile and the number of sticks they wish to remove.
        // It checks for valid input and updates the piles accordingly.
        Scanner in = new Scanner(System.in);
        int p = -1, s = -1;

        do {
            System.out.println("P1: Which pile do you want to remove from?");
            int tempP = in.nextInt();
            if (tempP < 1 || tempP > 4) {
                System.out.println("Enter a valid pile");
            } else if (Check(P[tempP - 1]) == false) {
                System.out.println("Choose a pile with sticks");
            } else {
                p = tempP;
            }
        } while (p == -1);

        do {
            System.out.println("P1: How many sticks do you want to remove?");
            int tempS = in.nextInt();
            if (tempS >= 1 && tempS <= P[p - 1]) {
                s = tempS;
            } else {
                System.out.println("Enter a valid number of sticks");
            }
        } while (s == -1);

        if (Check(P[p - 1], s) == true) {
            P[p - 1] = RemoveNumber(P[p - 1], s);
        }
    }

    // Other methods (Player2turn, Check, RemoveNumber, DetermineLoser) follow the same pattern of user input and processing.

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p1count = 0, p2count = 0;
        String replay;

        do {
            int[] piles = setup();

            do {
                Display(piles);
                Player1turn(piles);
                if (DetermineLoser(piles) == true) {
                    System.out.println("Player 2 wins! Congrats!");
                    p2count++;
                    break;
                }
                Display(piles);
                Player2turn(piles);
                if (DetermineLoser(piles) == true) {
                    System.out.println("Player 1 wins! Congrats!");
                    p1count++;
                    break;
                }
            } while (DetermineLoser(piles) == false);

            System.out.println("Do you want to play again?");
            replay = in.next();

        } while (replay.equals("yes") || replay.equals("Yes"));

        System.out.println("Player 1 won " + p1count + " games");
        System.out.println("Player 2 won " + p2count + " games");
    }
}