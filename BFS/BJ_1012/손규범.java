package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_1012_sgb {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static ArrayDeque<int[]> next = new ArrayDeque<int[]>();
	static int[][] cabbageList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s;
		int T, N, M, K, answer;
		int[][] farm;
		
		int[] cur = new int[2];
		
		s = in.readLine();
		st = new StringTokenizer(s, " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int TC=0; TC<T; TC++)
		{
			answer = 0;
			
			s = in.readLine();
			st = new StringTokenizer(s, " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			farm = new int[N][M];
			cabbageList = new int[K][2];
			
			for(int i=0; i<K; i++)
			{
				s = in.readLine();
				st = new StringTokenizer(s, " ");
				cur[0] = Integer.parseInt(st.nextToken());
				cur[1] = Integer.parseInt(st.nextToken());
				farm[cur[1]][cur[0]] = 1; 
				cabbageList[i] = new int[] {cur[0], cur[1]};
			}
			
			for(int i=0; i<K; i++)
			{
				if(farm[cabbageList[i][1]][cabbageList[i][0]] != 1) continue;
				
				next.offer(cabbageList[i]);
				
				while(!next.isEmpty())
				{
					cur = next.poll();
					farm[cur[1]][cur[0]] = -1;
					
					for(int dir=0; dir<4; dir++)
					{
						if(cur[0]+dx[dir]<0 || cur[0]+dx[dir]>=M || cur[1]+dy[dir]<0 || cur[1]+dy[dir]>=N) continue;
						
						if(farm[cur[1]+dy[dir]][cur[0]+dx[dir]] == 1) 
                        {
                            farm[cur[1]+dy[dir]][cur[0]+dx[dir]] = -1;
							next.offer(new int[] {cur[0]+dx[dir], cur[1]+dy[dir]});
                        }
					}
				}
				
				answer++;
			}
			
			System.out.println(answer);
		}	
	}
}