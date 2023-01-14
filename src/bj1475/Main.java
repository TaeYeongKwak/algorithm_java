package bj1475;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.nextLine();
        int[] nums = new int[10];
        String[] nStr = N.split("");
        for(String s : nStr){
            nums[Integer.parseInt(s)]++;
        }

        nums[6] += nums[9];
        nums[6] = (nums[6] % 2 == 0)? nums[6] / 2 : nums[6] / 2 + 1;
        nums[9] = 0;

        int max = 0;
        for (int i = 0; i < 10; i++){
            max = Math.max(nums[i], max);
        }

        System.out.println(max);
    }
}
