package CodSoft;

import java.util.Random;
import java.util.Scanner;

public class Task_1{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(100) + 1; // random number b/w 1 and 100
        int guess = 0;
        int attempts = 0;
        int maxAttempts = 5; // limit for attempts

        
        while (guess != number && attempts < maxAttempts) {
            System.out.println("Guess the number between 1 and 100: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < number) {
                System.out.println("Too low!");
            } else if (guess > number) {
                System.out.println("Too high!");
            } else {
                System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
            }
        }

        if (guess != number) {
            System.out.println("You've used all attempts.\nThe correct number was: " + number);
        }

        scanner.close();
    }
}


