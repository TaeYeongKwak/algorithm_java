package bj11656;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        List<String> list = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            list.add(word.substring(i));
        }

        Collections.sort(list);

        for(String suffix : list){
            bw.write(suffix);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
