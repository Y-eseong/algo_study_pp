package test_etw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2458 {
	static List<List<Integer>> list1,list2;
	static int n,m,answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		
		for (int i = 0; i <= n; i++) {
			list1.add(new ArrayList<>());
			list2.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list1.get(a).add(b);
			list2.get(b).add(a);
		}
		
		answer = 0;
		
		for (int i = 1; i <= n; i++) {
			bfs(i);
		}
		
		System.out.println(answer);
	}
	
	private static void bfs(int x) {
		int count = 0;
		
		boolean[] visited = new boolean[n+1];
		visited[x] = true;
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		queue.offer(x);
		
		while(!queue.isEmpty()) {
			int y = queue.poll();
			
			for (int item : list1.get(y)) {
				if (!visited[item]) {
					queue.offer(item);
					visited[item] = true;
					count++;
				}
			}
		}
		
		queue.offer(x);
		
		while(!queue.isEmpty()) {
			int y = queue.poll();
			
			for (int item : list2.get(y)) {
				if (!visited[item]) {
					queue.offer(item);
					visited[item] = true;
					count++;
				}
			}
		}
		
		if (count+1 == n) {
			answer++;
		}
		
		
	}
}
