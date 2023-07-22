package bj11651;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		Point[] points = new Point[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			points[n] = new Point(x, y);
		}

		Arrays.sort(points);

		for (Point p : points) {
			bw.write(p.toString());
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}

class Point implements Comparable<Point>{
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		int k = Integer.compare(this.y, o.y);
		return k == 0? Integer.compare(this.x, o.x) : k;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

}