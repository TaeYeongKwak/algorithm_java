package bj15649;

import java.io.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        visited = new boolean[n + 1];

        backTracking(m, "");

        bw.flush();
        bw.close();
        br.close();
    }

    static void backTracking(int r, String str) throws IOException {
        if (r == 0){
            bw.write(str + "\n");
            return;
        }
        for(int i = 1; i < n + 1; i++){
            if (!visited[i]){
                visited[i] = true;
                backTracking(r - 1, str + i + " ");
                visited[i] = false;
            }
        }
    }
}
