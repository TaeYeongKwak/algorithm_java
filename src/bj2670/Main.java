package bj2670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[] nums = new double[N];
		for (int n = 0; n < N; n++){
			nums[n] = Double.parseDouble(br.readLine());
		}

		double[] dp = new double[N];
		dp[0] = nums[0];
		double max = dp[0];
		for (int i = 1; i < N; i++){
			dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
			max = Math.max(max, dp[i]);
		}

		System.out.println(String.format("%.3f", max));
	}
}
