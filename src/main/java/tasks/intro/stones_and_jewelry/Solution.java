package tasks.intro.stones_and_jewelry;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String jewelry = reader.readLine();
        String stones = reader.readLine();

        int result = 0;
        for(int i = 0; i < stones.length(); i++) {
            char ch = stones.charAt(i);
            if (jewelry.indexOf(ch) >= 0) {
                ++result;
            }
        }
        System.out.println(result);
    }
}