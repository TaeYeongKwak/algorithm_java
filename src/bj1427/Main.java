package bj1427;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] N = sc.nextLine().toCharArray();
		Arrays.sort(N);
		StringBuilder sb = new StringBuilder();
		for (int i = N.length - 1; i >= 0; i--) {
			sb.append(N[i]);
		}
		System.out.println(sb);
	}

}
