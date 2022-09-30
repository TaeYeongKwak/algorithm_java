package bj1063;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, int[]> commands = new HashMap<>();
        commands.put("R", new int[]{1, 0});
        commands.put("L", new int[]{-1, 0});
        commands.put("B", new int[]{0, -1});
        commands.put("T", new int[]{0, 1});
        commands.put("RT", new int[]{1, 1});
        commands.put("LT", new int[]{-1, 1});
        commands.put("RB", new int[]{1, -1});
        commands.put("LB", new int[]{-1, -1});

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] king = getPoint(st.nextToken());
        int[] stone = getPoint(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++){
            int[] d = commands.get(br.readLine());
            int kdx = king[0] + d[0];
            int kdy = king[1] + d[1];

            if (0 < kdx && kdx < 9 && 0 < kdy && kdy < 9){
                if (stone[0] == kdx && stone[1] == kdy){
                    int sdx = stone[0] + d[0];
                    int sdy = stone[1] + d[1];

                    if (0 < sdx && sdx < 9 && 0 < sdy && sdy < 9){
                        stone[0] = sdx;
                        stone[1] = sdy;
                    }
                    else{
                        continue;
                    }
                }
                king[0] = kdx;
                king[1] = kdy;
            }
        }

        System.out.println(getPointStr(king));
        System.out.println(getPointStr(stone));
    }

    static int[] getPoint(String p){
        String[] pp = p.split("");
        int x = (pp[0].charAt(0) - 'A') + 1;
        int y = Integer.parseInt(pp[1]);
        return new int[]{x, y};
    }

    static String getPointStr(int[] p){
        StringBuilder sb = new StringBuilder();
        sb.append((char) (p[0] - 1 + 'A'));
        sb.append(p[1]);
        return sb.toString();
    }
}
