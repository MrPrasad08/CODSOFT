import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1,max = 100,maxChances = 7,totalRounds = 0,roundsWon = 0;
        boolean playagain = true;

        // Introduction
        System.out.println("===============================");
        System.out.println("   WELCOME TO NUMBER GAME ");
        System.out.println("===============================");
        System.out.println("Guess the secret number between " + min + " and " + max + ".");
        System.out.println("You have " + maxChances + " tries each round.");

        while (playagain) {
            totalRounds++;
            int correctNumber = random.nextInt(max - min + 1) + min, attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + totalRounds + " ---");

            while (attempts < maxChances) {
                System.out.print("Enter your guess (" + min + " - " + max + "): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("That's not a number. Try again.");
                    scanner.next(); // clear invalid input
                    continue;
                }

                int guess = scanner.nextInt();
                attempts++;

                if (guess == correctNumber) {
                    System.out.println("Correct!!! You guessed the number!");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < correctNumber) {
                    System.out.println("Your guess is too low. Try a bigger number.");
                } else {
                    System.out.println("Your guess is too high. Try a smaller number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You didn't guess it. The correct number was: " + correctNumber);
            }

            System.out.println("Score: " + roundsWon + " out of " + totalRounds + " rounds.");

            scanner.nextLine(); // clear newline
            System.out.print("Do you want to play another round? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (!answer.equals("yes")) {
                playagain = false;
            }
        }

        System.out.println("\nThanks for playing the Number Game!");
        scanner.close();
    }
}
