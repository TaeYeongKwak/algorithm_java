package bj12852;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        class Node{
            int w;
            List<Integer> seq;

            Node(){
                this.w = 0;
                this.seq = new ArrayList<>();
                seq.add(1);
            }

            void setNode(int w, List<Integer> seq){
                this.w = w;
                this.seq = seq;
            }
        }

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node dp[] = new Node[n + 1];

        for (int i = 1; i < n + 1; i++){
            dp[i] = new Node();
        }

        for(int i = 2; i <= n; i++){
            if (i <= 3) {
                dp[i].w = 1;
            }
            int x = n;
            int y = i;
            if (i % 3 == 0) {
                x = dp[i / 3].w + 1;
                y = i / 3;
            }
            if (i % 2 == 0) {
                if(dp[i / 2].w + 1 < x){
                    x = dp[i / 2].w + 1;
                    y = i / 2;
                };
            }
            if (dp[i - 1].w + 1 < x){
                x = dp[i - 1].w + 1;
                y = i - 1;
            }
            List<Integer> seq = new ArrayList<>(dp[y].seq);
            seq.add(i);
            dp[i].setNode(x, seq);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(dp[n].w + "\n");
        for(int i = dp[n].seq.size()-1; i >= 0; i--){
            bw.write(dp[n].seq.get(i) + " ");
        }

        bw.flush();
        bw.close();
    }

}
