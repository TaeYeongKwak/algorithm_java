package bj17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D;
    static int[][] map;
    static boolean[] selected;
    static Queue<int[]> bowMan;
    static Queue<Enemy> enemies, queue;
    static List<Enemy> enemyList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        selected = new boolean[M + 1];
        queue = new LinkedList<>();
        bowMan = new LinkedList<>();
        enemies = new LinkedList<>();
        enemyList = new ArrayList<>();

        for (int n = 1; n < N + 1; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m < M + 1; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 1){
                    enemyList.add(new Enemy(m, n));
                }
            }
        }

        int result = 0;
        selectedBowMan(0, 0);
        while(!bowMan.isEmpty()){
            enemies.clear();
            for (Enemy e : enemyList){
                enemies.offer(new Enemy(e.x, e.y));
            }

            int killCnt = 0;
            int[] bowMans = bowMan.poll();
            while(!enemies.isEmpty()){
                for (int b : bowMans){
                    killEnemy(b);
                }
                killCnt += moveEnemy();
            }
            result = Math.max(killCnt, result);
        }

        System.out.println(result);
    }

    static void selectedBowMan(int r, int x){
        if (r == 3){
            int[] select = new int[3];
            int i = 0;
            for (int m = 1; m < M + 1; m++){
                if (selected[m]){
                    select[i++] = m;
                }
            }
            bowMan.offer(select);
            return;
        }

        for (int m = x + 1; m < M + 1; m++){
            selected[m] = true;
            selectedBowMan(r + 1, m);
            selected[m] = false;
        }
    }

    static void killEnemy(int bow){
        queue.clear();
        int[] bowMan = new int[]{N + 1, bow};
        Enemy deadEnemy = null;
        while(!enemies.isEmpty()){
            Enemy e = enemies.poll();
            int eDist = e.getDist(bowMan);
            if (eDist <= D){
                if (deadEnemy == null){
                    deadEnemy = e;
                }
                else if (deadEnemy.getDist(bowMan) > eDist){
                    deadEnemy = e;
                }
                else if (deadEnemy.getDist(bowMan) == eDist){
                    deadEnemy = (deadEnemy.x < e.x)? deadEnemy : e;
                }
            }
            queue.offer(e);
        }
        if (deadEnemy != null){
            deadEnemy.dead = true;
        }

        while(!queue.isEmpty()){
            enemies.offer(queue.poll());
        }
    }

    static int moveEnemy(){
        int killCnt = 0;
        while (!enemies.isEmpty()){
            Enemy e = enemies.poll();
            if (e.dead) {
                killCnt++;
                continue;
            }

            int moveY = e.y + 1;
            if (moveY < N + 1){
                e.y = moveY;
                queue.offer(e);
            }
        }

        while(!queue.isEmpty()){
            enemies.offer(queue.poll());
        }

        return killCnt;
    }
}

class Enemy{
    int x;
    int y;
    boolean dead;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        dead = false;
    }

    public int getDist(int[] bowMan){
        return Math.abs(bowMan[0] - y) + Math.abs(bowMan[1] - x);
    }
}