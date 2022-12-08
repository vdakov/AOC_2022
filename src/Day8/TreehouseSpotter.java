package Day8;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TreehouseSpotter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("resources\\trees.txt"));
        int i = 0;
        int j = 0;
        String a = reader.readLine();
        while (a != null) {
            j = a.length();
            i++;
            a = reader.readLine();
        }
        reader.close();
        int[][] trees = new int[i][j];
        reader = new BufferedReader(new FileReader("resources\\trees.txt"));
        i = 0;
        a = reader.readLine();
        while (a != null) {
            j = 0;
            String[] s = a.strip().split("");
            for (String num : s) trees[i][j++] = Integer.parseInt(num);
            i++;
            a = reader.readLine();
        }
        int visible = 0;
        int maxScore = 0;
        for (i = 0; i < trees.length; i++) {
            for (j = 0; j < trees[i].length; j++) {
                visible += checkTreeFromAllSides(i, j, trees);
                int score = scenicScore(i, j, trees);
                if (score > maxScore) maxScore = score;
            }
        }
        reader.close();
        System.out.println("Amount of visible trees:" + visible);
        System.out.println("Max Scenic Score:" + maxScore);
    }

    public static int checkTreeFromAllSides(int i, int j, int[][] trees) {
        for (int k = 0; k <= j; k++) {
            if (k == j) return 1; //left
            if (trees[i][k] >= trees[i][j]) break;
        }
        for (int k = trees[j].length - 1; k >= j; k--) {
            if (k == j) return 1; //right
            if (trees[i][k] >= trees[i][j]) break;
        }
        for (int k = 0; k <= i; k++) {
            if (k == i) return 1; //up
            if (trees[k][j] >= trees[i][j]) break;
        }
        for (int k = trees.length - 1; k >= i; k--) {
            if (k == i) return 1; //down
            if (trees[k][j] >= trees[i][j]) break;
        }
        return 0;
    }

    public static int scenicScore(int i, int j, int[][] trees) {
        int l = 0;
        int r = 0;
        int u = 0;
        int d = 0;
        for (int k = j - 1; k >= 0; k--) {
            l++;
            if (trees[i][k] >= trees[i][j]) break; //left
        }
        for (int k = j + 1; k < trees[i].length; k++) {
            r++; //right
            if (trees[i][k] >= trees[i][j]) break;
        }
        for (int k = i - 1; k >= 0; k--) {
            u++;
            if (trees[k][j] >= trees[i][j]) break;

        }
        for (int k = i + 1; k < trees.length; k++) {
            d++;
            if (trees[k][j] >= trees[i][j]) break;
        }
        return l * r * u * d;

    }

}


