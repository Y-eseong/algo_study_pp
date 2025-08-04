
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // start = dir[0]
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			String h = br.readLine();
			int x = 0;
			int y = 0;
			int maxX = 0;
			int maxY = 0;
			int minX = 0;
			int minY = 0;
			int heading = 0;
			for(int m = 0; m < h.length(); m++) {
				if(h.charAt(m) == 'F') {
					x = x + dir[heading][1];
					y = y + dir[heading][0];
				} else if(h.charAt(m) == 'B') {
					x = x - dir[heading][1];
					y = y - dir[heading][0];
				} else if(h.charAt(m) == 'R') {
					heading = (heading + 1) % 4;
				} else if(h.charAt(m) == 'L') {
					if(heading == 0) heading = 3;
					else heading -= 1;
				}
				if(x > 0) maxX = Math.max(maxX, x);
				else if (x < 0) minX = Math.min(minX, x);
				if(y > 0) maxY = Math.max(maxY, y);
				else if(y < 0) minY = Math.min(minY, y);
			}
			System.out.println(Math.abs((maxX - minX) * (maxY - minY)));
		}
	}
}
