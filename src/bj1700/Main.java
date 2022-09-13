package bj1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] order;
    static Item[] items;
    static int[] socket;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        order = new int[K + 1];
        items = new Item[K + 1];
        socket = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int k = 1; k < K + 1; k++){
            order[k] = Integer.parseInt(st.nextToken());
            if (items[order[k]] != null){
                items[order[k]].count++;
            }
            else{
                items[order[k]] = new Item();
            }
            items[order[k]].nextOrder.offer(k);
        }

        for (int i = 1; i < K + 1; i++){
            int itemNum = order[i];
            items[itemNum].count--;
            items[itemNum].nextOrder.poll();
            // 현재 물건이 멀티탭에 있는지 확인
            if (items[itemNum].inSocket) continue;

            // 현재 물건이 멀티탭에 없다면,
            boolean flag = false;
            // 1. 멀티탭에 빈자리가 있을 경우
            for (int s = 0; s < N; s++){
                // 빈자리에 물건을 넣는다.
                if (socket[s] == 0){
                    socket[s] = itemNum;
                    items[itemNum].inSocket = true;
                    flag = true;
                    break;
                }
            }
            // 2. 멀티탭에 빈자리가 없을 경우
            if (!flag){
                // 현재 멀티탭에 있는 물건중 가장 나중에 사용되는 물건을 뺀다.
                int outSocketNum = 0;
                for (int s = 0; s < N; s++){
                    if (items[socket[outSocketNum]].getNextOrder() < items[socket[s]].getNextOrder()){
                        outSocketNum = s;
                    }
                }

                items[socket[outSocketNum]].inSocket = false;
                socket[outSocketNum] = itemNum;
                items[itemNum].inSocket = true;
                result++;
            }
        }

        System.out.println(result);
    }
}

class Item{

    int count;
    boolean inSocket;
    Queue<Integer> nextOrder;

    public Item() {
        this.count = 1;
        inSocket = false;
        nextOrder = new LinkedList<>();
    }

    public int getNextOrder(){
        if (nextOrder.isEmpty())
            return 101;
        return nextOrder.peek();
    }
}