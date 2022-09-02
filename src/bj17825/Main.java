package bj17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dice;
    static int[] block = {-1, -1, -1, -1, -1, -1};
    static Node[] map;
    static int[] horse;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dice = new int[10];
        for (int i = 0; i < 10; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        init();

        result = 0;
        dfs(0, 0);
        System.out.println(result);
    }

    static void init(){
        map = new Node[33];
        map[0] = new Node(0, new int[]{-1, 1, 2, 3, 4, 5}, 0);
        map[32] = new Node(32, block, 0);
        for (int i = 1; i < 21; i++){
            int[] nextIdx = new int[6];
            if (i == 5){
                nextIdx = new int[]{-1, 21, 22, 23, 24, 30};
            }
            else if (i == 10){
                nextIdx = new int[]{-1, 28, 29, 24, 30, 31};
            }
            else if (i == 15){
                nextIdx = new int[]{-1, 27, 26, 25, 24, 30};
            }
            else if (i == 20){
                nextIdx = new int[]{-1, 32, 32, 32, 32, 32};
            }
            else{
                nextIdx[0] = -1;
                for (int j = 1; j < 6; j++){
                    nextIdx[j] = (i + j < 21)? i + j : 32;
                }
            }
            map[i] = new Node(i, nextIdx, i * 2);
        }

        map[21] = new Node(21, new int[]{-1, 22, 23, 24, 30, 31}, 13);
        map[22] = new Node(22, new int[]{-1, 23, 24, 30, 31, 20}, 16);
        map[23] = new Node(23, new int[]{-1, 24, 30, 31, 20, 32}, 19);
        map[24] = new Node(24, new int[]{-1, 30, 31, 20, 32, 32}, 25);

        map[28] = new Node(28, new int[]{-1, 29, 24, 30, 31, 20}, 22);
        map[29] = new Node(29, new int[]{-1, 24, 30, 31, 20, 32}, 24);

        map[30] = new Node(30, new int[]{-1, 31, 20, 32, 32, 32}, 30);
        map[31] = new Node(31, new int[]{-1, 20, 32, 32, 32, 32}, 35);

        map[25] = new Node(25, new int[]{-1, 24, 30, 31, 20, 32}, 26);
        map[26] = new Node(26, new int[]{-1, 25, 24, 30, 31, 20}, 27);
        map[27] = new Node(27, new int[]{-1, 26, 25, 24, 30, 31}, 28);

        horse = new int[]{0, 0, 0, 0};
    }

    static void dfs(int r, int score){
        if (r == 10){
            result = Math.max(result, score);
            return;
        }

        for (int i = 0; i < 4; i++){
            int now = horse[i];
            if (now == 32) continue;

            int next = map[now].nextIdx[dice[r]];

            boolean flag = true;
            for (int j = 0; j < 4; j++){
                if (i != j && next != 32 && horse[j] == next){
                    flag = false;
                    break;
                }
            }

            if (flag){
                horse[i] = next;
                dfs(r + 1, score + map[next].score);
                horse[i] = now;
            }

        }
    }
}

class Node{
    int idx;
    int[] nextIdx;
    int score;

    public Node(int idx, int[] nextIdx, int score) {
        this.idx = idx;
        this.nextIdx = nextIdx;
        this.score = score;
    }
}
