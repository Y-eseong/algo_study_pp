package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458 {
	static int N;
	static int M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] isTall = new ArrayList[N + 1];
		ArrayList<Integer>[] isShort = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			isTall[i] = new ArrayList<>();
			isShort[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			isShort[a].add(b); // a는 b보다 작다
			isTall[b].add(a); // b는 a보다 크다
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int tCnt = bfs(isTall, i);
			int sCnt = bfs(isShort, i);
			if (tCnt + sCnt == N - 1) ans++;
		}
		
		System.out.println(ans);
	}

	private static int bfs(ArrayList<Integer>[] heights, int pIdx) {
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();	
		q.add(pIdx);
		visited[pIdx] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : heights[cur]) {
				if (visited[next]) continue;
				visited[next] = true;
				q.add(next);
				cnt++;
			}
		}
		return cnt;
	}
}
