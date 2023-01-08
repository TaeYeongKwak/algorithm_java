package bj1181;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Set<String> set = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(o -> o));
        for(int i = 0; i < N; i++){
            set.add(br.readLine());
        }

        for (String s : set){
            bw.write(s);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
