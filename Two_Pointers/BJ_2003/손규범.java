package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2003_sgb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, M, sum, answer = 0;
		int array[];
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++)
		{
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++)
		{
			sum = 0;
			for(int j=i; j<N; j++)
			{	
				sum += array[j];
				
				if(sum == M)
				{
					answer++;
					break;
				}
				else if (sum > M) break;
			}
		}
		
		System.out.println(answer);
	}
}
