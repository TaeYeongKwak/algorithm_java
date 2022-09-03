package bj20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, size;
    static Deque<Conveyor> top;
    static Deque<Conveyor> under;
    static Conveyor[] conveyors;
    static int brokenCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        size = (2 * N) + 1;
        conveyors = new Conveyor[size];
        top = new LinkedList<>();
        under = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < size; n++){
            int a = Integer.parseInt(st.nextToken());
            conveyors[n] = new Conveyor(n, a);
            if (top.size() < N){
                top.offerLast(conveyors[n]);
            }
            else{
                under.offerFirst(conveyors[n]);
            }
        }

        brokenCnt = 0;
        int result = 0;
        while(brokenCnt < K){
            result++;
            operateConveyorBelt();
        }

        System.out.println(result);
    }

    static void operateConveyorBelt(){
        // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한칸 회전한다.
        Conveyor takeDown = top.pollLast();
        takeDown.isThereRobot = false;
        under.offerLast(takeDown);
        top.offerFirst(under.pollFirst());
        top.peekLast().isThereRobot = false;

        // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
        int takeDownIdx = top.peekLast().idx;
        int raiseIdx = top.peekFirst().idx;

        int now = takeDownIdx - 1 > 0? takeDownIdx - 1 : size - 1;
        int right = now + 1 < size? now + 1 : 1;
        while(true){
            // 컨베이어 벨트 위에 로봇이 있고
            if (conveyors[now].isThereRobot) {
                // 다음 컨베이어 벨트에 로봇을 올려둘 수 있다면 로봇을 옮긴다.
                if (conveyors[right].isPossiblePut()){
                    conveyors[now].isThereRobot = false;
                    conveyors[right].putRobot();
                }
            }
            // 올리는 위치에 있는 로봇까지 모두 이동했다면 종료한다.
            if (now == raiseIdx) break;

            // 현재 위치와 로봇이 이동할 위치를 변경해준다.
            now = now - 1 > 0? now - 1 : size - 1;
            right = now + 1 < size? now + 1 : 1;
        }

        // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if (conveyors[raiseIdx].isPossiblePut()){
            conveyors[raiseIdx].putRobot();
        }

        // 4. 내구도가 0인 칸의 개수를 확인한다.
        int count = 0;
        for (int i = 1; i < size; i++){
            if (conveyors[i].durability == 0){
                count++;
            }
        }
        brokenCnt = count;
    }
}

class Conveyor{
    int idx;
    int durability;
    boolean isThereRobot;

    public Conveyor(int idx, int durability) {
        this.idx = idx;
        this.durability = durability;
        isThereRobot = false;
    }

    public boolean isPossiblePut(){
        return (durability > 0 && !isThereRobot);
    }

    public void putRobot(){
        this.durability--;
        this.isThereRobot = true;
    }
}
