package test_etw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1861 {
	
	static int answer, n, search;
	static int[][] map;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	private static void bfs(int i, int j, int v) {
		int count = 0;
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {i,j, v});
		
		while(!queue.isEmpty()) {
			int[] test = queue.poll();
			int x = test[0];
			int y = test[1];
			int z = test[2];
			
			for (int k = 0; k < 4; k++) {
				int ni = x+dx[k];
				int nj = y+dy[k];
				if (0 <= ni && ni < n && 0 <= nj && nj < n) {
					if (map[ni][nj] == z+1) {
						count++;
						queue.offer(new int[] {ni,nj,z+1});
					}
				}
			}
		}
		
//		System.out.println(v+" "+count);
//		System.out.println(search);
		
		if (count > search) {
			answer = v;
			search = count;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			search = Integer.MIN_VALUE;
			
			answer = Integer.MAX_VALUE;
			
			n = Integer.parseInt(in.readLine());

			map = new int[n][n];
			
			//각 좌표별로 저장할 값. 0을 없애기 위헤 +1로 한다.
			int[][] arr = new int[(n*n)+1][];
			
			//미리 찾은 값들을 넣어놓자. 이러면 시간복잡도 감소. 예를 들어 1에서 2와 3을 탐색했다면 0이 아니게 될테고, 그러면 찾을 필요가 없지.
			int[] find = new int[(n*n)+1];
			
			for (int i = 0; i < n; i++) {
				String[] test= in.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(test[j]);
					arr[map[i][j]] = new int[] {i,j};
				}
			}
			//map 생성 완료
			//System.out.println(arr.length);
			
			//1부터 n*n까지의 값에 대한 좌표를 미리 저장. 이러면 for문 돌려서 찾는 미친짓은 안해도 된다.
//			for (int i = 0; i < arr.length; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			for (int i = 1; i < arr.length; i++) {
				int x = arr[i][0];
				int y = arr[i][1];
				
				//이제 여기서 bfs를 굴린다.
				//단, find 값이 0인 경우 즉, 탐색을 해본 적이 없는 경우에만
				
				if(find[i] == 0) {
					bfs(x,y,i);
				}
			}
			
			System.out.println("#"+tc+" "+answer+" "+(search+1));
			//System.out.println();
		}
	}

}
