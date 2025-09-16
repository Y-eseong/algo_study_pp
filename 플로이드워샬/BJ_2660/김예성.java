import java.io.*;
import java.util.*;

public class 김예성 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n+1][n+1];
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1)
				break;
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		int[] max = new int[n+1];
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(graph[i][k] != 0 && graph[k][j] != 0) {
						if(graph[i][j] == 0 && i != j)
							graph[i][j] = graph[i][k] + graph[k][j];
						else graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				max[i] = Math.max(graph[i][j], max[i]);
			}
			min = Math.min(max[i], min);
		}
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=n; i++) {
			if(min == max[i])
				list.add(i);
		}
		sb.append(min).append(" ").append(list.size()).append("\n");
		Collections.sort(list);
		for(int x : list)
			sb.append(x).append(" ");
		System.out.println(sb.toString());
	}
	
}