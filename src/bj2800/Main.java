package bj2800;

import java.io.*;
import java.util.*;

public class Main {

    static char[] cArray;
    static List<int[]> bracketIndex;
    static boolean[] selected;
    static Set<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        cArray = S.toCharArray();

        Stack<Integer> stack = new Stack<>();
        bracketIndex = new ArrayList<>();
        for (int i = 0; i < cArray.length; i++){
            if (cArray[i] == '('){
                stack.push(i);
            }
            else if (cArray[i] == ')'){
                int lastIndex = stack.pop();
                bracketIndex.add(new int[]{lastIndex, i});
            }
        }

        selected = new boolean[cArray.length];
        result = new TreeSet<>();
        dfs(0);

        for (String s : result){
            if (s.equals(S)) continue;
            bw.write(s + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int r){
        if (r == bracketIndex.size()){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cArray.length; i++){
                if (!selected[i]){
                    sb.append(cArray[i]);
                }
            }
            result.add(sb.toString());
            return;
        }

        int[] index = bracketIndex.get(r);
        selected[index[0]] = true;
        selected[index[1]] = true;
        dfs(r + 1);
        selected[index[0]] = false;
        selected[index[1]] = false;
        dfs(r + 1);
    }
}
