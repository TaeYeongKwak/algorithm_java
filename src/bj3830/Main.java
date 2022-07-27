package bj3830;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0){
                break;
            }

            parent = new int[N + 1];
            weight = new int[N + 1];
            for (int i = 1; i < N + 1; i++){
                parent[i] = i;
            }

            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                if (command.equals("!")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());

                    union(a, b, w);
                    for (int j = 1; j < N + 1; j++){
                        System.out.print(weight[j] + " ");
                    }
                    System.out.println();
                }
                else if (command.equals("?")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int aRoot = find(a);
                    int bRoot = find(b);

                    if (aRoot != bRoot){
                        bw.write("UNKNOWN\n");
                    }else{
                        bw.write( weight[b] - weight[a] + "\n");
                    }
                }
            }

        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int x){
        if (x == parent[x]){
            return x;
        }
        int p = find(parent[x]);
        weight[x] += weight[parent[x]];
        return parent[x] = p;
    }

    static void union(int a, int b, int w){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;
        //기준에 대한 무게 조절
        weight[bRoot] = (weight[a] + w) - weight[b];
        parent[bRoot] = aRoot;
    }
}
