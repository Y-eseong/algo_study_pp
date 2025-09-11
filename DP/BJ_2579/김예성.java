import java.io.*;
 
public class Main {

	static int max = Integer.MIN_VALUE;
	static int n;
	static int[] score;
	static int[][] dp;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        score = new int[n];
        dp = new int[n][2];
        
        for(int i=0; i<n; i++) {
        	score[i] = Integer.parseInt(br.readLine());
        }
        
        if(n < 2) {
        	sb.append(score[0]);
        }
        else {        	
        	dp[0][0] = score[0];
        	dp[0][1] = 0;
        	dp[1][0] = score[1];
        	dp[1][1] = dp[0][0] + score[1];
        	
        	for(int i=2; i<n; i++) {
        		dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + score[i];
        		dp[i][1] = dp[i-1][0] + score[i];
        	}
        	
        	max = Math.max(dp[n-1][0], dp[n-1][1]);
        	sb.append(max);
        }
        System.out.println(sb);
    }
}