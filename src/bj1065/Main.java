package bj1065;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int answer = 0;
		for (int n = 1; n <= N; n++) {
			if (isHanSu(n))
				answer++;
		}
		System.out.println(answer);
	}

	public static boolean isHanSu(int n) {
		if (n < 100) {
			return true;
		}
		int[] nums = new int[4];
		int i = 0;
		int k = 10;
		while(n > 0) {
			nums[i++] = n % k;
			n = n / k;
		}
		int x = nums[1] - nums[0];
		for (int j = 1; j < i - 1; j++) {
			int y = nums[j + 1] - nums[j];
			if (y != x)
				return false;
		}
		return true;
	}
}
