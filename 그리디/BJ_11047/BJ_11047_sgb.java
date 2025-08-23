package algo_study_pp.algo.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_11047_sgb {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer> coins = new ArrayList<>();
		int answer = 0, cnt = 0;
		
		for(int i=0; i<N; i++)
		{
			int num = Integer.parseInt(br.readLine());
			coins.add(num);
		}
		Collections.sort(coins, Collections.reverseOrder());
		while(K!=0)
		{
			int num = coins.get(cnt);
			if(K >= num)
			{
				K -= num;
				answer++;
			}
			else
				cnt++;
		}
		
		System.out.println(answer);
	}
}
