package bj1439;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();

		int[] count = new int[2];
		char check = '.';
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (check != c) {
				check = c;
				count[c - 48]++;
			}
		}

		System.out.println(Math.min(count[0], count[1]));
	}
}
