import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int from, to;
		double w;

		public Edge(int from, int to, double w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	static class Node {
		double x, y;

		public Node(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int N, parents[], INF = 1_000_000;
	static double total;
	static Node[] nList;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nList = new Node[N];
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nList[i] = new Node(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			parents[i] = i;
		}
		
		
		
		for (int n = 0; n < N - 1; n++) {
			Node startNode = nList[n];
			for (int m = n + 1; m < N; m++) {
				Node endNode = nList[m];
				double dist = hypot(startNode.x, endNode.x, startNode.y, endNode.y);
				pq.offer(new Edge(n, m, dist));
			}
		}
		
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(union(e.from, e.to)) {
				total += e.w;
			}
			
		}
		System.out.println(String.format("%.2f", total));
		
	}
	
	public static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[aRoot] = bRoot;
		return true;
	}
	
	public static double hypot(double x1, double x2, double y1, double y2) {
		return Math.hypot(x1 - x2, y1 - y2);
	}
	
	public static double manhatan(double x1, double x2, double y1, double y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
