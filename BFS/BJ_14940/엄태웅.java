import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] o = in.readLine().split(" ");
		int n = Integer.parseInt(o[0]);
		int m = Integer.parseInt(o[1]);
		
		int[][] map = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < n; i++) {
			String[]test = in.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				if (Integer.parseInt(test[j]) == 2) {x = i; y = j;}
				map[i][j] = Integer.parseInt(test[j]);
			}
		}
		
		//System.out.println(Arrays.deepToString(map));
		//여기까지 맵 생성 완료
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {x,y});
		visited[x][y] = true;
		map[x][y] = 0;
		
		while (!queue.isEmpty()) {
			int[] find = queue.poll();
			int findx = find[0];
			int findy = find[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = findx+dx[i];
				int ny = findy+dy[i];
				if(0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
					map[nx][ny] = map[findx][findy] + 1;
					queue.offer(new int[] {nx,ny});
					visited[nx][ny] = true;
				}
				
			}
	}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					map[i][j] = -1;
					System.out.print(map[i][j]+" ");
				}
				else {System.out.print(map[i][j]+" ");}
			}
			System.out.println();
		}

	}

}
