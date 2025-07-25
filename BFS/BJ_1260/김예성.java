import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] visited; 
	static StringBuilder sb = new StringBuilder();
	static int[][] graph;
	static int n, m, v;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		v = Integer.parseInt(input[2]);
		visited = new boolean[n+1];
		graph = new int[n+1][n+1];
		
		for(int i = 0; i < m; i++) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			int b = Integer.parseInt(in[1]);
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		dfs(v);
		sb.append("\n");
		visited = new boolean[n+1];
		bfs(v);
		System.out.println(sb);
	}
	
	static void dfs(int next) {
		visited[next] = true;
		sb.append(next).append(" ");
		
		for(int i = 1; i <=n; i++) {
			if(!visited[i] && graph[next][i] == 1) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int next) {
		q.offer(next);
		visited[next] = true;
		while(!q.isEmpty()) {
			next = q.poll();
			sb.append(next).append(" ");
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && graph[next][i] == 1) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
	}
}
