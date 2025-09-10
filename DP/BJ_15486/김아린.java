import java.io.*;
import java.util.*;

public class 퇴사2 {
	static int N;
	static int[] dp;
	static int[][] data;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		data = new int[N + 1][2];
		dp = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int n = 1; n <= N; n++) {
			dp[n] = Math.max(dp[n], dp[n - 1]);
			int time = data[n][0];
			if (n + time <= N + 1)
				dp[n + time] = Math.max(dp[n] + data[n][1], dp[n + time]);
		}

		System.out.println(Math.max(dp[N], dp[N+1]));

	}

}
