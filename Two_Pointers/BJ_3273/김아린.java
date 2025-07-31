
import java.io.*;
import java.util.*;

public class Main {
	static int N, X;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		X = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		// ai + aj = X, 몇 쌍?
		// 1보다 크거나 같고, 1000000보다 작거나 같은 자연수
		int s = 0, e = N - 1, cnt = 0;
		while(s < e) {
			if(arr[s] + arr[e] == X) cnt++;
			if(arr[s] + arr[e] <= X) s++;
			else e--;
			
		}
		System.out.println(cnt);
		
	}
	
}
