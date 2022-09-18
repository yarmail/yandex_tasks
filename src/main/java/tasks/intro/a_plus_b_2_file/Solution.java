package tasks.intro.a_plus_b_2_file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        PrintWriter writer = new PrintWriter("output.txt");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        writer.print(a+b);
        writer.close();
    }
}