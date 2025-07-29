import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			
			String input = br.readLine();
			int ans = 0;
			int bar = 0;
			for (int i = 0; i < input.length(); i++) {
				char token = input.charAt(i);
				
				if (token == ')') {
					
					if (input.charAt(i - 1) == '(') {
						ans += --bar;
					}
					else if (input.charAt(i - 1) == ')') {
						bar--;
						ans++;
					}
					
				}
				
				else if (token == '(') {
					bar++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
		}
		
		System.out.print(sb);
	}
}
