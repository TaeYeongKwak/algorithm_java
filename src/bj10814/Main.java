package bj10814;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Member[] members = new Member[N];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members[n] = new Member(n, age, name);
        }

        Arrays.sort(members);

        for(Member member : members){
            bw.write(member.toString());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }
}

class Member implements Comparable<Member>{
    int order;
    int age;
    String name;

    public Member(int order, int age, String name) {
        this.order = order;
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Member o) {
        int x = Integer.compare(age, o.age);
        return x == 0? Integer.compare(order, o.order) : x;
    }

    @Override
    public String toString() {
        return age + " " + name;
    }
}
