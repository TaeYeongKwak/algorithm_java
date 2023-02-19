package bj10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		CustomQueue queue = new CustomQueue();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int n = 0; n < N; n++){
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int num;
			switch(command){
				case "push" :
					num = Integer.parseInt(st.nextToken());
					queue.push(num);
					break;
				case "pop":
					bw.write(queue.pop() + "\n");
					break;
				case "size":
					bw.write(queue.size() + "\n");
					break;
				case "empty":
					bw.write(queue.empty() + "\n");
					break;
				case "front":
					bw.write(queue.front() + "\n");
					break;
				case "back":
					bw.write(queue.back() + "\n");
					break;
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}

class CustomQueue{

	Queue<Integer> queue;
	Integer back;

	public CustomQueue() {
		this.queue = new LinkedList<>();
		back = null;
	}

	public void push(int num){
		queue.offer(num);
		back = num;
	}

	public int pop(){
		if (queue.isEmpty()){
			back = null;
			return -1;
		}
		return queue.poll();
	}

	public int size(){
		return queue.size();
	}

	public int empty(){
		return queue.isEmpty()? 1 : 0;
	}

	public int front(){
		return queue.isEmpty()? -1 : queue.peek();
	}

	public int back(){
		return queue.isEmpty()? -1 : back;
	}

}
