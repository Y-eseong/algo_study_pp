import java.io.*;
import java.util.*;

public class 김예성 {

	static class Pair{
		int to;
		int weight;
		
		public Pair(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int n, m, k;
	static List<Pair>[] graph;
	static PriorityQueue<Pair> pq;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		m = Integer.parseInt(in[1]);
		k = Integer.parseInt(in[2]);
		
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		String[] in2 = br.readLine().split(" ");
		List<Integer> list = new ArrayList<>();
		visited = new boolean[n+1];

		for(int i=0; i<k; i++) {
			list.add(Integer.parseInt(in2[i]));
			visited[Integer.parseInt(in2[i])] = true;
		}
		
		pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Pair(v, w));
			graph[v].add(new Pair(u, w));
			
			if(list.contains(u)) {
				pq.offer(new Pair(v, w));
			}
			
			if(list.contains(v)) {
				pq.offer(new Pair(u, w));
			}
		}
		
		int sum = 0;
		
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			int to = cur.to;
			int weight = cur.weight;
			
			if(!visited[to]) {
				visited[to] = true;
				sum += weight;
				for(Pair x : graph[to]) {
					pq.offer(x);
				}
			}
		}
		sb.append(sum);
		System.out.println(sb);
	}
	
}
