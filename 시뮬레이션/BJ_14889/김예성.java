import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] s;
	static boolean[] visited;
	static Integer[] arr;
	static int n, m;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		static StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		s = new int[n][n];
		for(int i=0; i<n; i++) {
			String[] in = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				s[i][j] = Integer.parseInt(in[j]);
			}
		}
		m = n/2;
		arr = new Integer[m];
		visited = new boolean[n+1];
		backTracking(0, 1);
		System.out.println(min);
	}
	
	static void backTracking(int k, int st) {
		if(k == m) {
			int sum_start = 0;
			int sum_link = 0;
			Integer[] arr2 = new Integer[m];
			int cnt = 0;
			for(int i=1; i<=n; i++) {
				if(!Arrays.asList(arr).contains(i)) {
					arr2[cnt++] = i;
				}
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(Arrays.asList(arr).contains(i) && Arrays.asList(arr).contains(j)) {
						sum_start += s[i-1][j-1];
					}
					else if(Arrays.asList(arr2).contains(i) && Arrays.asList(arr2).contains(j))
						sum_link += s[i-1][j-1];
				}
			}
			min = Math.min(Math.abs(sum_start-sum_link), min);
			return;
		}
		for(int i=st; i<=n; i++) {
			if(!visited[i]) {				
				arr[k] = i;
				visited[i] = true;
				backTracking(k+1, i);
				visited[i] = false;
			}
		}
	}
}