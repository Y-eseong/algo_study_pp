import java.io.*;

public class Main {
	
	// 상 우 하 좌
	static int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			String in = br.readLine();
			int x = 0;
			int y = 0;
			int x_min = 0;
			int x_max = 0;
			int y_min = 0;
			int y_max = 0;
			int z = 0;
			
			for(int j=0; j<in.length(); j++) {
				if(in.charAt(j) == 'L') {
					z -= 1;
					if(z<0) z=3;
				}
				else if(in.charAt(j) == 'R') {
					z += 1;
					if(z>3) z=0;
				}
				else if(in.charAt(j) == 'F') {
					x += dir[z][0];
					y += dir[z][1];
				}
				else if(in.charAt(j) == 'B') {
					x -= dir[z][0];
					y -= dir[z][1];
				}
				x_max = Math.max(x_max, x);
				y_max = Math.max(y_max, y);
				x_min = Math.min(x_min, x);
				y_min = Math.min(y_min, y);
				
			}
			sb.append(Math.abs(x_max - x_min) * Math.abs(y_max - y_min)).append("\n");
		}
		System.out.println(sb);
	}
}