package bj1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S;
	static int[] nums;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			nums[n] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0, 0);
		System.out.println(answer);
	}

	static void dfs(int r, int sum, int size) {
		if (r == N) {
			if (size > 0 && sum == S)
				answer++;
			return;
		}
		dfs(r + 1, sum, size);
		dfs(r + 1, sum + nums[r], size + 1);
	}

}
