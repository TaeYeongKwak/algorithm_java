package bj10282;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			List<int[]>[] graph = new ArrayList[N + 1];
			for (int n = 1; n < N + 1; n++){
				graph[n] = new ArrayList<>();
			}

			for (int d = 0; d < D; d++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph[b].add(new int[]{a, s});
			}

			bw.write(solution(N, C, graph));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static String solution(int N, int C, List<int[]>[] graph){
		int count = 0;
		int time = 0;
		int[] dist = hacking(N, C, graph);
		for (int n = 1; n < N + 1; n++){
			if (dist[n] == Integer.MAX_VALUE) continue;
			time = Math.max(time, dist[n]);
			count++;
		}

		return count + " " + time;
	}



	static int[] hacking(int N, int start, List<int[]>[] graph){
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.clear();
		pq.offer(new int[]{start, 0});
		dist[start] = 0;

		while(!pq.isEmpty()){
			int[] now = pq.poll();

			if (dist[now[0]] < now[1]) continue;

			for (int[] next : graph[now[0]]){
				if (dist[next[0]] <= now[1] + next[1]) continue;
				dist[next[0]] = now[1] + next[1];
				pq.offer(new int[]{next[0], dist[next[0]]});
			}
		}

		return dist;
	}
}
