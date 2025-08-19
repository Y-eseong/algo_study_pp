import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] meetings = new int[N][2];
		
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			meetings[n][0] = start;
			meetings[n][1] = end;
		}
		
		Arrays.sort(meetings, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			};
		});
		
		int endTime = 0;
		int count = 0;
		
		for(int i=0; i<N; i++) {
			if(meetings[i][0] >= endTime) {
				count++;
				endTime = meetings[i][1];
			}
		}
		
		
		System.out.println(count);
	}
	
}
