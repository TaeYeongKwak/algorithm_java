package bj11650;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[][] point = new int[N][2];
		StringTokenizer st;
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(point, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));
		for (int i = 0; i < N; i++){
			bw.write(point[i][0] + " " + point[i][1]);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
