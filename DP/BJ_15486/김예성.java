import java.io.*;
import java.util.*;

public class 김예성 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n+1];
		dp[n] = 0;
		for(int i=n-1; i>=0; i--) {
			if(n-i >= arr[i][0]) {
				dp[i] = Math.max(dp[i+1], arr[i][1] + dp[i+arr[i][0]]);
			}
			else
				dp[i] = dp[i+1];
		}
		sb.append(dp[0]);
		System.out.println(sb);
	}
}
