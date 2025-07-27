import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 우선, 접근 방법이 잘못된 것 같습니다. 점심 먹기 전부터 지금까지 계속 코드 짜봤는데
 * 무한 루프에서 벗어날 수가 없습니다..
 * 아마 벽을 부순 횟수(breakCount)를 어떻게 갱신하느냐가 종료 조건에 가장 큰 영향을 미치는 것 같은데
 * 이 방법은 아예 해결될 기미가 보이질 않습니다.
 * 
	 if (board[ny][nx] == 1 && wallBreakCnt == 0) {
	    Q.add... // 큐에 현재 좌표 넣고
	    BFS 계속 진행
	    wallBreakCnt++;
		}
		
		if (board[ny][nx] == 1 && wallBreakCnt == 1) {
		    wallBreakCnt--;
		    continue;
		}
		
		} // BFS 끝
		
		1. 
		벽을 만났을 때, 해당 좌표를 Q2에 넣어서 부순 포인트를 가지고 있다가
		기존 Q의 진행이 끝나서 dist가 다 갱신된 상태일 때, Q2에 있는 걸 다시 Q에 넣고 다시 진행. -> dist가 더 작으면 갱신.
		
		2. 
		- Node(int x, int y, int cost) {...}
		- 좌표쌍과 우선순위를 도입해서 벽 부순 포인트에서 길을 부술 때마다 cost를 증가 시켜서 큐에 삽입
		    - 따라서 cost가 작은 것들부터 출력하고 벽 부순 포인트에서 길을 안부순 분기를 다시 진행
 * 
 * 차라리 성원님께서 말씀하신 것처럼 3차원 배열처럼 접근해야되지 않을까 생각해봅니다..
 * 
 */

public class BOJ_2206 {
	
	 static class Pair {
		public int x;
		public int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] dx = {1, 0, 0, -1};
		int[] dy = {0, 1, -1, 0};
				
		int[][] board = new int[N][M];
		int[][] dist = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				int a = line.charAt(j) - '0';
				if (a == 1) dist[i][j] = -1;
				board[i][j] = a;
			}
		}
		
		Queue<Pair> q = new LinkedList<>();
		Queue<Pair> q2 = new LinkedList<>();
		
		q.add(new Pair(0, 0));
		dist[0][0] = 1;
		visited[0][0] = true;
		int bfsCnt = 0;
		
		int breakCount = 0;
		do {
			while(!q.isEmpty()) {
				Pair cur = q.poll(); int cur_x = cur.x; int cur_y = cur.y;
				
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur_x + dx[dir]; int ny = cur_y + dy[dir];
					
					if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
					if (visited[ny][nx]) continue;
					if (board[ny][nx] == 1) { // 벽을 만나면
						if (breakCount == 0) {
							breakCount = 1; // 벽을 부수고
							dist[ny][nx] = dist[cur_y][cur_x] + 1;
							q.add(new Pair(nx, ny)); // BFS 계속 진행
							visited[ny][nx] = true;
							q2.add(new Pair(cur_x, cur_y)); // 임시 큐에 벽 만나기 직전 좌표 저장
							continue;
						} else {
							continue; // 이미 부순 벽이면 continue
						}
					}
					if (bfsCnt > 0 && dist[cur_y][cur_x] + 1 < dist[ny][nx]) {
						dist[ny][nx] = dist[cur_y][cur_x] + 1;
						q.add(new Pair(nx, ny)); // BFS 계속 진행
//						System.out.println("1111111111111111");
					} else if (bfsCnt == 0) {
						dist[ny][nx] = dist[cur_y][cur_x] + 1; // 벽이 아니라 0을 만났다면 
						visited[ny][nx] = true;
						q.add(new Pair(nx, ny)); // BFS 계속 진행
//						System.out.println("22222222222222222");
//						System.out.println("bb " + bfsCnt);
					} else {
//						System.out.println("33333333333333");
						continue;
					}
				}	
			}
			while (!q2.isEmpty()) { // 임시 큐에서 벽을 부순 지점을 다시 q에 넣기
				Pair temp = q2.poll();
				System.out.println("뿅~!");
				q.add(temp);
			}
			breakCount = 0; // 벽 부순 횟수 초기화
			bfsCnt++;
		} while (!q.isEmpty()); // 벽을 부숴서 분기가 나눠졌던 지점부터 다시 BFS 시작
		

//		System.out.println("디버깅====");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dist[i][j] + " ");
//			} System.out.println("\n");
//		}
//		System.out.println("디버깅====");
		
		
		boolean impossible = false; // 0인 곳들은  BFS 진행시에 dist 값들이 갱신되어야 함
		for (int i = 0; i < N; i++) { // 만약, dist 값이 0인 곳이 있다면 문제에서의 불가능한 경우에 해당함
			for (int j = 0; j < M; j++) { // 추가로 벽에 대한 dist 값은 -1, 초기 위치의 dist 값은 1
				if (dist[i][j] == 0) {
					System.out.println("-1");
					impossible = true;
					break;
				}
			}
			if (impossible) break;
		}
		
		if (!impossible) {
			System.out.println(dist[N - 1][M - 1]); // 최종 위치에서의 거리 값 출력
		}
	}
}