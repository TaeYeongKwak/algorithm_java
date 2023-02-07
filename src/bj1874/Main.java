package bj1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());
		int[] order = new int[N + 1];
		for (int i = 1; i < N + 1; i++){
			order[i] = Integer.parseInt(br.readLine());
		}

		StringBuilder answer = new StringBuilder();
		int index = 1;
		for (int n = 1; n < N + 1; n++){
			stack.push(n);
			answer.append("+\n");
			while(!stack.isEmpty() && stack.peek() == order[index]){
				stack.pop();
				answer.append("-\n");
				index++;
			}
		}

		System.out.println(stack.isEmpty()? answer : "NO");

	}
}
