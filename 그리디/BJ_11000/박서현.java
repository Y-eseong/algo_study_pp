import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		int[][] classes = new int[N][2];
		PriorityQueue<int[]> countClass = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				
				return o1[1] - o2[1];
			}
		});
				
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
										
			classes[n][0] = Integer.parseInt(st.nextToken());;
			classes[n][1] = Integer.parseInt(st.nextToken());;
			
		}
		
		Arrays.sort(classes, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			};
		});
		
		for(int[] c : classes) {
			if(countClass.isEmpty()) {
				countClass.offer(c);
				continue;
			}
			
			if(countClass.peek()[1] <= c[0]) {
				countClass.poll();
			}
			countClass.offer(c);
		}
		
		System.out.println(countClass.size());
			
	}
	
}
