package bj1759;

import java.io.*;

public class Main {

    static int l, c;
    static String[] words;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] selectWord;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        selectWord = new boolean[26];
        visited = new boolean[26];

        String[] lc = br.readLine().split(" ");
        l = Integer.parseInt(lc[0]);
        c = Integer.parseInt(lc[1]);

        words = br.readLine().split(" ");
        for (String w : words){
            selectWord[(int)(w.charAt(0))-'a'] = true;
        }

        makePassword(0,0);

        bw.flush();
        bw.close();
        br.close();
    }

    static void makePassword(int s, int r) throws IOException {
        if (r == l){
            if (check()){
                String pw = getPassword();
                bw.write(pw + "\n");
            }
            return;
        }
        for (int i = s; i < 26; i++){
            if (!visited[i] && selectWord[i]){
                visited[i] = true;
                makePassword(i + 1,r + 1);
                visited[i] = false;
            }
        }
    }

    static String getPassword(){
        StringBuffer password = new StringBuffer("");
        for (int i = 0; i < 26; i++){
            if (visited[i]){
                password.append((char)(i + 97));
            }
        }
        return password.toString();
    }

    static boolean check(){

        boolean vowelResult = false;
        boolean consonantResult = false;
        int count = 0;
        for (int i = 0; i < 26; i++){
            if(visited[i]){
                if(i == 0 || i == 4 || i == 8 || i == 14 || i == 20){
                    vowelResult = true;
                }else{
                    count++;
                }
            }
        }

        if (count > 1)
            consonantResult = true;

        return vowelResult && consonantResult;
    }

}
