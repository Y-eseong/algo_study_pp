import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {
	
	static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M; // 가로 길이
	static int N; // 세로 길이
	static int K; // 심어진 배추의 수
	
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static int[][] arr;
	static boolean[][] visited; 
	static Queue<Pair> q;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로 길이
			N = Integer.parseInt(st.nextToken()); // 세로 길이
			K = Integer.parseInt(st.nextToken()); // 심어진 배추의 수
			
			arr = new int[N][M];
			visited = new boolean[N][M];
			
			q = new LinkedList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine()); // 입력: x, y
				int x, y;
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				arr[y][x] = 1;
			}
			
			int bfsCnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {
						q.add(new Pair(j, i)); // pair 생성할 때 유의, 생성자로 x, y 순서로 받음.
						visited[i][j] = true;
						bfs(i, j); // bfs는 for index와 같이 y, x 순으로 넘겨줌.
						bfsCnt++;
					}
				}
			}
			System.out.println(bfsCnt);
		}
	}
	
	public static void bfs(int i, int j) {
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				if (visited[ny][nx] || arr[ny][nx] == 0) continue;
				
				q.add(new Pair(nx, ny));
				visited[ny][nx] = true;
			}
		}
	}
}