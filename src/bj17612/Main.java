package bj17612;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static PriorityQueue<CashRegister> cashRegisters;
    static PriorityQueue<Member> members;
    static CashRegister[] cashRegisterInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cashRegisters = new PriorityQueue<>();
        cashRegisterInfo = new CashRegister[K + 1];

        for (int k = 1; k < K + 1; k++){
            cashRegisterInfo[k] = new CashRegister(k);
            cashRegisters.offer(cashRegisterInfo[k]);
        }


        members = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            long memberIdx = Long.parseLong(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int k = cashRegisters.poll().num;
            int exitTime = cashRegisterInfo[k].sumTime(w);
            cashRegisters.offer(cashRegisterInfo[k]);
            members.offer(new Member(memberIdx, w, k, exitTime));
        }

        long i = 1;
        long sum = 0;
        while(!members.isEmpty()){
            Member member = members.poll();
            sum += member.id * i;
            i++;
        }

        System.out.println(sum);
    }
}

class Member implements Comparable<Member>{
    long id;
    int w;
    int cashRegister;
    int exitTime;

    public Member(long id, int w, int cashRegister, int exitTime) {
        this.id = id;
        this.w = w;
        this.cashRegister = cashRegister;
        this.exitTime = exitTime;
    }

    @Override
    public int compareTo(Member o) {
        int x = Integer.compare(exitTime, o.exitTime);
        return (x == 0)? Integer.compare(o.cashRegister, cashRegister) : x;
    }
}

class CashRegister implements Comparable<CashRegister>{
    int num;
    int time;

    public CashRegister(int num) {
        this.num = num;
        this.time = 0;
    }

    public int sumTime(int w){
        this.time += w;
        return time;
    }

    @Override
    public int compareTo(CashRegister o) {
        int x = Integer.compare(time, o.time);
        return (x == 0)? Integer.compare(num, o.num) : x;
    }
}
