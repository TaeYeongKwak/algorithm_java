package bj2010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		for (int n = 0; n < N; n++) {
			int x = Integer.parseInt(br.readLine());
			answer += (answer == 0)? x : x - 1;
		}
		System.out.println(answer);
	}
}
