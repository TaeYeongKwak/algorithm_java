package bj9202;

import java.io.*;
import java.util.Arrays;

public class Main {

    static int N;
    static char[][] charMap;
    static boolean[] visitedWord;
    static boolean[][] visited;
    static Node rootNode;
    static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        String[] wordDic = new String[N];
        for (int i = 0; i < N; i++){
            wordDic[i] = br.readLine();
        }
        Arrays.sort(wordDic);

        rootNode = new Node(false, -1);
        //단어 트라이 생성
        for (int i = 0; i < N; i++){
            char[] word = wordDic[i].toCharArray();
            int size = word.length;
            Node node = rootNode;
            for (int j = 0; j < size; j++){
                node = node.setNode(word[j], (j == (size - 1)), i);
            }
        }
        br.readLine();

        // 단어 지도
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++){
            // 맵 입력받기
            visitedWord = new boolean[N];
            visited = new boolean[4][4];
            charMap = new char[4][4];

            for (int y = 0; y < 4; y++){
                String mapLine = br.readLine();
                for (int x = 0; x < 4; x++){
                    charMap[y][x] = mapLine.charAt(x);
                }
            }
            if (i < T-1) br.readLine();

            // 단어 찾기
            for (int y = 0; y < 4; y++){
                for (int x = 0; x < 4; x++){
                    if (rootNode.getNode(charMap[y][x]) != null){
                        searchWord(new int[]{x, y}, rootNode.getNode(charMap[y][x]));
                    }
                }
            }

            // 찾은 단어로 정답 계산
            int[] answer = new int[3];
            for (int j = 0; j < N; j++){
                if (visitedWord[j]){
                    //찾은 단어 수
                    answer[2] += 1;
                    //찾은 단어에 대한 점수
                    int size = wordDic[j].length();
                    answer[0] += score[size];
                    //찾은 단어중 가장 긴 단어
                    if (wordDic[answer[1]].length() < wordDic[j].length()){
                        answer[1] = j;
                    }
                }
            }

            bw.write(answer[0] + " " + wordDic[answer[1]] + " " + answer[2] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void searchWord(int[] point, Node node){
        visited[point[1]][point[0]] = true;
        //단어를 찾았을 경우
        if (node.isEndOfWord){
            // 해당 단어가 처음 찾는 단어일 경우
            if (!visitedWord[node.wordIdx]){
                visitedWord[node.wordIdx] = true;
            }
        }

        for (int i = 0; i < 8; i++){
            int nx = point[0] + dx[i];
            int ny = point[1] + dy[i];

            if (0 <= nx && nx < 4 && 0 <= ny && ny< 4){
                if (!visited[ny][nx] && node.getNode(charMap[ny][nx]) != null){
                    searchWord(new int[]{nx, ny}, node.getNode(charMap[ny][nx]));
                }
            }
        }
        visited[point[1]][point[0]] = false;
    }
}

class Node{
    Node[] childNode;
    boolean isEndOfWord;
    int wordIdx;

    Node(boolean isEndOfWord, int wordIdx){
        this.childNode = new Node[26];
        this.isEndOfWord = isEndOfWord;
        this.wordIdx = wordIdx;
    }

    public Node getNode(char c){
        return childNode[c - 'A'];
    }

    public Node setNode(char c, boolean isEndOfWord, int idx){
        // 해당 문자에 대한 노드가 없을 경우
        if (getNode(c) == null){
            Node node = new Node(isEndOfWord, ((isEndOfWord)? idx : -1));
            return childNode[c - 'A'] = node;
        }
        // 해당 문자에 대한 노드가 있을 경우
        else{
            return childNode[c - 'A'];
        }
    }
}
