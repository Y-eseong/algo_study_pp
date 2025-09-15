package test_etw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			//System.out.println(Arrays.toString(arr[i]));
		}
		
		for (int i = n-1; i > 0; i--) {
			//System.out.println(Arrays.toString(arr[i]));
			//System.out.println(i);
			for (int j = 0; j < i; j++) {
				//System.out.println(j);
				int x = arr[i-1][j] + arr[i][j];
				int y = arr[i-1][j] + arr[i][j+1];
				arr[i-1][j] = Math.max(x, y);
			}
		}
		
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		System.out.println(arr[0][0]);
		
	}
}
