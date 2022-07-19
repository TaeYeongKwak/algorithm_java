package bj2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long t = Long.parseLong(br.readLine());
        long result = 0;
        int n = Integer.parseInt(br.readLine());
        String[] numArrayStr1 = br.readLine().split(" ");
        long[] numList1 = new long[n];
        for (int i = 0; i < n; i++){
            numList1[i] = Long.parseLong(numArrayStr1[i]);
        }

        int m = Integer.parseInt(br.readLine());
        String[] numArrayStr2 = br.readLine().split(" ");
        long[] numList2 = new long[m];
        for (int i = 0; i < m; i++){
            numList2[i] = Long.parseLong(numArrayStr2[i]);
        }

        ArrayList<Long> sumList1 = new ArrayList<>();
        calSumList(numList1, sumList1);

        ArrayList<Long> sumList2 = new ArrayList<>();
        calSumList(numList2, sumList2);

        Collections.sort(sumList1);
        Collections.sort(sumList2, Comparator.reverseOrder());

        int aPointer = 0;
        int bPointer = 0;
        while(true){
            long sum = sumList1.get(aPointer) + sumList2.get(bPointer);
            //합이 t보다 클 경우
            if (sum > t){
                bPointer++;
            }
            // 합이 t보다 작을 경우
            else if (sum < t){
                aPointer++;
            }
            // 합이 t와 같을 경우
            else if(sum == t){
                long countA = 0;
                long countB = 0;

                long currentA = sumList1.get(aPointer);
                while(aPointer < sumList1.size() && sumList1.get(aPointer) == currentA){
                    countA++;
                    aPointer++;
                }

                long currentB = sumList2.get(bPointer);
                while(bPointer < sumList2.size() && sumList2.get(bPointer) == currentB){
                    countB++;
                    bPointer++;
                }
                result += countA * countB;
            }
            //apointer 또는 bpointer가 m과 같아졌을 경우
            if (aPointer == sumList1.size() || bPointer == sumList2.size())
                break;
        }

        System.out.println(result);
    }

    static void calSumList(long[] numList, ArrayList<Long> list){
        for (int i = 0; i < numList.length; i++){
            long sum = 0;
            for (int j = i; j < numList.length; j++){
                sum += numList[j];
                list.add(sum);
            }
        }
    }
}
