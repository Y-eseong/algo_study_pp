import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        ArrayList<Integer>[] bfslist = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
            bfslist[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) { // 예제 간선 저장
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st2.nextToken());
            int to = Integer.parseInt(st2.nextToken());
            list[temp].add(to);
            list[to].add(temp);
            bfslist[temp].add(to);
            bfslist[to].add(temp);
        }
        // 정렬
        for(int i = 0; i < N+1 ;i++) {
        	Collections.sort(list[i]);
        	Collections.sort(bfslist[i]);
        }
        Stack<Integer> stack = new Stack<>(); // 깊이
        Queue<Integer> queue = new LinkedList<>(); //너비
        StringBuilder sb = new StringBuilder();
        int[] visit = new int[N + 1];
        int[] visitBfs = new int[N + 1];
        stack.push(V);
        // 깊이로 풀기
        while (!stack.isEmpty()) {
            int temp = stack.pop();

            if (visit[temp] == 1) {
                continue;  // 이미 방문했으면 건너뛰기
            }
            visit[temp] = 1;
            sb.append(temp).append(" ");
            for (int i = list[temp].size() - 1; i >= 0; i--) {
                int nextNode = list[temp].get(i);
                if (visit[nextNode] == 0) {
                    stack.push(nextNode);
                }
            }
        }
        System.out.println(sb.toString());
        // 너비로 풀기
        StringBuilder sb2= new StringBuilder();
        queue.add(V);
        while(!queue.isEmpty()) {
        	int temp = queue.poll();
        	if(visitBfs[temp] == 1) {
        		continue;
        	}
        	visitBfs[temp] = 1;
        	sb2.append(temp).append(" ");
        	for(int i = 0; i < bfslist[temp].size();i++ ) {
        		int nextNode = bfslist[temp].get(i);
        		if(visitBfs[nextNode] == 0) {
        			queue.add(nextNode);
        		}
        	}
        	
        }
        System.out.println(sb2);
    }

}
