package bj2609;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();

		int gcd = gcd(x, y);
		System.out.println(gcd);
		System.out.println(x * y / gcd);
	}

	public static int gcd(int x, int y){
		return (y == 0)? x : gcd(y, x % y);
	}
}
