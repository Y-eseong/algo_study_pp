import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> arr = new ArrayList<>();
		BackTrack(arr, 1, N, M);
	}
	static void BackTrack(ArrayList<Integer> arr, int idx, int N, int M)
	{
		StringBuilder sb = new StringBuilder();
		if(arr.size() == M)
		{
			for(int a : arr)
			{
				sb.append(a).append(" ");
			}
			System.out.println(sb);
			return;
		}
		
		for(int i = idx; i <= N; i++)
		{
			ArrayList<Integer> copyarr = new ArrayList<>(arr);
			copyarr.add(i);
			BackTrack(copyarr, i + 1, N, M);
		}
	}
}
