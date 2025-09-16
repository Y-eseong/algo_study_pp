import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
  
public class Solution {
      
    static int T, N, min;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // 사방탐색
       
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;
          
        T = Integer.parseInt(br.readLine());
          
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
              
            map = new int[N][N];
            visited = new boolean[N][N];
              
            min = Integer.MAX_VALUE;
              
            for(int n=0; n<N; n++) {
                s = br.readLine();
                  
                for(int i=0; i<N; i++) {
                    map[n][i] = s.charAt(i) - '0';
                }
            }
  
            getRoute();
              
            sb.append("#").append(t).append(" ").append(map[N-1][N-1]).append("\n");
        }
          
        System.out.println(sb);
    }
     
    public static void getRoute() {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> map[o[0]][o[1]]));
         
        queue.offer(new int[] {0,0});
        visited[0][0] = true;
         
         
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
             
            for(int i=0; i<deltas.length; i++) {
                int r = current[0] + deltas[i][0];
                int c = current[1] + deltas[i][1];
                 
                if(r>=N || r<0 || c>=N || c<0) continue;
                 
                if(!visited[r][c]) {
                    visited[r][c] = true;
                    map[r][c] += map[current[0]][current[1]];
                    queue.offer(new int[] {r, c});
                }
            }
             
        }
    }
       
}
