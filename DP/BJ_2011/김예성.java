import java.io.*;
import java.util.*;

public class 김예성 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String in = br.readLine();
		
		int[] dp = new int[in.length()+1];
		dp[0] = 1;
		
		for(int i=1; i<=in.length(); i++) {
			int now = in.charAt(i-1) - '0';
			if(now >= 1 && now <= 9) {
				dp[i] = dp[i-1];
				dp[i] %= 1000000;
			}
			
			if(i == 1)
				continue;
			
			int pv = in.charAt(i-2) - '0';
			int num = pv*10 + now;
			if(num >= 10 && num <= 26) {
				dp[i] += dp[i-2];
				dp[i] %= 1000000;
			}
		}
		sb.append(dp[in.length()]);
		System.out.println(sb);
	}
	
}
