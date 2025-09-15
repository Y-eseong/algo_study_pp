import java.io.*;
import java.util.*;

public class 맥주_마시면서_걸어가기 {
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int T, N, B = 50, MAX = 1000;
	static Pos[] nodes;
	static boolean[][] dist;
	static String result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			// 0 집 n 락페 
			dist = new boolean[N+2][N+2];
			nodes = new Pos[N+2];
			
			// house
			st = new StringTokenizer(br.readLine());
			nodes[0] = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				nodes[n] = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			// rock festival
			st = new StringTokenizer(br.readLine());
			nodes[N+1] = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			for (int i = 0; i <= N+1; i++) {
				Pos p1 = nodes[i];
				for (int j = i + 1; j <=  N+1; j++) {
					Pos p2 = nodes[j];
					if(manhatan(p1.x, p2.x, p1.y, p2.y) <= 1000) {
						dist[i][j] = dist[j][i] = true;
					}
				}
			}
			
			warshall();
			
			sb.append(dist[0][N+1] ? "happy" : "sad").append("\n");
		}
		System.out.println(sb);
	}
	
	public static void warshall() {
		for (int k = 0; k < N+2; k++) {
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					if(dist[i][k] && dist[k][j]) {
						dist[i][j] = true;
					}
				}
			}
		}
	}
	
	public static int manhatan(int x1, int x2, int y1, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
