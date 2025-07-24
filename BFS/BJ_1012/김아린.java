import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int T;
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 어떤 배추에 지렁이 있으면 인접한 다른 배추로 이동할 수 있다. 상하좌우만 가능
		// 최소로 필요한 지렁이의 수를 구하는 문제 -> BFS
		// 0 = 배추 없음 1 = 배추 있음
		
		// T = 테스트 케이스 개수
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int M, N, K, X, Y, cnt = 0;
			// 배추밭 가로길이 M 세로길이 N
			// 배추 있는 위치 개수 K, 배추 위치 X,Y
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				map[Y][X] = 1;
			}
			
			for(int m = 0; m < N; m++) {
				for(int n = 0; n < M; n++) {
					if(map[m][n] == 1) {
						find(m, n, M , N);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
		
	}
	
	public static void find(int y, int x, int M, int N) {
		q.add(new int[] {y, x});
		// 방문한적 없는 1을 찾으면 큐에 넣음 (방문 후에 1을 2로 업데이트)
		while(!q.isEmpty()) {
			int[] start = q.poll();
			for(int i = 0; i < 4; i++) {
				int dy = start[0] + dir[i][0];
				int dx = start[1] + dir[i][1];
				if (dy >= 0 && dy < N && dx >= 0 && dx < M && map[dy][dx] == 1) {
					q.add(new int[] {dy, dx});
					map[dy][dx] = 2; // 방문했던 노드는 2로 업데이
				}
			}
		}
	}
	
}
