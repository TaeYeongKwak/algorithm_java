package bj7662;

import java.io.*;
import java.util.*;

public class Main {

    static int K;
    static TreeMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        //트리맵을 사용 key : 숫자, value : 해당 숫자의 갯수
        map = new TreeMap<>();

        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t < T + 1; t++){
            K = Integer.parseInt(br.readLine());
            map.clear();

            for (int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());

                if (command == 'I'){
                    // 트리맵에 n의 갯수를 +1
                    map.put(n, map.getOrDefault(n, 0) + 1);
                }
                else if (command == 'D'){
                    // 트리맵이 비었을 경우 건너뜀
                    if (map.size() == 0){
                        continue;
                    }
                    // n = -1이면 최소값, n = 1이면 최대값을 찾는다.
                    int deleteNum = (n == -1)? map.firstKey() : map.lastKey();
                    // 해당 값의 갯수가 0이되면 트리맵에서 해당 값을 삭제
                    if (map.put(deleteNum, map.get(deleteNum) - 1) == 1){
                        map.remove(deleteNum);
                    }
                }
            }

            if (map.isEmpty()){
                bw.write("EMPTY\n");
            }
            else{
                bw.write(map.lastKey() + " " + map.firstKey() + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
