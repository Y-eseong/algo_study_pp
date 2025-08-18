import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 보급로 {
	static int T, N, minTime;
	static int[][] map, visited, memo;
	static int[][] diList = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			memo = new int[N][N];
			minTime = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
					memo[i][j] = INF;
				}
			}
			
			calcTime(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void calcTime(int x, int y, int sum) {
		if(minTime <= sum) return;
		if(sum >= memo[y][x]) return;
		memo[y][x] = sum;
		
		if(x == N - 1 && y == N - 1) {
			minTime = Math.min(minTime, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int dix = x + diList[i][1];
			int diy = y + diList[i][0];
			if(dix < 0 || dix >= N || diy < 0 || diy >= N) continue;
			calcTime(dix, diy, sum + map[diy][dix]);
		}
		
	}

}
