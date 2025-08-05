package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_8911_sgb {
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		int[] curPos;
        
		T = Integer.parseInt(in.readLine());
		
		for(int tc=0; tc<T; tc++)
		{
			String s = in.readLine();
			curPos = new int[] {0, 0};
			int dir = 0;
			int[] xlen = new int[2];
			int[] ylen = new int[2];
			int answerX = 0, answerY = 0;
			
			for(int i=0; i<s.length(); i++)
			{
				
				if(s.charAt(i) == 'F')
				{
					curPos[0] += dx[dir];
					curPos[1] += dy[dir];
				}
				
				if(s.charAt(i) == 'B')
				{
					curPos[0] -= dx[dir];
					curPos[1] -= dy[dir];
				}
				
				if(s.charAt(i) == 'L')
				{
					dir += 2;
					
					if(dir==4) dir = 1;
					if(dir==5) dir = 0;
				}
				
				if(s.charAt(i) == 'R')
				{
					dir -= 2;
					if(dir==-1) dir = 2;
					if(dir==-2) dir = 3;
				}
				
				if(curPos[0] > 0) xlen[1] = Math.max(xlen[1], Math.abs(curPos[0]));
				else xlen[0] = Math.max(xlen[0], Math.abs(curPos[0]));
				if(curPos[1] > 0) ylen[1] = Math.max(ylen[1], Math.abs(curPos[1]));
				else ylen[0] = Math.max(ylen[0], Math.abs(curPos[1]));
				
			}
			
			for(int i=0; i<2; i++)
			{
				answerX += Math.abs(xlen[i]);
				answerY += Math.abs(ylen[i]);
			}
			
			System.out.println(answerX * answerY);
		}
	}
}