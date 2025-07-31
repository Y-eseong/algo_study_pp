
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 합이 M이 되는 경우의 수
		// M에서 계속 마이너스 했을 때 0이 되는 경우의 수를 모두 구함
		int s = 0, e = 0, sum = 0, cnt = 0;
		while(true) {
			
			if(sum >= M) sum -= arr[s++];
            else if(e == N) break;
			else sum += arr[e++];
			if(sum == M) cnt++;
		}
		System.out.println(cnt);
		
	}
	
}
