package algo_study_pp.algo.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ_2217_sgb {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> rope = new ArrayList<>();
		int k = 0;
		int answer = 0;
		
		for(int i=0; i<N; i++)
		{
			int num = Integer.parseInt(br.readLine());
			rope.add(num);
		}
		Collections.sort(rope, Collections.reverseOrder());
		for(int i=0; i<N; i++)
		{
			k++;
			int w = rope.get(i)*k;
			if(answer<w)
			{
				answer = w;
			}
		}
		
		System.out.println(answer);
	}
}
