package bj1049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] bundle = new int[M];
		int[] piece = new int[M];

		for (int m = 0; m < M; m++){
			st = new StringTokenizer(br.readLine());
			bundle[m] = Integer.parseInt(st.nextToken());
			piece[m] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(bundle);
		Arrays.sort(piece);

		int bundleCnt = N / 6;
		int pieceCnt = N % 6;
		int bundlePrice = (bundle[0] * bundleCnt) + piece[0] * pieceCnt;
		if (pieceCnt > 0){
			bundlePrice = Math.min(bundlePrice, bundle[0] * (bundleCnt + 1));
		}
		int min = Math.min(bundlePrice, piece[0] * N);
		System.out.println(min);
	}
}
