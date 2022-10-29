package bj2170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Line[] lines = new Line[N];
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines[n] = new Line(x, y);
        }

        Arrays.sort(lines);

        int min = lines[0].x;
        int max = lines[0].y;
        int length = max - min;
        for (int n = 0; n < N; n++){
            Line current = lines[n];
            if (min <= current.x && current.y <= max){
                continue;
            }
            else if (current.x < max){
                length += current.y - max;
            }
            else{
                length += current.y - current.x;
            }
            min = current.x;
            max = current.y;
        }

        System.out.println(length);
    }
}

class Line implements Comparable<Line>{

    int x, y;

    public Line(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Line o) {
        int com = Integer.compare(x, o.x);
        return (com == 0)? Integer.compare(y, o.y) : com;
    }
}
