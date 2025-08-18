import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class 프로세서_연결하기 {
    static int T, N, maxCore, minLine;
    static int[][] panel;
    static ArrayList<int[]> cores;
    static int[][] diList = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception, IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cores = new ArrayList<>();
            panel = new int[N][N];
            maxCore = 0;
            minLine = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    panel[i][j] = Integer.parseInt(st.nextToken());
                     
                    if(i > 0 && j > 0 && i < N - 1 && j < N - 1 && panel[i][j] == 1) {
                        cores.add(new int[] {i, j});
                    }
                }
            }
             
            findMax(0, 0, 0);
             
            System.out.println("#" + tc + " " + minLine);
        }
    }
     
    public static void findMax(int crrIdx, int coreSum, int lineSum) {
         
        if (coreSum + (cores.size() - crrIdx) < maxCore) return;
 
         
        if(crrIdx == cores.size()) {
            if(maxCore < coreSum) {
                maxCore = coreSum;
                minLine = lineSum;
            } else if(maxCore == coreSum) {
                minLine = Math.min(minLine, lineSum);
            }
            return;
        }
        int dir = cores.get(crrIdx)[0];
        int dic = cores.get(crrIdx)[1];
 
        for (int i = 0; i < 4; i++) {
            if(checkAvailable(dir, dic, i)) {
                int cnt = updatePanel(dir, dic, i, 2);
                findMax(crrIdx+1, coreSum+1, lineSum+cnt);
                updatePanel(dir, dic, i, 0);
            }
        }
        findMax(crrIdx+1, coreSum, lineSum);
    }
     
    public static boolean checkAvailable(int row, int col, int di) {
        int dir = row, dic = col;
         
        while(true) {
            dir += diList[di][0];
            dic += diList[di][1];
             
            if(dir < 0 || dic < 0 || dir >= N || dic >= N) return true;
            if(panel[dir][dic] != 0) return false;
            if(dir == 0 || dic == 0 || dir == N - 1 || dic == N - 1) return true;
        }
         
    }
     
    public static int updatePanel(int row, int col, int di, int num) {
        int dir = row, dic = col;
        int cnt = 0;
        while(true) {
            dir += diList[di][0];
            dic += diList[di][1];
             
            if(dir < 0 || dic < 0 || dir >= N || dic >= N) break;
            panel[dir][dic] = num;
            cnt++;
            if(dir == 0 || dic == 0 || dir == N - 1 || dic == N - 1) break;
        }
        return cnt;
    }
     
 
}
