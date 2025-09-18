package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13302 {
	static final int INF = 1_000_000_000;

    static int N, M;
    static boolean[] holiday;       // true면 그 날은 휴일 (돈 안 쓰고 그냥 넘어감)
    static int[][] memo;            // memo[day][coupon] = 최소 비용 (없으면 -1)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        holiday = new boolean[N + 1];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int d = Integer.parseInt(st.nextToken());
                if (1 <= d && d <= N) holiday[d] = true;
            }
        }

        memo = new int[N + 2][50];
        for (int i = 0; i <= N + 1; i++) Arrays.fill(memo[i], -1);

        System.out.println(dfs(1, 0));
    }
    
    static int dfs(int day, int c) {
        if (day > N) return 0;
        if (memo[day][c] != -1) return memo[day][c];

        int res = INF;

        // 휴일은 그냥 다음 날로 (비용 0)
        if (holiday[day]) {
            res = Math.min(res, dfs(day + 1, c));
            return memo[day][c] = res;
        }

        // 쿠폰 3장으로 무료 1일
        if (c >= 3) {
            res = Math.min(res, dfs(day + 1, c - 3));
        }

        // 1일권
        res = Math.min(res, 10_000 + dfs(day + 1, c));

        // 3일권 (쿠폰 +1)
        res = Math.min(res, 25_000 + dfs(Math.min(N + 1, day + 3), c + 1));

        // 5일권 (쿠폰 +2)
        res = Math.min(res, 37_000 + dfs(Math.min(N + 1, day + 5), c + 2));

        return memo[day][c] = res;
    }
}