package bj2941;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] croatia = new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

		String str = sc.nextLine();
		for (String alphabet : croatia) {
			str = str.replaceAll(alphabet, "|");
		}
		System.out.println(str.length());
	}
}
