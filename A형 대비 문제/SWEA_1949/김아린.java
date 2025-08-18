import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 등산로_조성 {
	static int T, N, K, maxLen, heighest;
	static int[][] map, visited;
	static int[][] diList = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static List<int[]> list;

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			maxLen = Integer.MIN_VALUE;
			heighest = Integer.MIN_VALUE;

			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (heighest < map[i][j]) {
						heighest = map[i][j];
					}
				}
			}
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (heighest == map[i][j]) {
						list.add(new int[] { i, j }); // y, x
					}
				}
			}

			visited = new int[N][N];
			for (int i = 0; i < list.size(); i++) {
				int x = list.get(i)[1];
				int y = list.get(i)[0];
				visited[y][x] = 1;
				path(y, x, 1, 0);
				visited[y][x] = 0;
			}

			sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
		}
		System.out.print(sb);
	}

	public static void path(int y, int x, int cnt, int isUsed) {
		maxLen = Math.max(cnt, maxLen);

		for (int i = 0; i < diList.length; i++) {
			int diy = y + diList[i][0];
			int dix = x + diList[i][1];

			if (dix < 0 || dix >= N || diy < 0 || diy >= N)
				continue;
			 if (visited[diy][dix] == 1) continue;

			if (map[diy][dix] < map[y][x]) {
				visited[diy][dix] = 1;
				path(diy, dix, cnt+1, isUsed);
				visited[diy][dix] = 0;
			}
			else if (isUsed == 0) {
				
				int original = map[diy][dix];
				int want = map[y][x] - 1;
				
				if (original - K <= want) {
					map[diy][dix] = want;
					visited[diy][dix] = 1;
					path(diy, dix, cnt+1, 1);
					visited[diy][dix] = 0;
					map[diy][dix] = original;
				}

			}

		}
	}

}
