package test_etw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1486_2 {
	static int n, b;
	
	static int[] arr;
	
	static int answer;
	
	private static void find(int cnt, int x) {
		if (x >= answer) {
			return;
		}

		if (cnt == n) {
			if (x >= b) {
				answer = Math.min(answer, x);
			}
			return;
		}
		
		find(cnt+1, x+arr[cnt]);
		find(cnt+1, x);
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			String[] test = in.readLine().split(" ");
			n = Integer.parseInt(test[0]);
			b = Integer.parseInt(test[1]);
			
			arr = new int[n];
			
			String[] h = in.readLine().split(" ");
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(h[i]);
			}
			
			answer = Integer.MAX_VALUE;
			
			find(0,0);
			System.out.println("#"+tc+" "+(answer-b));
			
		}
		
	}

}
