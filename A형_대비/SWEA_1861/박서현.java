import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
     
    static int N, maxLen, min;
    static int[][] rooms, memo;
    static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
  
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
          
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
             
            rooms = new int[N][N];
            memo = new int[N][N];
             
            maxLen = 0;
            min = Integer.MAX_VALUE;
             
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    rooms[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    int len = go(i, j);
                     
                    if(maxLen < len) {
                        maxLen = len;
                        min = rooms[i][j];
                    }
                    else if(maxLen == len) {
                        min = Math.min(min, rooms[i][j]);
                    }
                }
            }
             
            sb.append("#").append(t).append(" ").append(min).append(" ").append(maxLen).append("\n");
        }
        System.out.println(sb);
    }
     
    public static int go(int x, int y) {
 
        int cnt = 1;
         
        for(int i=0; i<deltas.length; i++) {
            int r = x + deltas[i][0];
            int c = y + deltas[i][1];
             
            if(r<0 || r>=N || c<0 || c>=N || rooms[r][c] - rooms[x][y] != 1) continue;
             
            if(memo[r][c] > 0) {
                cnt += memo[r][c];
            }
            else cnt += go(r, c);
            break;
        }
         
        return memo[x][y] = cnt;
    }
 
}
