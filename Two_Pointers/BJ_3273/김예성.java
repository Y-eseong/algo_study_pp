import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		String[] in = br.readLine().split(" ");
        
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(in[i]);
		}
		int x = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		int l = 0;
		int r = n -1;
		
		int count = 0;
		
		while(l < r) {
			int sum = arr[l] + arr[r];
			if(sum == x) {
				count++;
				if(arr[l+1] + arr[r] == x)
					l++;
				else
					r--;
			}
			else if(sum < x)
				l++;
			else 
				r--;
		}
		sb.append(count);
		System.out.println(sb);
	}
}