import java.io.*;
import java.util.*;

public class 전기가_부족해 {
	static class Node implements Comparable<Node> {
		int u, v, w;

		public Node(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int N, M, K;
	static int[] installed;
	static int[] parents;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		installed = new int[K];
		parents = new int[N+1];
		
		for (int n = 1; n <= N; n++) {
			parents[n] = n;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			parents[Integer.parseInt(st.nextToken())] = 0;
		}
		
		for (int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(u, v , w));
		}
		
		int total = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(union(node.u, node.v)) {
				total += node.w;
				
				int gCnt = 0;
				for (int i : parents) {
					if(i == -1) {
						gCnt++;
					}
				}
				
				if (gCnt == N) break;
			}
		}
		
		System.out.println(total);
	}
	
	public static int find(int a) {
		if(parents[a] == -1) return -1;
		if(a == parents[a]) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		if(aRoot == -1) parents[b] = aRoot;
		else if(bRoot == -1) parents[a] = bRoot;
		else parents[aRoot] = bRoot;
		
		return true;
	}
	
}
