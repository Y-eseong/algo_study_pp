import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[][] line = new int[n][2];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			line[i][0] = Integer.parseInt(st.nextToken());
			line[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(line, (o1, o2)->{
			return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
		});
		
		
		int l = 0;
		int s = line[0][0];
		int e = line[0][1];
		
		for(int i=1; i<n; i++) {
			if(line[i][1] <= e)
				continue;
			else if(line[i][0] <= e && line[i][1] > e)
				e = line[i][1];
			else if(line[i][0] > e) {
				l += e-s;
				s = line[i][0];
				e = line[i][1];
			}
		}
		l += e-s;
		sb.append(l);
		
		System.out.println(sb);
	}
}
