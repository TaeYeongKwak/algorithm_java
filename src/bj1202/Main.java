package bj1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());


        List<Long> bags = new ArrayList<>();
        List<Jewelry> jewelries = new ArrayList<>();

        long result = 0;

        for (long n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            long weight = Integer.parseInt(st.nextToken());
            long value = Integer.parseInt(st.nextToken());
            Jewelry jewelry = new Jewelry(weight, value);
            jewelries.add(jewelry);
        }

        for (long k = 0; k < K; k++){
            bags.add(Long.parseLong(br.readLine()));
        }
        Collections.sort(jewelries);
        Collections.sort(bags);

        PriorityQueue<Long> jewelryPQ = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        for (int k = 0; k < K; k++){
            while(j < N && jewelries.get(j).weight <= bags.get(k)){
                jewelryPQ.offer(jewelries.get(j++).value);
            }
            if (!jewelryPQ.isEmpty())
                result += jewelryPQ.poll();
        }

        System.out.println(result);
    }
}

class Jewelry implements Comparable<Jewelry>{
    long weight;
    long value;

    public Jewelry(long weight, long value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Jewelry o) {
        return Long.compare(weight, o.weight);
    }
}
