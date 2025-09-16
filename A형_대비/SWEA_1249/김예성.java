import java.io.*;
import java.util.*;
 
 
public class Solution {
     
    static class Pair{
        int x;
        int y;
        int c;
         
        public Pair(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
     
    static Queue<Pair> q = new ArrayDeque<>();
    // 우하좌상
    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static int n;
    static int[][] map, sum;
     
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                String in = br.readLine();
                for(int j=0; j<n; j++) {
                    map[i][j] = in.charAt(j) - '0';
                }
            }
            sum = new int[n][n];
            for(int i=0; i<n; i++)
                Arrays.fill(sum[i], Integer.MAX_VALUE);
             
            q.offer(new Pair(0, 0, 0));
            sum[0][0] = map[0][0];
            bfs();
            sb.append(sum[n-1][n-1]).append("\n");
        }
        System.out.println(sb);
    }
 
    private static void bfs() {
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int c = cur.c;
             
            c += 2;
            if(c>3)
                c -= 4;
             
            for(int z=0; z<4; z++) {
                if(z == c)
                    continue;
                int nx = x + dir[z][0];
                int ny = y + dir[z][1];
                if(nx<0 || ny<0 || nx>=n || ny>=n)
                    continue;
                if(sum[nx][ny] <= sum[x][y] + map[nx][ny]) {
                    continue;
                }
                sum[nx][ny] = sum[x][y] + map[nx][ny];
                q.offer(new Pair(nx, ny, z));
            }
        }
    }
}