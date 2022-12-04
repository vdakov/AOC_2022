package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ElfPairs {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("resources\\pairs.txt"));
        int overlaps = 0;
        int contains = 0;

        while(input.hasNextLine()){
            Scanner curr = new Scanner(input.nextLine()).useDelimiter("-|,");
            Pair r1 = new Pair(curr.next(),curr.next());
            Pair r2 = new Pair (curr.next(),curr.next());
            if (r1.fullyIntersect(r2)) contains++;
            if (r1.intersect(r2)) overlaps++;
        }
        System.out.println("Fully overlapping pairs:" + contains);
        System.out.println("Intersecting pairs:"+overlaps);

    }

}

class Pair{
    int start;
    int end;

    public Pair(String r1, String r2){
        this.start=Integer.parseInt(r1);
        this.end =Integer.parseInt(r2);
    }

    public boolean fullyIntersect(Pair a){
        return this.start <= a.start && this.end >= a.end || a.start<=this.start && a.end >= this.end;
    }
    public boolean intersect(Pair a){ //two pairs intersect if one has the other's start withing itself
        return a.start >= this.start && a.start <= this.end || this.start >= a.start && this.start <= a.end;
    }
}
