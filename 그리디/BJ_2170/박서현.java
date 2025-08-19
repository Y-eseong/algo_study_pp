import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		int[][] lines = new int[N][2];
		int sum = 0;
		
		int start  = -1000000001;
		int end  = -1000000001;
		
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
										
			if(a > b) {
				lines[n][0] = b;
				lines[n][1] = a;
			} else {
				lines[n][0] = a;
				lines[n][1] = b;
			}
			
		}
		
		Arrays.sort(lines, new Comparator<>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			};
		});
		
		for(int i=0; i<N; i++) {
			if(lines[i][0] > end) {
				sum += (lines[i][1] - lines[i][0]);
				start = lines[i][0];
				end = lines[i][1];
			}
			else if(lines[i][0] >= start && lines[i][0] <= end && lines[i][1] > end) {
				sum += (lines[i][1] - end);
				end = lines[i][1];
			}
		}
		
		System.out.println(sum);
			
	}
	
}
