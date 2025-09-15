import java.io.*;
import java.util.*;

public class 계단오르기 {
	static int N, MODUL = 1_000_000;
	static int[] data, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		data = new int[N+1];
		dp = new int[N+1];
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		
		if (N >= 1) dp[1] = data[1];
        if (N >= 2) dp[2] = data[1] + data[2];
        if (N >= 3) dp[3] = Math.max(data[1] + data[3], data[2] + data[3]);

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + data[i - 1]) + data[i];
        }

        System.out.println(dp[N]);
		
	}

}
