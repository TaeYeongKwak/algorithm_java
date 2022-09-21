package pg_신고_결과_받기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

    static Map<String, Integer> idNum;
    static boolean[][] isReport;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] id_list = new String[N];
        for (int i = 0; i < N; i++){
            id_list[i] = br.readLine();
        }

        String[] report = new String[M];
        for (int i = 0; i < M; i++){
            report[i] = br.readLine();
        }

        Solution s = new Solution();
        int[] answer = s.solution(id_list, report, k);
        for (int a : answer){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        idNum = new HashMap<>();
        int idSize = id_list.length;
        for (int i = 0; i < idSize; i++){
            idNum.put(id_list[i], i);
        }

        isReport = new boolean[idSize][idSize];
        int[] reportCnt = new int[idSize];

        StringTokenizer st;
        for (String r : report){
            st = new StringTokenizer(r);
            int fromId = idNum.get(st.nextToken());
            int toId = idNum.get(st.nextToken());

            if (!isReport[fromId][toId]){
                isReport[fromId][toId] = true;
                reportCnt[toId] += 1;
            }
        }

        answer = new int[idSize];
        for (int i = 0; i < idSize; i++){
            if (reportCnt[i] >= k){
                for (int j = 0; j < idSize; j++){
                    if (isReport[j][i]){
                        answer[j] += 1;
                    }
                }
            }
        }

        return answer;
    }
}
