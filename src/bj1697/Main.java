package bj1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;
        boolean[] visited = new boolean[200001];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0});

        while(!queue.isEmpty()){
            int[] temp = queue.poll();

            if (temp[0] == k){
                result = temp[1];
                break;
            }

            int[] movePoints = {temp[0] - 1, temp[0] + 1, temp[0] * 2};
            for (int movePoint : movePoints){
                if (0 <= movePoint && movePoint <= 200000){
                    if (!visited[movePoint]) {
                        visited[movePoint] = true;
                        queue.offer(new int[]{movePoint, temp[1] + 1});
                    }
                }
            }
        }

        System.out.println(result);
    }
}
