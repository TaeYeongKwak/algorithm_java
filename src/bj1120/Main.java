package bj1120;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		String A = st.nextToken();
		String B = st.nextToken();

		int answer = A.length();
		for (int i = 0; i <= B.length() - A.length(); i++) {
			int count = 0;
			for (int j = 0; j < A.length(); j++) {
				if (A.charAt(j) != B.charAt(i + j)) {
					count++;
				}
			}
			answer = Math.min(answer, count);
		}

		System.out.println(answer);
	}

}
