package bj11478;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();
		Set<String> set = new HashSet<>();
		for (int i = 1; i <= S.length(); i++) {
			for (int j = 0; j < i; j++) {
				set.add(S.substring(j, i));
			}
		}
		System.out.println(set.size());
	}
}
