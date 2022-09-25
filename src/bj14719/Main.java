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

        int[] field = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int w = 0; w < W; w++){
            field[w] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int w = 0; w < W; w++){
            int middle = field[w];
            int left = middle;
            int right = middle;

            for (int l = w - 1; l >= 0; l--){
                if (field[l] > middle){
                    left = Math.max(left, field[l]);
                }
            }

            for (int r = w + 1; r < W; r++){
                if (field[r] > middle){
                    right = Math.max(right, field[r]);
                }
            }

            int min = Math.min(left, right);
            if (min > middle){
                sum += min - middle;
            }
        }

        System.out.println(sum);
    }
}
