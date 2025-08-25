package test_etw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1249 {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		
		int INF = Integer.MAX_VALUE;
		
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(in.readLine());
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String[] test = in.readLine().split("");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(test[j]);
				}
			}
			//map 생성 완료
			
			//누적되는 값을 넣을 배열
			int[][] dist = new int[n][n];
			
			//dist에 모두 최대값을 넣고
			for (int i = 0; i < n; i++) {
				Arrays.fill(dist[i], INF);
			}
			
			//시작점은 무조건 0으로 시작
			dist[0][0] = 0;
			
			Queue<int[]> queue = new LinkedList<>();
			
			//첫 번째는 i좌표, 두 번째는 j좌표
			queue.offer(new int[] {0,0});
			
			//최소값을 구하는, 방향이 정해지지 않았기에 방문여부는 필요 없다
			while(!queue.isEmpty()) {
				int[] z = queue.poll();
				int x = z[0]; //i값
				int y = z[1]; //j값
				
				for (int k = 0; k < 4; k++) {
					int nx = x+dx[k];
					int ny = y+dy[k];
					
					if (0 <= nx && nx < n && 0 <= ny && ny < n) {
						//거리에 필요한 값은 map에 있고, 누적되는 값은 dist에 들어있다
						int next = dist[x][y] + map[nx][ny];
						if (next < dist[nx][ny]) {
							dist[nx][ny] = next;
							queue.offer(new int[] {nx,ny});
						}
					}
				}
			}
			System.out.println("#"+tc+" "+dist[n-1][n-1]);
		}
	}
}
