package bj1927;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        MyMinHeap queue = new MyMinHeap();
        for (int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x != 0) {
                queue.offer(x);
            }else{
                bw.write(queue.poll() + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class MyMinHeap{
    ArrayList<Integer> list;

    MyMinHeap(){
        list = new ArrayList<>();
        list.add(0);
    }

    public void offer(int num){
        // 1. leaf 마지막에 삽입
        list.add(num);
        // 2. 부모와 비교 후 조건에 맞지 않으면 Swap
        // 3. 조건이 만족되거나 Root 까지 반복
        int current = list.size() - 1;
        int parent = current / 2;
        while (true){
            if (parent == 0 || list.get(parent) <= list.get(current)){
                break;
            }

            int temp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, temp);

            current = parent;
            parent = current / 2;
        }

    }

    public int poll(){
        // list가 비었을 경우
        if (isEmpty()){
            return 0;
        }
        // 1. Root 에 leaf 마지막 데이터 가져옴
        int top = list.get(1);
        list.set(1, list.get(list.size()-1));
        list.remove(list.size()-1);
        // 2. 자식과 비교 후 조건이 맞지 않으면 Swap
        // 3. 조건이 만족되거나 leaf 까지 반복
        int currentPos = 1;
        while (true) {
            int leftPos = currentPos * 2;
            int rightPos = currentPos * 2 + 1;
            // 왼쪽 먼저 확인
            if (leftPos >= list.size()){
                break;
            }

            int minValue = list.get(leftPos);
            int minPos = leftPos;

            // 오른쪽 확인
            if (rightPos < list.size() && minValue > list.get(rightPos)){
                minValue = list.get(rightPos);
                minPos = rightPos;
            }

            // Swap
            if (list.get(currentPos) > minValue){
                int temp = list.get(currentPos);
                list.set(currentPos, list.get(minPos));
                list.set(minPos, temp);
            }
            currentPos = minPos;
        }
        // 4. 값 반환
        return top;
    }

    public boolean isEmpty(){
        return (list.size() == 1);
    }
}