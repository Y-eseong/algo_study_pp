package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1931 {
	public static class Pair implements Comparable<Pair> {
		int sTime;
		int eTime;
		
		public Pair(int s, int e) {
			this.sTime = s;
			this.eTime = e;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.eTime == o.eTime) {
				return Integer.compare(this.sTime, o.sTime);
			} else {
				return Integer.compare(this.eTime, o.eTime);				
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Pair> meetings = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			meetings.add(new Pair(start, end));
		}
		
		Collections.sort(meetings);
		
		int optimalEndTime = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			Pair cur = meetings.get(i);
			int start = cur.sTime;
			int end = cur.eTime;
			
			if (start >= optimalEndTime) {
				cnt++;
				optimalEndTime = end;
			}
		}
		
		System.out.println(cnt);
	}

}
