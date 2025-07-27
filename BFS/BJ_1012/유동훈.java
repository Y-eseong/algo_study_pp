import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	static int M; // ���� ����
	static int N; // ���� ����
	static int K; // �ɾ��� ������ ��
	
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
			M = Integer.parseInt(st.nextToken()); // ���� ����
			N = Integer.parseInt(st.nextToken()); // ���� ����
			K = Integer.parseInt(st.nextToken()); // �ɾ��� ������ ��
			
			arr = new int[N][M];
			visited = new boolean[N][M];
			
			q = new LinkedList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine()); // �Է�: x, y
				int x, y;
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				arr[y][x] = 1;
			}
			
			int bfsCnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {
						q.add(new Pair(j, i)); // pair ������ �� ����, �����ڷ� x, y ������ ����.
						visited[i][j] = true;
						bfs(i, j); // bfs�� for index�� ���� y, x ������ �Ѱ���.
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

