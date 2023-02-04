package bj1302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> map = new HashMap<>();
		String top = null;
		int max = 0;
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++){
			String book = br.readLine();
			int count = map.getOrDefault(book, 0) + 1;
			map.put(book, count);
			if (count > max){
				top = book;
				max = count;
			}
			else if (count == max){
				if (book.compareTo(top) < 0){
					top = book;
				}
			}
		}

		System.out.println(top);

	}
}
