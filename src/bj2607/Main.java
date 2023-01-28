package bj2607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] alphabet;
	static int length;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int answer = 0;
		String word = br.readLine();
		alphabet = countAlphabet(word);
		length = word.length();
		for (int n = 1; n < N; n++){
			word = br.readLine();
			if (check(word)) answer++;
		}

		System.out.println(answer);
	}

	static int[] countAlphabet(String word){
		int[] alpha = new int[26];
		for (int w : word.toCharArray()){
			alpha[(w - 'A')]++;
		}
		return alpha;
	}

	static boolean check(String word){
		int count = 0;
		int[] checkAlpha = countAlphabet(word);
		for (int i = 0; i < 26; i++){
			count += Math.abs(checkAlpha[i] - alphabet[i]);
		}
		if (length == word.length()){
			return count <= 2;
		}
		return count == 1;
	}
}
