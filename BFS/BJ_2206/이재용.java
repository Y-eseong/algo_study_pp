import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		Queue<int[]> queue = new ArrayDeque<>();
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		boolean[][][] visited = new boolean[2][N][M];
		queue.offer(new int[] { 0, 0, 1, 0 }); // row, col, count, wall
		int ans = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			int cnt = current[2];
			int wl = current[3];
			if (cr == N - 1 && cc == M - 1) {
				System.out.println(cnt);
				return;
			}
			for (int[] dir : directions) {
				int nr = cr + dir[0];
				int nc = cc + dir[1];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[wl][nr][nc] && map[nr][nc] == 0) {
					visited[wl][nr][nc] = true;
					queue.offer(new int[] { nr, nc, cnt + 1, wl });
				}
				if (0 <= nr && nr < N && 0 <= nc && nc < M && wl == 0 && !visited[1][nr][nc] && map[nr][nc] == 1) {
					visited[1][nr][nc] = true;
					queue.offer(new int[] { nr, nc, cnt + 1, 1 });
				}
			}
		}
		System.out.println(-1);
	}
}
