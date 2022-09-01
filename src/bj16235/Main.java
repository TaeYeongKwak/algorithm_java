package bj16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K; // N = 땅 크기, M = 나무 갯수, K = 년도
    static int[][] A; // 겨울에 넣을 양분
    static PriorityQueue<Tree> trees; // 나무 정보
    static Queue<Tree> liveTrees; // 살아있는 나무
    static Queue<Tree> deadTrees; // 죽은 나무
    static int[][] nutrient; // 양분 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        nutrient = new int[N + 1][N + 1];
        trees = new PriorityQueue<>();
        liveTrees = new LinkedList<>();
        deadTrees = new LinkedList<>();

        // 겨울마다 줄 양분 정보 저장, 기본 양분 저장
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                nutrient[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 정보 저장
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(y, x, z));
        }

        // 나무제테크를 k년도 만큼 진행
        for (int k = 1; k < K + 1; k++){
            treeFinance();
        }

        System.out.println(trees.size());
    }

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    static void treeFinance(){
        // 봄
        // 나무들을 하나씩 확인해보고 해당 나무가 있는 땅에 양분이 있는지 없는지를 확인한다.
        while(!trees.isEmpty()){
            Tree tree = trees.poll();
            // 양분이 충분한 경우
            if (tree.age <= nutrient[tree.y][tree.x]){
                // 해당 땅의 양분을 나이만큼 줄이고 나이를 +1 한다.
                nutrient[tree.y][tree.x] -= tree.age;
                tree.age += 1;
                liveTrees.offer(tree);
            }
            // 양분이 부족한 경우
            else{
                deadTrees.offer(tree);
            }
        }

        // 여름
        // 죽은 나무들의 나이값 / 2 만큼의 값을 양분으로 추가한다.
        while(!deadTrees.isEmpty()){
            Tree tree = deadTrees.poll();
            nutrient[tree.y][tree.x] += (tree.age / 2);
        }

        // 가을
        // 살아있는 나무들중 번식이 가능한(age % 5 == 0) 나무들 주위에 새로운 나무를 추가한다.
        while(!liveTrees.isEmpty()){
            Tree tree = liveTrees.poll();
            if (tree.possibleBreeding()){
                // 인접한 8방향을 확인
                for (int i = 0; i < 8; i++){
                    int nx = tree.x + dx[i];
                    int ny = tree.y + dy[i];

                    // 땅을 벗어나지 않았다면 나이가 1인 나무를 추가
                    if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                        trees.offer(new Tree(nx, ny, 1));
                    }
                }
            }
            // 해당 나무도 다시 우선순위 큐에 추가
            trees.offer(tree);
        }

        // 겨울
        // 로봇이 해당 땅에 A[r][c]만큼의 양분을 추가한다.
        for (int i = 1; i < N + 1; i++){
            for (int j = 1; j < N + 1; j++){
                nutrient[i][j] += A[i][j];
            }
        }
    }
}

class Tree implements Comparable<Tree>{
    int x;
    int y;
    int age;

    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    public boolean possibleBreeding(){
        return (age % 5 == 0);
    }

    @Override
    public int compareTo(Tree o) {
        return Integer.compare(age, o.age);
    }
}
