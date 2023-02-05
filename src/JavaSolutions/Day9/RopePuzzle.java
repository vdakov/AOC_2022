package Day9;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RopePuzzle {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("resources\\ropePuzzle.txt"));
        partOne(input);
        input = new Scanner(new File("resources\\ropePuzzle.txt"));
        partTwo(input, 10);
    }

    public static void partOne(Scanner input) {
        Board visited = new Board();
        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);
        while (input.hasNextLine()) {
            String[] move = input.nextLine().split(" ");
            String direction = move[0];
            int amount = Integer.parseInt(move[1]);
            int step = 1;
            boolean x = true;
            if (direction.equals("U") || direction.equals("D")) x = false;
            if (direction.equals("L") || direction.equals("D")) step = -1;
            int i = amount;
            while (i > 0) {
                if (x) {
                    head.x += step;
                } else {
                    head.y += step;
                }
                i--;
            }
            i = amount;
            tail.findNewLocation(head,i,tail,visited,step);

        }
        System.out.println("Amount of visited positions:" + visited.size());
    }

    public static void partTwo(Scanner input, int knots) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < knots; i++) {
            points.add(new Point(0, 0));
        }

        Board visited = new Board();
        while (input.hasNextLine()) {
            String[] move = input.nextLine().split(" ");
            String direction = move[0];
            int amount = Integer.parseInt(move[1]);
            int step = 1;
            boolean x = true;

            if (direction.equals("U") || direction.equals("D")) x = false;
            if (direction.equals("L") || direction.equals("D")) step = -1;

            int i = amount;
            Point head = points.get(0);
            while (i > 0) {
                if (x) {
                    head.x += step;
                } else {
                    head.y += step;
                }
                i--;
            }
            i = amount;
            Point tail = points.get(points.size()-1);
            for (int j = 1; j < points.size(); j++) {
                points.get(j).findNewLocation(points.get(j - 1), i, tail, visited, step);
            }
            //visited.add(tail);


        }
        System.out.println("Amount of visited positions with " + knots + " knots:" + visited.size());
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point o) {
        return x == o.x && y == o.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void findNewLocation(Point next, int i, Point tail, Board visited, int step) {
        //this - current tail
//        while (i > 0) {
//            visited.add(tail);
//            if (Math.abs(localHead.x - this.x) > 1) {
//                this.y = localHead.y;
//                this.x += step;
//            } else if (Math.abs(localHead.y - this.y) > 1) {
//                this.x = localHead.x;
//                this.y += step;
//            }
//            i--;
//        }
        while (i>0){
            visited.add(tail);
            if (Math.abs((next.x) - (this.x)) > 1) {//move left/right
                this.y += (short)(Integer.compare((next.y) - (this.y), 0)); // move 1 place u/d
                this.x +=step;
            } else if (Math.abs((next.y) - (this.y)) > 1) { //move up/down
                this.x += (short) (Integer.compare((next.x) - (this.x), 0)); // move 1 place l/r
                this.y +=step; //move tail
            }
            i--;

        }

    }
}

class Board {
    ArrayList<Point> points;

    public Board() {
        this.points = new ArrayList<>();
    }

    public void add(Point a) {
        for (Point b : points) {
            if (b.equals(a)) return;
        }
        points.add(new Point(a.x, a.y));
    }

    public int size() {
        return points.size();
    }

    @Override
    public String toString() {
        return "Board{" +
                "points=" + points +
                '}';
    }
}

