package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16202 {
	public static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;
		
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static void make() {
		for (int i = 0; i <= N; i++) {
			parents[i] = -1;
		}
	}
	
	public static int find(int cur) {
		if (parents[cur] < 0) return cur;
		return parents[cur] = find(parents[cur]);
	}
	
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		
		if (aP == bP) return false;
		
		if (parents[aP] > parents[bP]) {
			int temp = aP;
			aP = bP;
			bP = temp;
		}
		
		if (parents[aP] == parents[bP]) {
			parents[aP]--;
		}
		
		parents[bP] = aP;
		return true;
	}
	
	static int N;
	static int M;
	static int K;
	
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N + 1];

		PriorityQueue<Edge> edges = new PriorityQueue<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(a, b, i + 1));
		}
		
		for (int i = 0; i < K; i++) {
			make();
			
            // 이번 턴용 PQ 복사 후 poll()로만 사용
            PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
            
			int score = 0;
			int cnt = 0;
			
            while (!pq.isEmpty() && cnt < N - 1) {
                Edge e = pq.poll();
                
                if (union(e.s, e.e)) {
                	score += e.w;
                	cnt++;
            	}
            }
			
            if (cnt == N - 1) {
                sb.append(score).append(' ');
                if (!edges.isEmpty()) {
                	edges.poll();
                }
            } else {
                sb.append(0).append(' ');
                for (int t = i + 1; t < K; t++) {
                	sb.append(0).append(' ');
                }
                break;
            }
		}
		System.out.print(sb);
	}
}
