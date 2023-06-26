package bj1755;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static String[] numWord = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(Main::convert));
		for (int m = M; m <= N; m++) {
			pq.add(m);
		}

		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(!pq.isEmpty()) {
			sb.append(pq.poll());
			i++;
			if (i == 10) {
				sb.append("\n");
				i = 0;
				continue;
			}
			sb.append(" ");
		}

		System.out.println(sb);
	}

	static String convert(int n) {
		String numString = String.valueOf(n);
		StringBuilder sb = new StringBuilder();
		for (char c : numString.toCharArray()) {
			sb.append(numWord[(c - '0')] + " ");
		}
		return sb.toString();
	}

}
