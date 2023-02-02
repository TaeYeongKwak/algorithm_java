package bj2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> queue = new LinkedList<>();

		int N = sc.nextInt();
		for (int i = 1; i <= N; i++){
			queue.offer(i);
		}
		boolean flag = false;
		int answer = queue.poll();
		while(!queue.isEmpty()){
			answer = queue.poll();
			if (!flag){
				queue.offer(answer);
			}
			flag = !flag;
		}

		System.out.println(answer);
	}
}
