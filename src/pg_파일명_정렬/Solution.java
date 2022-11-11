package pg_파일명_정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        List<File> fileList = new ArrayList<>();
        for(String file : files){
            fileList.add(new File(file));
        }

        Collections.sort(fileList);
        answer = new String[files.length];
        for(int i = 0; i < files.length; i++){
            answer[i] = fileList.get(i).toString();
        }
        return answer;
    }
}

class File implements Comparable<File>{
    String name;
    String head;
    int number;
    String tail;

    public File(String file){
        this.name = file;
        boolean flag = false;
        int startNum = 0;
        number = -1;
        for(int i = 0; i < file.length(); i++){
            int c = (int) file.charAt(i);
            if(!flag && 48 <= c && c <= 57){
                flag = true;
                head = file.substring(0, i).toLowerCase();
                startNum = i;
            }
            else if(flag && (48 > c || c > 57)){
                number = Integer.parseInt(file.substring(startNum, i));
                tail = file.substring(i);
                break;
            }
        }

        if(number == -1){
            number = Integer.parseInt(file.substring(startNum, file.length()));
        }
    }

    @Override
    public int compareTo(File o){
        int x = head.compareTo(o.head);
        return x == 0? Integer.compare(this.number, o.number) : x;
    }

    @Override
    public String toString(){
        return name;
    }
}
