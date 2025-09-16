package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10159 {
	
	static int N;
	static int M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] downGraph = new ArrayList[N + 1];
		ArrayList<Integer>[] upGraph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			upGraph[i] = new ArrayList<>();
			downGraph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 자기 자신보다 작은 사람들을 저장
			downGraph[a].add(b);
			
			// 자기 자신보다 큰 사람들을 저장
			upGraph[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			int ans = N - 1;
			
			int downConnCnt = bfs(downGraph, i);
			int upConnCnt = bfs(upGraph, i);
			
			sb.append(ans - downConnCnt - upConnCnt).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int bfs(ArrayList<Integer>[] graph, int pIdx) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(pIdx);
		int connCnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph[cur]) {
				if (visited[next]) continue;
				visited[next] = true;
				connCnt++;
				q.add(next);
			}
		}
		
		return connCnt;
	}

}
