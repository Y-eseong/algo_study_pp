package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2011 {
	
    static String s;
    static int N;
    static long[] memo;
    
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine().trim();
        N = s.length();
        memo = new long[N + 1];
        Arrays.fill(memo, -1);

        long ways = dfs(0);
        System.out.println(ways % 1000000);
    }

    static long dfs(int idx) {
        if (idx == N) return 1;
        
        if (memo[idx] != -1) return memo[idx];

        if (s.charAt(idx) == '0') return memo[idx] = 0; // 0 단독 불가

        long res = 0;

        // 1자리
        res += (dfs(idx + 1) % 1000000);

        // 2자리
        if (idx + 1 < N) {
            int val = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if (val >= 10 && val <= 26) {
                res += (dfs(idx + 2) % 1000000);
            }
        }

        return memo[idx] = res;
    }
}