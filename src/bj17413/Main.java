package bj17413;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();
		Stack<Character> stack = new Stack<>();
		boolean reverse = true;
		StringBuilder answer = new StringBuilder();
		for (char c : S.toCharArray()) {
			if (c == '<') {
				popAll(stack, answer);
				answer.append(c);
				reverse = false;
			}
			else if (c == '>') {
				answer.append(c);
				reverse = true;
			}
			else if (c == ' ') {
				if (reverse)
					popAll(stack, answer);
				answer.append(c);
			}
			else {
				if (reverse)
					stack.push(c);
				else
					answer.append(c);
			}
		}
		popAll(stack, answer);

		System.out.println(answer);
	}

	public static void popAll(Stack<Character> stack, StringBuilder answer) {
		while(!stack.isEmpty()) {
			answer.append(stack.pop());
		}
	}
}
