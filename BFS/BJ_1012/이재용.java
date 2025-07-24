import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int T, M, N, K;

	static int[][] map;

	static Deque<int[]> queue = new ArrayDeque<>();
	static boolean[][] visited;

	// 상, 하, 좌, 우
	static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				map[Y][X] = 1;
			}

			visited = new boolean[N][M];

			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visited[i][j]) { // 방문하지 않은 배추를 발견하면 bfs!
						bfs(i, j);
						count++;
					}
				}
			}

			sb.append(count).append("\n");
			
		}
		System.out.print(sb);
	}

	static void bfs(int row, int col) {

		queue.offer(new int[] { row, col });

		while (!queue.isEmpty()) { // 큐가 빌 때까지 돌아라!
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			if (!visited[cr][cc]) { // 방문하지 않았으면,
				visited[cr][cc] = true;

				for (int[] dir : directions) {
					int nr = cr + dir[0];
					int nc = cc + dir[1];

					if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {
						queue.offer(new int[] { nr, nc });
					}
				}
			}
		}
	}
}
