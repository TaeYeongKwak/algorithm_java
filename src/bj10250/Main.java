package bj10250;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++){
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			bw.write(solution(H, W, N));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static String solution(int H, int W, int N){
		int answer = (N % H == 0)? (H * 100) + (N / H) : ((N % H) * 100) + ((N / H) + 1);
		return String.valueOf(answer);
	}
}
