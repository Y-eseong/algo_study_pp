import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] seq;
	static int maxTmp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		seq = new int[N];
		
		
		for(int n=0; n<N; n++) {
			seq[n] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sumTmp(0, K, -100*K));
		
	}
	
	public static int sumTmp(int idx, int k, int maxTmp) {
		int sum = 0;
		
		if(idx+k>seq.length) return maxTmp;
		
		for(int i=idx; i<idx+k; i++) {
			sum += seq[i];
		}
		
		return Math.max(sumTmp(idx+1, k, maxTmp), sum);
	}
	
}
