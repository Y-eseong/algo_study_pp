import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리멘더링 {
	static int T, N, fstGroup, scndGroup, result;
	static int[][] graph;
	static int[] population;
	static boolean[] checked;

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		checked = new boolean[N+1];
		population = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			population[n] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			for (int j = 0; j < L; j++) {
				int temp = Integer.parseInt(st.nextToken());
				graph[i][temp] = graph[temp][i] = 1;
			}
		}
		
		result = Integer.MAX_VALUE;
		
		combination(1, new boolean[N+1]);
		
		System.out.println( result == Integer.MAX_VALUE ? -1 : result);
	}
	
	public static void combination(int cnt, boolean[] store) {
		if(cnt == N) {
			
			if(isConnected(store, false) && isConnected(store, true)) {
				int falseSum = 0;
				int trueSum = 0;
				for (int i = 1; i <= N; i++) {
					if(store[i]) trueSum += population[i];
					else falseSum += population[i];
				}
				
				result = Math.min(result, Math.abs(falseSum - trueSum));
				
				
			}
			
			
			return;
		}

		
		store[cnt] = true;
		combination(cnt+1, store);
		store[cnt] = false;
		combination(cnt+1, store);
	}
	
	public static boolean isConnected(boolean[] group, boolean flag) {
		boolean[] tracking = new boolean[N+1];
		int start = -1;
		int groupCnt = 0;
		
		for (int i = 1; i <= N; i++) {
			if(group[i] == flag) {
				if(start == -1) start = i;
				groupCnt++;
			}
		}
		
		if(start == -1) return false;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		tracking[start] = true;
		int depth = 1;
		while(!q.isEmpty()) {
			int crrY = q.poll();
			
			for (int x = 1; x <= N; x++) {
				if(graph[crrY][x] == 0 || tracking[x]) continue;
				if(group[x] == flag && tracking[x] == false) {
					tracking[x] = true;
					depth++;
					q.add(x);
				}
			}
		}
		return depth == groupCnt;
	}

}
