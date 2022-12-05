package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class StackCounter {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("resources\\stacks.txt"));
        HashMap<Integer, List<String>> map = new HashMap<>();
        HashMap<Integer, Integer> indices = new HashMap<>();

        String line = input.nextLine();

        while (!line.contains("1")) {
            String[] r = line.split("");
            for (int i = 0; i < r.length; i++) {
                if (i % 4 == 1 && !r[i].equals(" ")) {
                    int k = i / 4 + 1;
                    if (!map.containsKey(k)) {
                        map.put(k, new ArrayList<>());
                    }
                    map.get(k).add(r[i]);
                }
            }
            line = input.nextLine();
        }

        input.nextLine();
        HashMap<Integer, Stack<String>> stacks = new HashMap<>();

        for (int i : map.keySet()) {
            stacks.put(i, new Stack<>());
            int j = map.get(i).size() - 1;
            while (j >= 0) {
                stacks.get(i).push(map.get(i).get(j));
                j--;
            }
        }

        //stacks = partOne(input,stacks); //VCTFTJQCG
        stacks = partTwo(input,stacks);

        String out="";

        for(Stack a: stacks.values()){
            out+=a.pop();
        }
        System.out.println(out);


    }

    public static HashMap<Integer, Stack<String>> partOne(Scanner input, HashMap<Integer, Stack<String>> stacks){
        while (input.hasNextLine()) {
            String line=input.nextLine().replaceAll("\\s+","");
            List<Integer> row = new ArrayList<>(List.of(line.split("move|from|to")).stream()
                    .filter(x->!x.equals("")).map(Integer::parseInt).collect(Collectors.toList()));
            int count = row.get(0);
            int from = row.get(1);
            int to = row.get(2);

            while(count>0) {
                stacks.get(to).push(stacks.get(from).pop());
                count--;
            }
        }
        return stacks;
    }

    public static HashMap<Integer, Stack<String>> partTwo(Scanner input, HashMap<Integer, Stack<String>> stacks){
        while (input.hasNextLine()) {
            String line=input.nextLine().replaceAll("\\s+","");
            List<Integer> row = new ArrayList<>(List.of(line.split("move|from|to")).stream()
                    .filter(x->!x.equals("")).map(Integer::parseInt).collect(Collectors.toList()));
            int count = row.get(0);
            int from = row.get(1);
            int to = row.get(2);

            ArrayList<String> moved = new ArrayList<>();

            while(count>0) {
                moved.add(stacks.get(from).pop());
                count--;
            }
            for(int i=moved.size()-1;i>=0;i--){
                stacks.get(to).push(moved.get(i));
            }
        }
        return stacks;
    }
}
