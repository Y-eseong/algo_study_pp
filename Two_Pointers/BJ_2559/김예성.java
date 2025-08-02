package com.ssafy.test;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] in = br.readLine().split(" ");
		int n = Integer.parseInt(in[0]);
		int k = Integer.parseInt(in[1]);
		int[] tp = new int[n];
		
		in = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			tp[i] = Integer.parseInt(in[i]);
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<=n-k; i++) {
			int sum = 0;
			
			for(int j=i; j<i+k; j++) {
				sum += tp[j];
			}
			max = Math.max(max, sum);
		}
		
		sb.append(max);
		System.out.println(sb);
	}
}