
import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		a: for(int x = 0; x < N; x++) {
			int sum = 0;
			for(int y = 0; y < K; y++) {
				if(x + y == N) break a;
				sum += arr[x + y];
			}
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}
	
}
