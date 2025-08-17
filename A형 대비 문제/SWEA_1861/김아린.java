import java.io.*;
import java.util.*;

public class 정사각형_방 {
    static int T, N;
    static int[][] map, dp;
    static final int[][] DIR = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            dp  = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int bestLen = 0;
            int bestRoom = Integer.MAX_VALUE;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    int len = dfs(y, x);
                    int startNum = map[y][x];
                    if (len > bestLen || (len == bestLen && startNum < bestRoom)) {
                        bestLen = len;
                        bestRoom = startNum;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(bestRoom).append(" ").append(bestLen).append("\n");
        }
        System.out.print(sb);
    }

      static int dfs(int y, int x) {
        if (dp[y][x] != 0) return dp[y][x];
        dp[y][x] = 1; // 최소 자기 자신
        for (int[] d : DIR) {
            int ny = y + d[0], nx = x + d[1];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
            // 이동 조건은 "정확히 +1"
            if (map[ny][nx] == map[y][x] + 1) {
                dp[y][x] = Math.max(dp[y][x], 1 + dfs(ny, nx));
            }
        }
        return dp[y][x];
    }
}
