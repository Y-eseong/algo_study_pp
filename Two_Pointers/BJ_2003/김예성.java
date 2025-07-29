import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] in = br.readLine().split(" ");
		int n = Integer.parseInt(in[0]);
		int m = Integer.parseInt(in[1]);
		
		int[] arr = new int[n];
		in = br.readLine().split(" ");
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(in[i]);
		}
		
		int count = 0;
		for(int i=0; i<n; i++) {
			int sum = 0;
			for(int j=i; j<n; j++) {
				sum += arr[j];
				if(sum == m) {
					count ++;
					break;
				}
				else if(sum > m) {
					break;
				}
			}
		}
		sb.append(count);
		System.out.println(sb);
	}
}