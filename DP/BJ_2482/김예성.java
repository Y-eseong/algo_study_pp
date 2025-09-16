import java.io.*;
import java.util.*;

public class 김예성 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+1][k+1];
		
		dp[1][0] = 1;
		dp[1][1] = 1;
		
		for(int i=2; i<=n; i++) {
			for(int j=1; j<=k; j++) {
				if(j == 1)
					dp[i][j] = i;
				else {
					if(i > j) {
						dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % 1000000003;
					}
				}
			}
		}
		if(k == 1) sb.append(n);
		else sb.append((dp[n-3][k-1] + dp[n-1][k]) % 1000000003);
		System.out.println(sb);
	}
}
