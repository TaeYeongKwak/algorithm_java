package bj9625;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int[][] dp = new int[K + 1][2];
		dp[0][0] = 1;
		for (int k = 1; k <= K; k++){
			dp[k][0] = dp[k - 1][1];
			dp[k][1] = dp[k - 1][0] + dp[k - 1][1];
		}

		System.out.println(dp[K][0] + " " + dp[K][1]);
	}
}
