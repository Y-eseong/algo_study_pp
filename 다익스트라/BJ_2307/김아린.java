import java.io.*;
import java.util.*;

public class 도로검문 {
	static class Edge implements Comparable<Edge> {
		int node, w;

		public Edge(int node, int w) {
			this.node = node;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int N, M, INF = 1_000_000;
	static int[] dist;
	static ArrayList<Edge>[] eList;
//	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		eList = new ArrayList[N+1];
		
		for (int n = 0; n <= N; n++) {
			eList[n] = new ArrayList<>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			eList[a].add(new Edge(b, w));
			eList[b].add(new Edge(a, w));
		}
		
		int shortest = findDist(1, 0);
		int temp = 0;
		for (int i = 2; i <= N; i++) {
			int c = findDist(1, i);
			temp = Math.max(c, temp);
		}
		
		System.out.println(temp == INF ? -1 : temp - shortest);
		
	}
	
	public static int findDist(int start, int ban) {
		resetDist(dist);
		boolean[] visited = new boolean[N+1];
		boolean[] bList = new boolean[N+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		bList[ban] = true;
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(bList[edge.node] || visited[edge.node]) continue;
			visited[edge.node] = true;
			
			for (Edge e : eList[edge.node]) {
				if(dist[e.node] >= dist[edge.node] + e.w) {
					dist[e.node] = dist[edge.node] + e.w;
					pq.offer(new Edge(e.node, dist[e.node]));
				}
			}
			
		}
		
		return dist[N];
	}
	
	public static void resetDist(int[] d) {
		for (int i = 0; i < d.length; i++) {
			d[i] = INF;
		}
	}

}
