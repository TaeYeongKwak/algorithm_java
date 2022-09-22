package pg_사라지는_발판;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] board = new int[R][C];
        for (int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] aloc = new int[2];
        int[] bloc = new int[2];

        st = new StringTokenizer(br.readLine());
        aloc[0] = Integer.parseInt(st.nextToken());
        aloc[1] = Integer.parseInt(st.nextToken());
        bloc[0] = Integer.parseInt(st.nextToken());
        bloc[1] = Integer.parseInt(st.nextToken());

        Solution s = new Solution();
        int result = s.solution(board, aloc, bloc);
        System.out.println(result);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] aResult, bResult;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        aResult = new int[]{Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2};
        bResult = new int[]{Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2};
        move(aloc, bloc, board, 0, 0);
        answer = Math.min(aResult[0] + aResult[1], bResult[0] + bResult[1]);
        return answer;
    }

    static void move(int[] aloc, int[] bloc, int[][] board, int aCnt, int bCnt){
        int[][] copyBoard = copyBoard(board);

        boolean canMove = false;
        int[] tempPoint = {-1, -1};

        for (int i = 0; i < 4; i++){
            int nx = aloc[1] + dx[i];
            int ny = aloc[0] + dy[i];

            if (0 <= ny && ny < copyBoard.length && 0 <= nx && nx < copyBoard[0].length){
                if (copyBoard[ny][nx] == 1){
                    if (ny == bloc[0] && nx == bloc[1]){
                        tempPoint[0] = ny;
                        tempPoint[1] = nx;
                    }
                    else{
                        canMove = true;
                        if (aloc[0] == bloc[0] && aloc[1] == bloc[1]){
                            aCnt++;
                            end(true, aCnt, bCnt);
                            return;
                        }

                        copyBoard[aloc[0]][aloc[1]] = 0;
                        int[] aPoint = new int[]{ny, nx};
                        int[][] bPoints = getMovePoint(bloc, aPoint, copyBoard);
                        boolean bFlag = false;
                        for (int[] bPoint : bPoints){
                            if (bPoint[0] != -1){
                                bFlag = true;
                                break;
                            }
                        }
                        if (!bFlag){
                            aCnt++;
                            end(true, aCnt, bCnt);
                            return;
                        }
                        if (copyBoard[ny][nx] == 0){
                            end(false, aCnt, bCnt);
                            return;
                        }

                        for (int[] bPoint : bPoints){
                            if (bPoint[0] != -1){
                                move(aPoint, bPoint, copyBoard, aCnt + 1, bCnt + 1);
                            }
                        }
                    }
                }
            }
        }

        // bloc 를 제외한 다른 곳으로 갈 곳이 없었을 경우
        if (!canMove){
            // bloc로 갈 수 있었을 경우
            if (tempPoint[0] != -1){
                int[][] bPoints = getMovePoint(bloc, tempPoint, copyBoard);
                boolean bFlag = false;
                for (int[] bPoint : bPoints){
                    if (bPoint[0] != -1){
                        bFlag = true;
                        break;
                    }
                }
                // b가 갈 곳이 없을 경우
                if (!bFlag){
                    aCnt++;
                    end(true, aCnt, bCnt);
                    return;
                }
                // b가 갈 곳이 있을 경우
                else{
                    bCnt++;
                    end(false, aCnt, bCnt);
                    return;
                }
            }
            else{
                end(false, aCnt, bCnt);
                return;
            }
        }
    }

    static void end(boolean isAWin, int aCnt, int bCnt){
        if (isAWin){
            if (aResult[0] > aCnt || (aResult[0] == aCnt && bCnt > aResult[1])){
                aResult[0] = aCnt;
                aResult[1] = bCnt;
            }
        }
        else{
            if (bResult[1] > bCnt || (bResult[1] == bCnt && aCnt > bResult[0])){
                bResult[0] = aCnt;
                bResult[1] = bCnt;
            }
        }
    }

    static int[][] getMovePoint(int[] bloc, int[] avoid, int[][] board){
        int[][] possibleMovePoint = new int[4][2];
        boolean canMove = false;
        int[] temp = {-1, -1};
        for (int i = 0; i < 4; i++){
            int nx = bloc[1] + dx[i];
            int ny = bloc[0] + dy[i];

            boolean flag = false;
            if (0 <= ny && ny < board.length && 0 <= nx && nx < board[0].length){
                if (board[ny][nx] == 1){
                    if (ny == avoid[0] && nx == avoid[1]){
                        temp[0] = ny;
                        temp[1] = nx;
                        continue;
                    }
                    possibleMovePoint[i][0] = ny;
                    possibleMovePoint[i][1] = nx;
                    flag = true;
                    canMove = true;
                }
            }

            if (!flag){
                possibleMovePoint[i][0] = possibleMovePoint[i][1] = -1;
            }

        }
        if (!canMove){
            possibleMovePoint[0][0] = temp[0];
            possibleMovePoint[0][1] = temp[1];
        }

        board[bloc[0]][bloc[1]] = 0;
        return possibleMovePoint;
    }

    static int[][] copyBoard(int[][] board){
        int[][] returnBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            returnBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return returnBoard;
    }
}