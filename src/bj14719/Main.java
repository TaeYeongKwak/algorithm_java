package bj14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int result = 0;
        int temp = 0;
        int leftBlock = 0;
        st = new StringTokenizer(br.readLine());
        for (int w = 0; w < W; w++){
            int blockHeight = Integer.parseInt(st.nextToken());

            if (blockHeight >= leftBlock){
                leftBlock = blockHeight;
                result += temp;
                temp = 0;
            }
            else if (blockHeight < leftBlock){
                temp += leftBlock - blockHeight;
            }


        }
    }
}
