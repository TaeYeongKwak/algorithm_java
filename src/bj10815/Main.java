package bj10815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] cards;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		cards = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++){
			cards[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cards);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			int card = Integer.parseInt(st.nextToken());
			answer.append(search(card)? 1 : 0);
			if (m < M - 1) answer.append(" ");
		}

		System.out.println(answer);
	}

	static boolean search(int card) {
		int left = 0;
		int right = N - 1;
		while(left <= right) {
			int mid = (left + right) / 2;
			if (cards[mid] < card) {
				left = mid + 1;
			}
			else if (cards[mid] > card) {
				right = mid - 1;
			}
			else if (cards[mid] == card) {
				return true;
			}
		}
		return false;
	}

}
