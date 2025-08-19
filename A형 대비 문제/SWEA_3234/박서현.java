import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
     
    static int N, cnt;
    static int[] weight;
  
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
          
        for (int t = 1; t <= T; t++) {
 
            N = Integer.parseInt(br.readLine());
             
            weight = new int[N];
            cnt = 0;
             
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }
             
            permutation(0,0,0, new boolean[N]);
             
             
            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }
     
    public static void permutation(int pCnt, int left, int right, boolean[] visited) {
        if(pCnt == N) {
            cnt++;
            return;
        }
         
        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(right + weight[i] <= left) {
                    permutation(pCnt+1, left, right+weight[i], visited);
                }
                permutation(pCnt+1, left+weight[i], right, visited);
                visited[i] = false;
                 
            }
        }
    }
 
}
