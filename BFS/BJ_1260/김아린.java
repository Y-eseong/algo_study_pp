import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[] visited;
	static int N, M, V;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 노드 숫자를 그대로 쓰고 싶어서 배열에  + 1을 함
		map = new int[N + 1][N + 1];
		visited = new int[N + 1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			map[dy][dx] = map[dx][dy] = 1;
		}
		
		
		dfs(V);
		System.out.println();
		visited = new int[N + 1];
		bfs(V);
		
		
	}
	
	public static void dfs(int start) {
		
		visited[start] = 1;
		System.out.print(start + " ");
		for(int i = 1; i < N + 1; i++) {
			// 맵에 간선이 연결되어있다고 저장된 노드가 방문되지 않았던 노드라면 재귀
			if(map[start][i] == 1 && visited[i] == 0) dfs(i);
		}
		
	}
	
	public static void bfs(int start) {
		// 첫 방문 노드를 스타트 큐에 넣고 방문했다고 저장
		q.add(start);
		visited[start] = 1;
		
		while(!q.isEmpty()) {
			// 방문 노드를 스타드 지점으로 잡으면서 큐에서는 뺌
			start = q.poll();
			System.out.print(start + " ");
			for(int i = 1; i < N + 1; i++) {
				// 맵에 간선이 연결되어있다고 저장된 노드가 방문되지 않았던 노드라면 큐에 넣고 방문했다고 표시
				if(map[start][i] == 1 && visited[i] == 0) {
					q.add(i);
					visited[i] = 1;
				}
			}
		}
		
	}

}
