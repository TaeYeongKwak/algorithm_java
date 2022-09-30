package bj14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static CogWheel[] cogWheels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cogWheels = new CogWheel[5];
        for (int i = 1; i <= 4; i++){
            String status = br.readLine();
            cogWheels[i] = new CogWheel(i, status);
        }

        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int direct = Integer.parseInt(st.nextToken());

            turnSaw(i, direct);

            for (int d = 1; d < 5; d++){
                cogWheels[d].turn();
            }
        }

        int result = 0;
        for (int d = 1; d < 5; d++){
            int score = 1 << d - 1;
            if (cogWheels[d].upSaw() == 1){
                result += score;
            }
        }

        System.out.println(result);

    }

    static void turnSaw(int i, int direct){
        cogWheels[i].visited = true;
        cogWheels[i].direction = direct;
        if (i + 1 < 5 && cogWheels[i].rightSaw() != cogWheels[i + 1].leftSaw() && !cogWheels[i + 1].visited){
            turnSaw(i + 1, -direct);
        }
        if (i - 1 > 0 && cogWheels[i].leftSaw() != cogWheels[i - 1].rightSaw() && !cogWheels[i - 1].visited){
            turnSaw(i - 1, -direct);
        }
    }


}

class CogWheel{
    int idx;
    Deque<Integer>[] sawtooth;
    int direction;
    boolean visited;

    public CogWheel(int idx, String status) {
        this.idx = idx;
        sawtooth = new LinkedList[3];
        direction = 0;
        visited = false;
        for (int i = 0; i < 3; i++){
            sawtooth[i] = new LinkedList<>();
        }

        String[] sawStatus = status.split("");
        for (int i = 0; i < 8;i++){
            if (i < 2){
                sawtooth[0].offerLast(Integer.parseInt(sawStatus[i]));
            }
            else if (i < 6){
                sawtooth[1].offerLast(Integer.parseInt(sawStatus[i]));
            }
            else{
                sawtooth[2].offerLast(Integer.parseInt(sawStatus[i]));
            }
        }
    }

    public int leftSaw(){
        return sawtooth[2].peekFirst();
    }

    public int rightSaw(){
        return sawtooth[1].peekFirst();
    }

    public int upSaw(){
        return sawtooth[0].peekFirst();
    }

    public void turn(){
        if (direction == 1){
            sawtooth[0].offerFirst(sawtooth[2].pollLast());
            sawtooth[2].offerFirst(sawtooth[1].pollLast());
            sawtooth[1].offerFirst(sawtooth[0].pollLast());
        }
        else if (direction == -1){
            sawtooth[0].offerLast(sawtooth[1].pollFirst());
            sawtooth[1].offerLast(sawtooth[2].pollFirst());
            sawtooth[2].offerLast(sawtooth[0].pollFirst());
        }
        direction = 0;
        visited = false;
    }
}
