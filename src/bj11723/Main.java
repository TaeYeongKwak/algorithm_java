package bj11723;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x;
        MySet mySet = new MySet();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command){
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    mySet.add(x);
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    mySet.remove(x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    bw.write(mySet.check(x) + "");
                    bw.newLine();
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    mySet.toggle(x);
                    break;
                case "all":
                    mySet.all();
                    break;
                case "empty":
                    mySet.empty();
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}

class MySet{

    static final int ALL = 2097151;

    private int bit;

    public MySet(){
        bit = 0;
    }

    public void add(int x){
        int add = 1 << x;
        bit |= add;
    }

    public void remove(int x){
        int remove = 1 << x;
        if ((bit & remove) > 0)
            bit -= remove;
    }

    public int check(int x){
        int check = 1 << x;
        return ((bit & check) > 0)? 1 : 0;
    }

    public void toggle(int x){
        int check = 1 << x;
        if((bit & check) > 0){
            remove(x);
        }
        else{
            add(x);
        }
    }

    public void all(){
        bit = ALL;
    }

    public void empty(){
        bit = 0;
    }
}
