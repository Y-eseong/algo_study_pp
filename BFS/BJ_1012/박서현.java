import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static boolean[][] visited;
	static int[][] dm = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int count = 0;
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				
				int j = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				
				map[i][j] = 1;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==1 && !visited[i][j]) {
						bfs(i, j);
						count += 1;
					}
				}
			}
			
			System.out.println(count);	
		}
	}
	
	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		visited[i][j] = true;
		q.offer(new int[] {i, j});
		
		while(!q.isEmpty()) {
			int[] node = q.poll();
			
			for(int[] rc:dm) {
				int r = node[0] + rc[0];
				int c = node[1] + rc[1];
				
				if(r>=0 && r<map.length && c>=0 && c<map[0].length && map[r][c]==1 && !visited[r][c]) {
					q.offer(new int[] {r, c});
					visited[r][c] = true;
				}
			}
		}
	}
}
