package com.ssafy.project.algo.simul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15686_sgb {
	static ArrayList<int[]> store = new ArrayList<>();
	static ArrayList<int[]> house = new ArrayList<>();
	
	static int dfs(int[] array, int cur, int M, int depth)
	{
		int[] choose = array;
		int result = 0;
		
		if(depth == M)
		{
			return minChickenLoad(choose);
		}
		
		for(int i=cur; i<store.size(); i++)
		{
			choose[depth] = i;
			int sum = dfs(choose, i+1, M, depth+1);
			result = calMin(result, sum);
		}
		
		return result;
	}
	
	static int minChickenLoad(int[] array)
	{
		int result = 0;
		for(int i = 0; i<house.size(); i++)
		{
			int sum = 0;
			for(int j : array)
			{
				int len = Math.abs(store.get(j)[0] - house.get(i)[0]) + Math.abs(store.get(j)[1] - house.get(i)[1]);
				sum = calMin(sum, len);
			}
			result += sum;
		}
		
		return result;
	}
	
	static int calMin(int a, int b)
	{
		if(a==0) return b;
		else if(b==0) return a;
		else return Math.min(a, b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N, M, answer;
		
		//Input N, M Value
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//Create Map
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++)
			{
				int num = Integer.parseInt(st.nextToken());
				if(num == 2) store.add(new int[] {i, j});
				if(num == 1) house.add(new int[] {i, j});
			}
		}
		
		
		answer = dfs(new int[M], 0, M, 0);
		System.out.println(answer);
	}
}
