package bj5430;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++){
            char[] commands = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String numArrayLine = br.readLine();
            String[] numArrayStr = numArrayLine.substring(1, numArrayLine.length()-1).split(",");
            bw.write(acCompile(commands, numArrayStr) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static String acCompile(char[] commands, String[] numArrayStr){

        boolean isForward = true;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < numArrayStr.length; i++){
            if (!numArrayStr[i].isBlank())
                deque.offerLast(Integer.parseInt(numArrayStr[i]));
        }

        for (char command : commands){
            if (command == 'R'){
                isForward = !isForward;
            }else if(command == 'D'){
                if (deque.isEmpty())
                    return "error";
                int x = (isForward)? deque.pollFirst(): deque.pollLast();
            }
        }
        StringBuffer sb = new StringBuffer("[");
        int size = deque.size();
        for (int i = 0; i < size; i++){
            sb.append(((isForward)? deque.pollFirst(): deque.pollLast()));
            if (i != size-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
