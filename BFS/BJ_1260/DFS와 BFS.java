import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static int[][] matrix;
	
	static Stack<Integer> stack = new Stack<>();
	static Queue<Integer> queue = new LinkedList<>();
	
	static boolean[] stack_visited;
	static boolean[] queue_visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		matrix = new int[N + 1][N + 1];
		stack_visited = new boolean[N + 1];
		queue_visited = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			matrix[s][d] = 1;
			matrix[d][s] = 1;
		}
		dfs(V);
		bfs(V);
	}
	static void dfs(int V) {
	    StringBuilder sb = new StringBuilder();

	    stack.push(V);

	    while (!stack.isEmpty()) {
	        int current = stack.pop();
	        if (stack_visited[current]) continue;

	        stack_visited[current] = true;
	        sb.append(current).append(" ");

	        for (int i = N; i >= 1; i--) {
	            if (matrix[current][i] == 1 && !stack_visited[i]) {
	                stack.push(i);
	            }
	        }
	    }
	    System.out.println(sb);
	}
	static void bfs(int V) {
		StringBuilder sb = new StringBuilder();
		
		queue.offer(V);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			if (queue_visited[current]) continue;
			
			queue_visited[current] = true;
			sb.append(current).append(" ");
			
			for (int i = 1; i <= N; i++) {
				if (matrix[current][i] == 1 && !queue_visited[i]) {
					queue.offer(i);
				}
			}
		}
		System.out.println(sb);
	}
}
