import java.io.*;
import java.util.*;

public class Main {
	static Stack<String> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long[] arr = new long[n];
		
		int i=0;
		while(i<n) {
			if(!st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
				continue;
			}
			String x = st.nextToken();
			for(int j=0; j<x.length(); j++) {
				stack.push(String.valueOf(x.charAt(j)));
			}
			String y = stack.pop();
			while(!stack.isEmpty()) {
				y = y.concat(stack.pop());
			}
			arr[i] = Long.parseLong(y);
			i++;
		}
		Arrays.sort(arr);
		for(int j=0; j<n; j++) {
			sb.append(arr[j]).append("\n");
		}
		System.out.println(sb);
	}
}