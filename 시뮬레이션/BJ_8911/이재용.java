import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };// 12시부터 시계방향
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			
			String str = br.readLine();
			
			int row = 0;
			int col = 0;
			int d = 0;
			int maxR = 0;
			int maxC = 0;
			int minR = 0;
			int minC = 0;
			for (int i = 0; i < str.length(); i++) {
				
				if (str.charAt(i) == 'F') {
					row += dir[d][0];
					col += dir[d][1];
					maxR = Math.max(maxR, row);
					maxC = Math.max(maxC, col);
					minR = Math.min(minR, row);
					minC = Math.min(minC, col);
				}
				
				else if (str.charAt(i) == 'B') {
					row -= dir[d][0];
					col -= dir[d][1];
					maxR = Math.max(maxR, row);
					maxC = Math.max(maxC, col);
					minR = Math.min(minR, row);
					minC = Math.min(minC, col);
				}
				
				else if (str.charAt(i) == 'L') {
					d = (d + 3) % 4;
					maxR = Math.max(maxR, row);
					maxC = Math.max(maxC, col);
					minR = Math.min(minR, row);
					minC = Math.min(minC, col);
				}
				
				else if (str.charAt(i) == 'R') {
					d = (d + 1) % 4;
					maxR = Math.max(maxR, row);
					maxC = Math.max(maxC, col);
					minR = Math.min(minR, row);
					minC = Math.min(minC, col);
				}
				
			}
			
			int ans = (maxR - minR) * (maxC - minC);
			sb.append(ans).append("\n");
			
		}
		System.out.println(sb);
	}
}