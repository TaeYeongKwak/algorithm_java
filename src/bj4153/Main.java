package bj4153;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (check(x, y, z)){
                break;
            }

            bw.write(solution(x, y, z)? "right" : "wrong");
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean check(int x, int y, int z){
        return (x == 0 && y == 0 && z == 0);
    }

    static boolean solution(int x, int y, int z){
        int[] nums = new int[]{x, y, z};
        Arrays.sort(nums);

        return nums[2] * nums[2] == (nums[0] * nums[0]) + (nums[1] * nums[1]);
    }
}
