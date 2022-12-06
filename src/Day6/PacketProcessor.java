package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacketProcessor {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("resources\\packetProcessor.txt"));
        solve(input, 4);
        input = new Scanner(new File("resources\\packetProcessor.txt"));
        solve(input, 14);
    }

    public static void solve(Scanner input, int len) {
        List<Character> c = new ArrayList<>();
        char[] arr = input.next().toCharArray();
        int i = 1;
        for (char a : arr) {
            if (c.contains(a)) {
                int k = c.indexOf(a);
                for (int j = 0; j <= k; j++) c.remove(0);
            }
            c.add(a);
            if (c.size() == len) break;
            i++;
        }
        System.out.println("First signal/message: " + i);
    }
}
