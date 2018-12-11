import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class ZgadywanieLiczb {
    public static void main(String[] args) {
        int numToGuess = 0;
        Random random = new Random();
        numToGuess = random.nextInt(101);

        //Making dot decimal separator
        Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);

        while (true) {
            System.out.println("Zgadnij liczbę");
            String numByUser = scan.nextLine();
            try {
                int numAsInt = Integer.parseInt(numByUser);
                if (numAsInt == numToGuess) {
                    System.out.println("Zgadłeś!");
                    break;
                } else if (numAsInt > numToGuess) {
                    System.out.println("Za dużo!");
                } else System.out.println("Za mało!");
            } catch (NumberFormatException ex1) {
                System.out.println("To nie jest liczba");
            }
        }

    }
}
