import java.io.*;
import java.util.*;

public class Main {

	static class Pair{
		int s;
		int e;
		
		public Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
	
	static PriorityQueue<Pair> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>((o1, o2) -> o1.e != o2.e ? o1.e - o2.e : o1.s - o2.s);
		
		for(int i=0; i<n; i++) {
			String[] in = br.readLine().split(" ");
			int s = Integer.parseInt(in[0]);
			int e = Integer.parseInt(in[1]);
			pq.offer(new Pair(s, e));
		}
		
		int count = 0;
		int end = -1;
		
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			int s = cur.s;
			int e = cur.e;
			
			if(end <= s) {
				count++;
				end = e;
			}
		}
		sb.append(count);
		System.out.println(sb);
	}
	
}
