package bj4195;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<String, Integer> friendMap;
    static int[] parent;
    static int[] networkSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){

            friendMap = new HashMap<>();
            parent = new int[200001];
            networkSize = new int[200001];

            for (int i = 1; i < 200001; i++){
                parent[i] = i;
                networkSize[i] = 1;
            }

            int F = Integer.parseInt(br.readLine());
            int friendSize = 1;
            for (int f = 0; f < F; f++){
                st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                if (!friendMap.containsKey(friend1)){
                    friendMap.put(friend1, friendSize++);
                }
                if (!friendMap.containsKey(friend2)){
                    friendMap.put(friend2, friendSize++);
                }

                int friend1Idx = friendMap.get(friend1);
                int friend2Idx = friendMap.get(friend2);

                int friend1Root = find(friend1Idx);
                int friend2Root = find(friend2Idx);

                if (friend1Root == friend2Root){
                    bw.write(networkSize[find(friend1Idx)] + "\n");
                    continue;
                }

                union(friend1Idx, friend2Idx);
                bw.write(networkSize[find(friend1Idx)] + "\n");
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
        networkSize[x] = networkSize[parent[x]];
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot){
            networkSize[find(b)] = networkSize[find(a)] + networkSize[find(b)];
        }
        parent[find(a)] = find(b);
    }
}
