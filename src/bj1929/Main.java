package bj1929;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();

		boolean[] isNotPrime = new boolean[N + 1];
		isNotPrime[1] = true;
		for(int i = 2; i <= N; i++) {
			if (isNotPrime[i]) {
				continue;
			}
			for (int j = 2; i * j <= N; j++) {
				isNotPrime[i * j] = true;
			}
		}

		for (int m = M; m <= N; m++) {
			if (!isNotPrime[m]) {
				System.out.println(m);
			}
		}
	}

}
