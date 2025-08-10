import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long[] arr = new long[N];
		
		for(int n=0; n<N; n++) {
			while(!st.hasMoreElements()) {
				st = new StringTokenizer(br.readLine());
			}

			arr[n] = Long.parseLong(new StringBuffer(st.nextToken()).reverse().toString());
		}
		
		Arrays.sort(arr);
		
		for(long a : arr) {
			sb.append(a).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
