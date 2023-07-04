package bj10825;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		List<Grade> students = new ArrayList<>(N);
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			students.add(new Grade(
				st.nextToken(),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken())
			));
		}

		Collections.sort(students);
		for (Grade student : students) {
			bw.write(student.name);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

}

class Grade implements Comparable<Grade>{

	String name;
	int korean;
	int english;
	int math;

	public Grade(String name, int korean, int english, int math) {
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
	}

	@Override
	public int compareTo(Grade o) {
		int kor = Integer.compare(o.korean, this.korean);
		if (kor == 0) {
			int eng = Integer.compare(this.english, o.english);
			if (eng == 0) {
				int ma = Integer.compare(o.math, this.math);
				if (ma == 0) {
					return name.compareTo(o.name);
				}
				return ma;
			}
			return eng;
		}
		return kor;
	}

}
