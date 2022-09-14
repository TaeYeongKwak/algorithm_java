package bj1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Problem> heap = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());

            heap.offer(new Problem(i, deadline, ramen));
        }


        PriorityQueue<Integer> solve = new PriorityQueue<>();
        while(!heap.isEmpty()){
            Problem problem = heap.poll();
            if (problem.deadline > solve.size()){
                solve.offer(problem.ramen);
            }
            else if(problem.deadline == solve.size()){
                if (solve.peek() < problem.ramen){
                    solve.poll();
                    solve.offer(problem.ramen);
                }
            }
        }

        int result = 0;
        while(!solve.isEmpty()){
            result += solve.poll();
        }

        System.out.println(result);
    }
}

class Problem implements Comparable<Problem>{
    int pId;
    int deadline;
    int ramen;

    public Problem(int pId, int deadline, int ramen) {
        this.pId = pId;
        this.deadline = deadline;
        this.ramen = ramen;
    }

    @Override
    public int compareTo(Problem o) {
        int x = Integer.compare(deadline, o.deadline);
        return (x == 0)? Integer.compare(o.ramen, ramen) : x;
    }
}
