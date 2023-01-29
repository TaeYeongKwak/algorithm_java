package bj10867;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> set = new TreeSet<>();
		for (int n = 0; n < N; n++){
			set.add(Integer.parseInt(st.nextToken()));
		}

		StringBuilder sb = new StringBuilder();
		for (int n : set){
			sb.append(n + " ");
		}

		System.out.println(sb);
	}
}
