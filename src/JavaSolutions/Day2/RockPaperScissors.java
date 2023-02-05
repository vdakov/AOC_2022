package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("resources\\gameRPS.txt"));
        HashMap<String, String> map = new HashMap<>();
        map.put("X", "A");
        map.put("Y", "B");
        map.put("Z", "C");
        HashMap<String, Integer> scores = new HashMap<>();
        HashMap<String, String> win = new HashMap<>();
        HashMap<String, String> lose = new HashMap<>();
        scores.put("A", 1);
        scores.put("B", 2);
        scores.put("C", 3);
        win.put("C", "A");
        win.put("A", "B");
        win.put("B", "C");
        lose.put("A", "C");
        lose.put("B", "A");
        lose.put("C", "B");
        int sum = 0;
        int sum2 = 0;
        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine());
            line.useDelimiter(" |\n");
            String a = line.next();
            String player = line.next();
            String b = map.get(player);

            sum += scores.get(b);
            sum += play(a, b);

            b = play2(a, player, win, lose);
            sum2 += scores.get(b);
            sum2 += play(a, b);
        }

    }

    public static int play(String opp, String player) {
        if (opp.equals(player)) return 3;
        if (opp.equals("A") && player.equals("C") || opp.equals("C") && player.equals("B")
                || opp.equals("B") && player.equals("A")) return 0;
        return 6;
    }

    public static String play2(String opp, String player, HashMap<String, String> win, HashMap<String, String> lose) {
        if (player.equals("X")) return lose.get(opp);
        if (player.equals("Z")) return win.get(opp);
        return opp;
    }
}
