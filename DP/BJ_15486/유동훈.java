package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] times = new int[N + 2];
		int[] prices = new int[N + 2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			prices[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 2];
		
		for (int i = 1; i <= N; i++) {
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
			
			int end = i + times[i];
			if (end <= N + 1) {
				dp[end] = Math.max(dp[end], dp[i] + prices[i]);
			}
		}
		
		System.out.println(Math.max(dp[N], dp[N + 1]));
	}

}
