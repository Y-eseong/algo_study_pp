package com.ssafy.test;

import java.io.*;
import java.util.*;

public class Main {
	
	static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	// 상 하 좌 우
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	static Queue<Pair> q = new LinkedList<>();
	
    public static void main(String args[]) throws IOException {
    	// 읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i <= T; i++) {
        	// m 가로, n 세로, k 배추
        	String[] input = br.readLine().split(" ");
        	int m = Integer.parseInt(input[0]);
        	int n = Integer.parseInt(input[1]);
        	int k = Integer.parseInt(input[2]);
        	int[][] ground = new int[n][m];
        	boolean[][] visited = new boolean[n][m]; // 이미 방문했으면 q에 넣지 않기
        	// 지렁이 수
        	int count = 0;
        	
        	for(int j = 0; j < k; j++) {
        		String[] in = br.readLine().split(" ");
        		int y = Integer.parseInt(in[0]);
        		int x = Integer.parseInt(in[1]);
        		// 배추 있는 곳 1 나머지 0
        		ground[x][y] = 1;
        	}

        	for(int x = 0; x < n; x ++) {
        		for(int y = 0; y < m; y++) {
        			if(ground[x][y] == 1) {
        				q.offer(new Pair(x, y));
        				visited[x][y] = true;
        				bfs(ground, visited, n, m);
        				count++;
        			}
        		}
        	}
        	sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
    
	static void bfs(int[][] ground, boolean[][] visited, int n, int m) {
		
		while(!q.isEmpty()){			
			Pair cur = q.poll();
			int cx = cur.getX();
			int cy = cur.getY();
			ground[cx][cy] = 0;
			
			for(int[] dxy : dir) {
				int nx = cx + dxy[0];
				int ny = cy + dxy[1];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}
				
				if(ground[nx][ny] == 1) {
					if(visited[nx][ny]) {
						continue;
					}
					q.offer(new Pair(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
}