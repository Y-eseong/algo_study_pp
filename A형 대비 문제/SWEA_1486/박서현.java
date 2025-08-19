import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
 
    static int T, N, B;
    static int[] heights;
 
    static int minHeight;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
             
            heights = new int[N];
             
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                heights[n] = Integer.parseInt(st.nextToken());
            }
 
            minHeight = Integer.MAX_VALUE;
 
            subset(0, 0);
             
            sb.append("#").append(t).append(" ").append(minHeight).append("\n");
        }
        System.out.println(sb);
    }
 
    private static void subset(int nth, int sum) {
        if (sum - B >= minHeight) {
            return;
        }
 
        if (nth == N) {
            if (sum >= B) {
                minHeight = Math.min(sum - B, minHeight);
            }
            return;
        }
 
        subset(nth + 1, sum);
        subset(nth + 1, sum + heights[nth]);
    }
 
}
