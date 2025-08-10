import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10025_sgb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	
		int[][] ice = new int[N][2];
		int max = 0;
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(in.readLine());
			ice[i][0] = Integer.parseInt(st.nextToken());
            ice[i][1] = Integer.parseInt(st.nextToken());
			max = Math.max(max, ice[i][1]);
		}
		
		int[] arr = new int[max+1];
		
		for(int i=0; i<N; i++)
		{
			arr[ice[i][1]] = ice[i][0];
		}
		max = 0;
		
		if(K>arr.length)
		{
			for(int i=0; i<N; i++)
			{
				max += ice[i][0];
			}
		}
		else
		{
			for(int i=0; i<=K*2; i++)
			{
				if(i>arr.length) break;
				max += arr[i];
			}
			
			int sum = max;
			max = Math.max(max, sum);
			
			for(int i=0; i<arr.length-K*2; i++)
			{
				if(i+K*2+1 >= arr.length) break;
				sum -= arr[i];
				sum += arr[i+K*2+1];
				
				max = Math.max(max, sum);
			}
		}
		System.out.println(max);
	}
}