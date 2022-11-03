package pg_카펫;

public class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int all = brown + yellow;
        int height = 3;
        int width = all;
        while(height <= width){
            if(all % height != 0){
                height++;
                continue;
            }
            width = all / height;
            int b = 2 * (width + height - 2);
            int y = (width - 2) * (height - 2);

            if(b == brown && y == yellow){
                break;
            }
            height++;
        }

        answer = new int[]{width, height};
        return answer;
    }
}
