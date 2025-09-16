package test_etw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643 {
	
	static int n;
	
	static List<List<Integer>> list1;
	static List<List<Integer>> list2;
	
	private static int bfs (int x) {
		int count = 0;
		
		boolean[] visited1 = new boolean[n+1];
		boolean[] visited2 = new boolean[n+1];
		
		Queue<Integer> queue1 = new LinkedList<>();
		Queue<Integer> queue2 = new LinkedList<>();
		
		//우선 원래 방향
		visited1[x] = true;
		queue1.offer(x);
		
		while(!queue1.isEmpty()) {
			int y = queue1.poll();
			for (int item : list1.get(y)) {
				if (!visited1[item]) {
					visited1[item] = true;
					count++;
					queue1.offer(item);
				}
			}
		}
		
		
		
		//반대 방향
		visited2[x] = true;
		queue2.offer(x);
		
		while(!queue2.isEmpty()) {
			int y = queue2.poll();
			for (int item : list2.get(y)) {
				if (!visited2[item]) {
					visited2[item] = true;
					count++;
					queue2.offer(item);
				}
			}
		}
		
		return count;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(in.readLine());
			int m = Integer.parseInt(in.readLine());
			
			list1 = new LinkedList<>();
			list2 = new LinkedList<>();
			
			for (int i = 0; i <= n; i++) {
				list1.add(new LinkedList<>());
				list2.add(new LinkedList<>());
			}
			
			for (int i = 0; i < m; i++) {
				StringTokenizer str = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(str.nextToken());
				int b = Integer.parseInt(str.nextToken());
				
				list1.get(a).add(b);
				list2.get(b).add(a);
			}
			//일단 순서를 대강 짜뒀고
			
			//System.out.println(list1.toString());
			//System.out.println(list2.toString());
			//생성 완료
			
			int test;
			
			int answer = 0;
			
			for (int i = 1; i <= n; i++) {
				test = bfs(i);
				
				if (test == n-1) {answer++;}
				//System.out.println(i+" "+answer);
			}
			
			System.out.println("#"+tc+" "+answer);
			
		}
		
	}

}
