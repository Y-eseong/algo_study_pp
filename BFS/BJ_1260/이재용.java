import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] adjList = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adjList[start].add(end);
			adjList[end].add(start);
		}
		
		// 인접 리스트로 구현 시 정렬 필수!!
		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]);
		}
		
		Deque<Integer> stack = new ArrayDeque<>();
		boolean[] stack_visited = new boolean[N + 1];
		
		stack.push(V);
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			int current = stack.pop();
			if (!stack_visited[current]) {
				stack_visited[current] = true;
				sb.append(current).append(" ");
				
				for (int i = adjList[current].size() - 1; i >= 0; i--) {
					if (!stack_visited[adjList[current].get(i)]) {
						stack.push(adjList[current].get(i));
					}
				}
			}
		}
		System.out.println(sb);
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] queue_visited = new boolean[N + 1];
		
		queue.offer(V);
		sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int current = queue.poll();
			if(!queue_visited[current]) {
				queue_visited[current] = true;
				sb.append(current).append(" ");
				
				for (int destination : adjList[current]) {
					if(!queue_visited[destination]) {
						queue.offer(destination);
					}
				}
			}
		}
		System.out.println(sb);
	}
}
