package bj1789;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long S = sc.nextLong();
		int N = 0;
		long sum = 0;
		int n = 1;
		while(sum <= S){
			sum += n++;
			N++;
		}

		System.out.println(sum == S? N : N - 1);
	}
}
