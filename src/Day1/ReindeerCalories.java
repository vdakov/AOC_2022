package Day1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class ReindeerCalories {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner (new File("resources\\calories.txt"));

        List<Integer> cals = new ArrayList<>();
        int sum = 0;
        while(input.hasNextLine()){
            String a = input.nextLine();
            if(!a.equals("")){
                sum+=Integer.parseInt(a);
            }else{
                cals.add(sum);
                sum=0;
            }

        }
        List<Integer> max = cals.stream().reduce(Integer::max).stream().collect(Collectors.toList());
        System.out.println("Max:" +max.get(0));
        Collections.sort(cals);
        Collections.reverse(cals);
        System.out.println(cals.get(0));
        System.out.println(cals.get(1));
        System.out.println(cals.get(2));
        System.out.println("Top 3: " + (cals.get(0)+cals.get(1)+cals.get(2)));

      
    }
}
