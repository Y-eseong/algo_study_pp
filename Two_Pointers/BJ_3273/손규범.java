package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3273_sgb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n, x, sum, answer = 0;
		int array[];
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		
		array = new int[n];
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<n; i++)
		{
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		x = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n-1; i++)
		{
			for(int j=i+1; j<n; j++)
			{	
				sum = array[i] + array[j];
				if(sum == x)
				{
					answer++;
                    break;
				}
			}
		}
		
		System.out.println(answer);
	}
}