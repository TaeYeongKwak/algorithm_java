package bj_5086;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = 0;
        int m = 0;
        while(true){
            n = sc.nextInt();
            m = sc.nextInt();

            if(n == 0 && m == 0){
                break;
            }

            boolean[] check = new boolean[2];
            if (n % m == 0){
                check[0] = true;
            }
            if(m % n == 0){
                check[1] = true;
            }

            if(check[0] && !check[1]){
                bw.write("multiple\n");
            }else if(!check[0] && check[1]){
                bw.write("factor\n");
            }else if(!check[0] && !check[1]){
                bw.write("neither\n");
            }

        }

        bw.flush();
        bw.close();

    }
}
