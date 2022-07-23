package bj1168;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] tree;
    static boolean[] visited;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("<");
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N){
            S *= 2;
        }

        visited = new boolean[N + 1];
        tree = new int[2 * S];

        init(1, N, 1);

        int k = K;
        for (int i = 1; i < N; i++){
            //해당 번호의 사람 찾기
            int x = query(1, N, 1,  k);
            sb.append(x + ", ");
            // 해당 번호의 사람 삭제
            update(1, N, 1, x, -1);

            // 뺄 사람 번호 조정
            // 방금 삭제한 사람의 오른쪽에 몇 명 더 있는지 확인
            int leftSum = sumQuery(1, N, 1, 1, x);
            int rightSum = sumQuery(1, N, 1, x, N);
            // k값보다 오른쪽에 사람이 더 많을 경우 -> 한바퀴를 안돌기 때문에 그냥 더해주면 됨.
            if (rightSum >= K){
                k = leftSum + K;
            }
            // k값보다 오른쪽에 사람이 더 적을 경우 -> 한바퀴 이상 돌아야 하기 때문에 K 값에서 오른쪽 사람만큼 뺀 후
            // 1바퀴이상 더 돌 경우 현재 인원수(tree[1])만큼 나누고 그 나머지를 k 값에 집어 넣는다.
            else{
                k = (K - rightSum) % tree[1];
                // 몇바퀴 더 돌 수 있기 때문에 현재 인원수만큼 나누고 그 나머지를 넣는다.
                if (k == 0){
                    // 나머지가 0일 경우는 끝쪽이므로 현재 인원수를 넣는다.
                    k = tree[1];
                }
            }
        }
        // 마지막 인원 빼기
        sb.append(query(1, N, 1,  1));
        sb.append(">");

        System.out.println(sb);
        br.close();
    }

    static int init(int left, int right, int node){
        if (left == right) return tree[node] = 1;
        int mid = (left + right) / 2;
        return tree[node] = init(left, mid, 2 * node) + init(mid + 1, right, 2 * node + 1);
    }

    static int sumQuery(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || right < queryLeft){
            return 0;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            return sumQuery(left, mid, 2 * node, queryLeft, queryRight) + sumQuery(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
        }
    }

    static int query(int left, int right, int node, int k){
        // index 반환
        if (left == right){
            return left;
        }
        int mid = (left + right) / 2;
        // 찾는 번호보다 왼쪽 합이 더 클 경우 왼쪽으로
        if (tree[2 * node] >= k){
            return query(left, mid, 2 * node, k);
        }
        // 작을 경우 오른쪽으로
        else{
            return query(mid + 1, right, 2 * node + 1, k - tree[2 * node]);
        }
    }

    static void update(int left, int right, int node, int target, int diff){
        //
        if (left > target || right < target){
            return;
        }
        tree[node] += diff;
        if (left != right){
            int mid = (left + right) / 2;
            update(left, mid, 2 * node, target, diff);
            update(mid + 1, right, 2 * node + 1, target, diff);
        }
    }
}
