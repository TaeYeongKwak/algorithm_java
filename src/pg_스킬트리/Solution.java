package pg_스킬트리;

public class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = -1;
        int[] alpha = new int[26];
        for(int i = 0; i < skill.length(); i++){
            int a = skill.charAt(i) - 'A';
            alpha[a] = i + 1;
        }

        answer = 0;
        int order;
        boolean check;
        for(String skillTree : skill_trees){
            order = 1;
            check = true;
            for(int i = 0; i < skillTree.length(); i++){
                int c = skillTree.charAt(i) - 'A';
                if(alpha[c] > order){
                    check = false;
                    break;
                }
                else if(alpha[c] == order){
                    order++;
                }
            }

            if(check){
                answer++;
            }
        }

        return answer;
    }
}
