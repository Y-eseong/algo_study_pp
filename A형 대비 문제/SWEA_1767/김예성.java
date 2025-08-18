import java.io.*;
import java.util.*;
 
public class Solution {
     
    //상하좌우
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
     
    static boolean[][] visited;
    static int result_pro, result_line;
    static int p_count, n;
    static int[][] proc;
    static List<Integer>[] core;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            visited = new boolean[n][n];
            p_count = 0;
            proc = new int[12][2];
                         
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1 && i!=0 && j!=0 && i!=n-1 && j!=n-1) {
                        proc[p_count][0] = i;
                        proc[p_count][1] = j;
                        p_count++;
                    }
                }
            }
//          for(int i=0; i<p_count; i++) {
//              System.out.println(Arrays.toString(proc[i]));
//          }
            core = new ArrayList[p_count];
            result_pro = Integer.MIN_VALUE;
            result_line = Integer.MAX_VALUE;
            for(int i=0; i<p_count; i++) {
                core[i] = new ArrayList<>();
                for(int z=0; z<4; z++) {
                    int nx = proc[i][0] + dir[z][0];
                    int ny = proc[i][1] + dir[z][1];
                    while(true) {
                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                            core[i].add(z);
                            break;
                        }
                        if(map[nx][ny] == 1) {
                            break;
                        }
                        else if(map[nx][ny] == 0) {
                            nx += dir[z][0];
                            ny += dir[z][1];
                        }
                    }
                }
//              System.out.println(core[i].toString());
            }
            go(0, 0, 0);
            if(result_line == Integer.MAX_VALUE)
                sb.append(0).append("\n");
            else
                sb.append(result_line).append("\n");
        }
        System.out.println(sb);
    }
     
    private static void go(int k, int p, int l) {
        if(p_count == k) {
            if(result_pro < p) {
                result_pro = p;
                result_line = l;
            }
            else if(result_pro == p) {
                if(result_line > l) {
                    result_line = l;
                }
            }
//          System.out.println(result_pro + " / "+ result_line);
            return;
        }
        if(core[k].isEmpty())
            go(k+1, p, l);
        for(int z : core[k]) {
            int nx = proc[k][0] + dir[z][0];
            int ny = proc[k][1] + dir[z][1];
            boolean check = false;
            int count = 0;
            while(true) {
                if(!visited[nx][ny]) {
                    nx += dir[z][0];
                    ny += dir[z][1];
                    count++;
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                        break;
                    }
                }
                else {
                    check = true;
                    break;
                }
            }
            if(check) {
                go(k+1, p, l);
            }
            else {
                for(int i=1; i<=count; i++) {
                    visited[proc[k][0] + i*dir[z][0]][proc[k][1] + i*dir[z][1]] = true;
                }
                go(k+1, p+1, l+count);
                for(int i=1; i<=count; i++) {
                    visited[proc[k][0] + i*dir[z][0]][proc[k][1] + i*dir[z][1]] = false;
                }
                go(k+1, p, l);
            }
        }
    }
}