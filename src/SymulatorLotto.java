import java.util.*;

public class SymulatorLotto {
    public static void main(String[] args) {
        //Creating table with lotto numbers
        ArrayList<Integer> numbersFrom1To49 = new ArrayList<Integer>();
        for (int fill = 1; fill < 50; fill++) {
            numbersFrom1To49.add(fill);
        }
        int[] lottoTab = new int[6];
        Random random = new Random();
        for (int i = 0; i < lottoTab.length; i++) {
            int tmpInt = random.nextInt(numbersFrom1To49.size());
            lottoTab[i] = numbersFrom1To49.get(tmpInt);
            numbersFrom1To49.remove(tmpInt);
        }
        Arrays.sort(lottoTab);

        //Reading numbers from user
        int[] userNums = new int[6];
        int counter = 0;

        //Making dot decimal separator
        Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
        while (counter < 6) {
            System.out.println("Podaj dodatnią liczbę z zakresu 1-49");
            String numByUser = scan.nextLine();
            try {
                int numAsInt = Integer.parseInt(numByUser);
                if (numAsInt > 49 || numAsInt < 1) {
                    throw new TooBigException("Wybrano liczbę spoza zakresu.");
                }
                boolean alreadyExist = false;
                for (int tabElem : userNums) {
                    if (numAsInt == tabElem) {
                        System.out.println("Liczba jest już podana, podaj inną.");
                        alreadyExist = true;
                    }
                }

                if (!alreadyExist) {
                    userNums[counter] = numAsInt;
                    counter++;
                }
            } catch (NumberFormatException ex1) {
                System.out.println("To nie jest liczba");
            } catch (TooBigException ex2) {
                System.out.println(ex2.getMessage());
            }
        }

        Arrays.sort(userNums);
        System.out.println("Wytypowano następujące liczby: " + Arrays.toString(userNums));
        System.out.println("W losowaniu padły następujące liczby: " + Arrays.toString(lottoTab));

        int numOfMatches = 0;
        for (int userNum : userNums) {
            for (int typedNum : lottoTab) {
                if (userNum == typedNum) numOfMatches++;
            }
        }
        if (numOfMatches > 2)
            System.out.println("Gratulacje! Padło " + numOfMatches + " trafień!");
        else
            System.out.println("Nie zwalniaj sie z pracy, masz mało trafień :(");
    }
}

class TooBigException extends Exception {
    public TooBigException(String message) {
        super(message);
    }
}