package bj11652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Long, Integer> map = new HashMap<>();
		long cardMax = Long.MIN_VALUE;
		int countMax = 0;
		for (int n = 0; n < N; n++){
			long card = Long.parseLong(br.readLine());
			int count = map.getOrDefault(card, 0) + 1;
			map.put(card, count);
			if (count > countMax){
				countMax = count;
				cardMax = card;
			}
			else if (count == countMax && cardMax > card){
				cardMax = card;
			}
		}

		System.out.println(cardMax);

	}

}


