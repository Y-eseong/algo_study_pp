import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N];
		int count = 0;
		int maxIdx = N-1;
		
		for(int n=0; n<N; n++) {
			int price = Integer.parseInt(br.readLine());
			coins[n] = price;
			
			if(price > K) maxIdx = n-1;
		}
		
		for(int i=maxIdx; i>=0; i--) {
			count += (int) (K / coins[i]); 
			K = K % coins[i];
		}
		
		System.out.println(count);
	}
}
