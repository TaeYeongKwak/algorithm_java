package bj5635;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Person[] people = new Person[N];
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			people[n] = new Person(st.nextToken(),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(people);
		System.out.println(people[N - 1].name);
		System.out.println(people[0].name);
	}
}

class Person implements Comparable<Person> {
	String name;
	int day;
	int mouth;
	int year;

	public Person(String name, int day, int mouth, int year) {
		this.name = name;
		this.day = day;
		this.mouth = mouth;
		this.year = year;
	}

	@Override
	public int compareTo(Person o) {
		int y = Integer.compare(year, o.year);
		if (y != 0)
			return y;
		int m = Integer.compare(mouth, o.mouth);
		if (m != 0)
			return m;
		int d = Integer.compare(day, o.day);
		return d;
	}
}
