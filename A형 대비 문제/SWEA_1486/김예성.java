import java.io.*;
import java.util.*;
   
public class Solution {
     
    static int[] h;
    static int n, b;
    static int min;
     
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            sb.append("#").append(t).append(" ");
            // 입력 받기
            String[] in = br.readLine().split(" ");
            n = Integer.parseInt(in[0]);
            b = Integer.parseInt(in[1]);
            h = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }
             
            min = Integer.MAX_VALUE;
            backT(0, 0);
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
     
    private static void backT(int k, int sum) {
        if(k == n) {
            if(sum >= b)
                min = Math.min(min, sum - b);
            return;
        }
        // 앞에서부터 고르는 경우와 고르지 않는 경우
        backT(k+1, sum);
        backT(k+1, sum + h[k]);
    }
}