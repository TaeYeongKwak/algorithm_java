package bj1940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[] material = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++){
			material[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(material);

		int answer = 0;
		int left = 0;
		int right = N - 1;
		while(left < right){
			int sum = material[left] + material[right];
			if (sum == M){
				answer++;
				right--;
				left++;
			}
			else if (sum > M){
				right--;
			}
			else if (sum < M){
				left++;
			}
		}

		System.out.println(answer);
	}
}
