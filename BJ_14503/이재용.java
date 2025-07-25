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
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 북, 동, 남, 서
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] cleaned = new boolean[N][M];
		queue.offer(new int[] { r, c, d });
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			int cd = current[2];
			if (!cleaned[cr][cc]) {
				cleaned[cr][cc] = true;
			}
			boolean notCleaned = false;
			for (int[] i : dir) {
				int nr = cr + i[0];
				int nc = cc + i[1];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && !cleaned[nr][nc] && map[nr][nc] == 0) {
					notCleaned = true;
					break;
				}
			}
			if (!notCleaned) {
				int nr = cr - dir[cd][0];
				int nc = cc - dir[cd][1];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == 0) {
					queue.offer(new int[] { nr, nc, cd });
				}
				else {
					continue;
				}
			} else {
				int nd = cd;
				for (int i = 0; i < 4; i++) {
					if (nd == 0) {
						nd = 3;
					} else {
						nd--;
					}
					int nr = cr + dir[nd][0];
					int nc = cc + dir[nd][1];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && !cleaned[nr][nc] && map[nr][nc] == 0) {
						queue.offer(new int[] { nr, nc, nd });
						break;
					}
				}
			}
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(cleaned[i][j]) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
