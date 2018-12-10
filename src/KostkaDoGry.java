import java.util.Random;
import java.util.StringTokenizer;

public class KostkaDoGry {
    public static void main(String[] args) {
        String[] dicePack = {"0d20-5", "-3d20+5", "d20+5", "d10", "-1d-20"};

        for (String diceExample : dicePack) {
            try {
                System.out.println(tossDice(diceExample));
            } catch (InvalidDiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int tossDice(String param) throws InvalidDiceException {
        String keepInitParam = new String(param);
        try {
            //Test 2d20-5
            param = param.replaceAll("d", " d");
            param = param.replaceAll("-", " -");
            param = param.replaceAll("\\+", " \\+");

            StringTokenizer st = new StringTokenizer(param, " +d");

            int[] outTab;

            if (st.countTokens() == 3) {
                //Case when dice is like xDy+z
                outTab = new int[3];
                for (int i = 0; i < 3; i++) {
                    outTab[i] = Integer.parseInt(st.nextToken());
                }

                return smallRandom(outTab[0], outTab[1], outTab[2]);
            } else if (st.countTokens() == 2) {
                //Case when dice is like Dy+z
                outTab = new int[2];
                for (int i = 0; i < 2; i++) {
                    outTab[i] = Integer.parseInt(st.nextToken());
                }

                return smallRandom(outTab[0], outTab[1]);
            } else if (st.countTokens() == 1) {
                //Case when dice is like Dy
                outTab = new int[1];
                outTab[0] = Integer.parseInt(st.nextToken());

                return smallRandom(outTab[0]);
            }
        } catch (Exception e) {
            throw new InvalidDiceException("Nieprawidłowy format kości do gry: " + keepInitParam);
        }
        return 0;
    }

    private static int smallRandom(int howMany, int diceSize, int modifier) throws InvalidDiceException {
        if (howMany < 1 || diceSize < 1) {
            throw new InvalidDiceException("");
        }
        Random random = new Random();
        return howMany * (random.nextInt(diceSize) + 1) + modifier;
    }

    private static int smallRandom(int diceSize, int modifier) throws InvalidDiceException {
        if (diceSize < 1) {
            throw new InvalidDiceException("");
        }
        Random random = new Random();
        return random.nextInt(diceSize) + 1 + modifier;
    }

    private static int smallRandom(int diceSize) throws InvalidDiceException {
        if (diceSize < 1) {
            throw new InvalidDiceException("");
        }
        Random random = new Random();
        return random.nextInt(diceSize) + 1;
    }
}