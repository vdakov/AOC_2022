package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PackageCounter {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("resources\\packages.txt"));
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String a = line.substring(0, line.length() / 2);
            String b = line.substring(line.length() / 2);
            for (char c : a.toCharArray()) {
                if (b.contains(c + "")) {
                    if (Character.isLowerCase(c)) {
                        c = Character.toUpperCase(c);
                        list.add(c - 64);
                    } else {
                        c = Character.toLowerCase(c);
                        list.add(c - 70);
                    }
                    break;
                }
            }
        }
        int sum = list.stream().reduce(Integer::sum).stream().collect(Collectors.toList()).get(0);
        input = new Scanner(new File("resources\\packages.txt"));
        System.out.println("Sum per line: "+sum);

        while (input.hasNextLine()) {
            String a = input.nextLine();
            String b = input.nextLine();
            String c = input.nextLine();
            for (char d : a.toCharArray()) {
                if (b.contains(d + "") && c.contains(d+"")) {
                    if (Character.isLowerCase(d)) {
                        d = Character.toUpperCase(d);
                        list2.add(d - 64);
                    } else {
                        d = Character.toLowerCase(d);
                        list2.add(d - 70);
                    }
                    break;
                }
            }
        }


        int sum2 = list2.stream().reduce(Integer::sum).stream().collect(Collectors.toList()).get(0);
        System.out.println("Sum per group: "+sum2);
    }
}
