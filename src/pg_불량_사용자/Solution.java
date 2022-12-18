package pg_불량_사용자;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    static Set<Integer> set;
    static String[] userId;
    static String[] bannedId;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        userId = user_id;
        bannedId = banned_id;
        set = new HashSet<>();
        dfs(0, 0);
        answer = set.size();
        return answer;
    }

    static void dfs(int r, int bitMask){
        if(r == bannedId.length){
            set.add(bitMask);
            return;
        }

        for(int i = 0; i < userId.length; i++){
            int bit = 1 << i;
            if(((bitMask & bit) == 0) && checkBannedId(userId[i], bannedId[r])){
                dfs(r + 1, bitMask + bit);
            }
        }
    }

    static boolean checkBannedId(String user, String ban){
        if(ban.length() != user.length()){
            return false;
        }

        for(int i = 0; i < ban.length(); i++){
            if(ban.charAt(i) == '*'){
                continue;
            }

            if(user.charAt(i) != ban.charAt(i)){
                return false;
            }
        }

        return true;
    }
}
