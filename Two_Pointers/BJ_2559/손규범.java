package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2559_sgb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int array[];
		int N, K;
		int answer, slider = 0;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++)
		{
			array[i] = Integer.parseInt(st.nextToken());
			if(i<K) slider += array[i];
		}
		answer = slider;
		
		for(int i=0; i<=N-K-1; i++)
		{
			slider -= array[i];
			slider += array[i+K];
			answer = Math.max(answer, slider);
		}
		
		System.out.println(answer);
	}
}
