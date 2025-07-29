import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] seq;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		seq = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int n=0; n<N; n++) {
			seq[n] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(countCases(0, M));
		
	}
	
	public static int countCases(int idx, int m) {
		int sum = 0;
		
		for(int i=idx; i<seq.length; i++) {
			sum += seq[i];
			
			if(sum == m) {
				count += 1;
				break;
			}
			else if(sum > m) break;
		}
		
		if(idx < seq.length) {
			countCases(idx+1, m);
		}
		
		return count;
	}
}
