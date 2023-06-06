package bj2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int max = 0;
	static int answer = 1;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		StringTokenizer st;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(map[i][j], max);
				min = Math.min(map[i][j], min);
			}
		}

		for (int k = min; k < max; k++) {
			answer = Math.max(solution(k), answer);
		}

		System.out.println(answer);
	}

	static void init() {
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	static boolean check(int x, int y, int height) {
		return !visited[y][x] && (map[y][x] > height);
	}

	static int solution(int height) {
		init();
		int count = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (check(j, i, height)) {
					bfs(j, i, height);
					count++;
				}
			}
		}
		return count;
	}

	static void bfs(int x, int y, int height) {
		queue.clear();
		queue.offer(new int[]{x, y});
		visited[y][x] = true;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1) {
					if (check(nx, ny, height)) {
						queue.offer(new int[]{nx, ny});
						visited[ny][nx] = true;
					}
				}
			}
		}
	}

}
