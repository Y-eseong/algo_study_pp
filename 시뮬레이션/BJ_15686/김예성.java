import java.io.*;
import java.util.*;

public class Main {

	static class Pair{
		int x;
		int y;

		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	static int n, m;
	static int[] arr;
	static int[][] map;
	static boolean[] visited;
	static int len;
	static int distance = Integer.MAX_VALUE;
	static ArrayList<Pair> chicken = new ArrayList<>();
	static ArrayList<Pair> home = new ArrayList<>();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		m = Integer.parseInt(in[1]);
		map = new int[n][n];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					chicken.add(new Pair(i, j));
				else if(map[i][j] == 1)
					home.add(new Pair(i, j));
			}
		}

		len = chicken.size();
		arr = new int[m];
		visited = new boolean[len];
		backTracking(0, 0);
		sb.append(distance);
		System.out.println(sb);
	}

	static void backTracking(int k, int st) {
		if(k == m) {
			int sum =0;
			for(Pair t : home) {
				int min=Integer.MAX_VALUE;
				for(int i=0; i<m; i++) {
					int dis=0;
					dis = Math.abs(t.x - chicken.get(arr[i]).x) + Math.abs(t.y - chicken.get(arr[i]).y);
					min = Math.min(dis, min);
				}
				sum += min;
			}
			distance = Math.min(distance, sum);
			return;
		}
		for(int i=st; i<len; i++) {
			if(!visited[i]) {
				arr[k] = i;
				visited[i] = true;
				backTracking(k+1, i);
				visited[i] = false;
			}
		}
	}
}
