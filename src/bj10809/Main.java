package bj10809;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] word = sc.nextLine().toCharArray();
        int[] result = new int[26];

        Arrays.fill(result, -1);

        for (int i = 0; i < word.length; i++){
            int a = (int)(word[i] - 'a');
            if (result[a] == -1){
                result[a] = i;
            }
        }

        for (int r : result){
            System.out.print(r + " ");
        }
    }
}
