import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] seq;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		seq = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int n=0; n<N; n++) {
			seq[n] = Integer.parseInt(st.nextToken());
		}
		
		int X = Integer.parseInt(br.readLine());
		
		combination(0, X);
		
		System.out.println(count);
		
	}
	
	public static void combination(int idx, int x) {
		int sum = seq[idx];
		
		for(int i=idx+1; i<seq.length; i++) {
			if(sum + seq[i] == x) {
				count += 1;
			}
		}
		
		if(idx+1<seq.length) combination(idx+1, x);
	}
	
}
