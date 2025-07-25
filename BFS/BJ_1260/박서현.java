import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
		
		visited = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
			
		}
		
		for (int i = 0; i <= N; i++) {
            graph.get(i).sort(null);
        }
		
		dfs(V);
		
		sb.append("\n");
		visited = new boolean[N+1];
		bfs(V);
		
		
		System.out.println(sb);
		
	}
	
	public static void dfs(int idx) {
		visited[idx] = true;
		
		sb.append(idx + " ");
		
		for(int node : graph.get(idx)) {
			if(!visited[node]) dfs(node);
		}
	}
	
	public static void bfs(int idx) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(idx);
		visited[idx] = true;
		
		while(!q.isEmpty()) {
			int nodeIdx = q.poll();
			
			for(int i=0; i<graph.get(nodeIdx).size(); i++) {
				int node = graph.get(nodeIdx).get(i);
				
				if(!visited[node]) {
					q.offer(node);
					visited[node] = true;
				}
			}
			
			sb.append(nodeIdx + " ");
		}
		
	}
}
