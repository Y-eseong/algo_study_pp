import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
  
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
  
    static int T, N, max, min;
    static ArrayList<int[]> core;
    static int[][] processor;
    static int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};
  
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
          
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            max = 0;
            min = Integer.MAX_VALUE;
            core = new ArrayList<>();
             
            processor = new int[N][N];
             
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                 
                for(int j=0; j<N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                     
                    if(n == 1) {
                        processor[i][j] = n;
                        if(i>0 && i<N-1 && j>0 && j<N-1) core.add(new int[] {i, j});
                    }
                }
                 
            }
 
            getRoute(0, 0, 0);
             
              
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
     
    public static void getRoute(int idx, int cCnt, int lCnt) {
        if(core.size() - idx + cCnt < max) return;
         
        if(idx == core.size()) {
            if(max < cCnt) {
                max = cCnt;
                min = lCnt;
            }
            else if(max == cCnt) {
                min = Math.min(min, lCnt);
            }
             
            return;
        }
         
        int r = core.get(idx)[0];
        int c = core.get(idx)[1];
         
        for(int d=0; d<4; d++) {
            if(isAvailable(r, c, d)) {
                int cnt = setRoute(r, c, d, 2);
                getRoute(idx+1, cCnt+1, lCnt+cnt);
                setRoute(r, c, d, 0);
            }
            else {
                getRoute(idx+1, cCnt, lCnt);
            }
        }
 
    }
     
    public static boolean isAvailable(int r, int c, int d) {
        while(true) {
            r += dir[d][0];
            c += dir[d][1];
             
            if(r<0 || r>=N || c<0 || c>=N) break;
            else if(processor[r][c]>0) return false;
        }
         
        return true;
    }
     
    public static int setRoute(int r, int c, int d, int s) {
        int cnt = 0;
         
        while(true) {
            r += dir[d][0];
            c += dir[d][1];
             
            if(r<0 || r>=N || c<0 || c>=N) break;
            processor[r][c] = s;
            cnt++;
        }
         
        return cnt;
    }
  
}
