import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Integer[] arrN;
	static int[] arrM;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arrN = new Integer[N];
			arrM = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arrN[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arrN, Collections.reverseOrder());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				arrM[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(getCount(0)).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	public static int getCount(int m) {
		int count = 0;
		if(m >= arrM.length) return count;
		
		for(int n: arrN) {
			if(arrM[m] >= n) break;
			count++;
		}
		
		return getCount(m+1) + count;
	}
}
