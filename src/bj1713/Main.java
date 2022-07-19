package bj1713;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int sCnt = Integer.parseInt(br.readLine());
        Photo[] photos = new Photo[101];
        List<Photo> list = new ArrayList<>();
        String[] sNums = br.readLine().split(" ");
        for (int i = 0; i < sCnt; i++){
            int currentSNum = Integer.parseInt(sNums[i]);
            //해당 후보가 최초 호출 시
            if (photos[currentSNum] == null){
                photos[currentSNum] = new Photo(currentSNum, 0, 0, false);
            }
            //해당 후보가 사진틀에 있을 경우
            if (photos[currentSNum].isIn){
                photos[currentSNum].count++;
            }
            //해당 후보가 사진 틀에 없음
            else{
                //사진틀이 가득 찬 경우
                if (list.size() == n){
                    Collections.sort(list);
                    list.get(0).isIn = false;
                    Photo photo = list.remove(0);
                }
                //사진틀에 여유가 있는 경우
                photos[currentSNum].isIn = true;
                photos[currentSNum].count = 1;
                photos[currentSNum].timestamp = i;
                list.add(photos[currentSNum]);
            }
        }

        Comparator<Photo> comp = (o1, o2) -> Integer.compare(o1.studentNum, o2.studentNum);

        Collections.sort(list, comp);

        for (Photo p : list){
            bw.write(p + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Photo implements Comparable<Photo>{
    int studentNum;
    int count;
    int timestamp;
    boolean isIn;

    public Photo(int studentNum, int count, int timestamp, boolean isIn) {
        this.studentNum = studentNum;
        this.count = count;
        this.timestamp = timestamp;
        this.isIn = isIn;
    }

    @Override
    public int compareTo(Photo o) {
        int comp = Integer.compare(count, o.count);
        if (comp == 0){
            return Integer.compare(timestamp, o.timestamp);
        }else{
            return comp;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(studentNum);
    }
}
