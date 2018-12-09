import java.util.Locale;
import java.util.Scanner;

public class ZgadywanieLiczb2 {
    public static void main(String[] args) {
        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ja zgadnę w max 10 próbach.");

        int min = 0;
        int max = 1000;
        while (true) {
            int guess = ((max - min) / 2) + min;
            System.out.println("Zgaduję: " + guess + ". Zgadłem?");

            if (collectAnswer().equals("tak")) {
                System.out.println("Wygrałem!");
                break;
            } else {
                System.out.println("Za dużo?");
                if (collectAnswer().equals("tak")) {
                    max = guess;
                } else {
                    System.out.println("Za mało?");
                    if (collectAnswer().equals("tak")) {
                        min = guess;
                    } else System.out.println("Nie oszukuj!");
                }
            }
        }
    }

    private static String collectAnswer() {
        //Making dot decimal separator
        Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
        String answer = scan.nextLine();

        while (!answer.toLowerCase().equals("tak") && !answer.toLowerCase().equals("nie")) {
            System.out.println("Przepraszam, rozumiem tylko odpowiedzi 'tak' oraz 'nie'.");
            answer = scan.nextLine();
        }
        return answer;
    }
}
