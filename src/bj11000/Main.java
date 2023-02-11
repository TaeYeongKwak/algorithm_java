package bj11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] lectures = new int[N][2];
		for (int n = 0; n < N; n++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			lectures[n] = new int[]{s, t};
		}

		Arrays.sort(lectures, (o1, o2) -> o1[0] == o2[0]? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(lectures[0][1]);

		for (int n = 1; n < N; n++){
			if (pq.peek() <= lectures[n][0]){
				pq.poll();
			}
			pq.offer(lectures[n][1]);
		}

		System.out.println(pq.size());
	}
}
