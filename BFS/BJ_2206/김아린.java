import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[][][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M;
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[2][N][M];
		// 배열 받아옴
		for(int n = 0; n < N; n++) {
			String t = br.readLine();
			for(int m = 0; m < M; m++) {
				map[n][m] = Character.getNumericValue(t.charAt(m));
				
			}
		}
		
		System.out.print(bfs());
	}
	
	public static int bfs() {
		
		if(N == 1 && M == 1) return 1;
		
		q.add(new int[] {0, 0, 0});
		visited[0][0][0]++;
		
		while(true) {
			int[] temp = q.poll();
			for(int i = 0; i < 4; i++) {
				int w = temp[0];
				int dx = temp[2] + dir[i][1];
				int dy = temp[1] + dir[i][0];
				
				if(dx >= 0 && dx < M && dy >= 0 && dy < N) {
					if(map[dy][dx] == 1 && visited[1][dy][dx] == 0 && w == 0) {
						q.add(new int[] {1, dy, dx}); // 벽을 부순 2차원 배열에서 탐
						visited[1][dy][dx] = visited[0][temp[1]][temp[2]] + 1;
						if(dx == M - 1 && dy == N - 1) return visited[1][dy][dx];
					}
					else if(map[dy][dx] == 0 && visited[w][dy][dx] == 0) {
						q.add(new int[] {w, dy, dx}); // 다음 위치가 0이고 방문한적 없었다면 원래 있던 2차원 배열에서 계속 탐
						visited[w][dy][dx] = visited[w][temp[1]][temp[2]] + 1;
						if(dx == M - 1 && dy == N - 1) return visited[w][dy][dx];
					}
					
				}
			}
			
			if(q.isEmpty()) return -1;
		}
	}
	
}
