import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		// 테케
		for(int i=0; i<T; i++) {
			String in = br.readLine();
			int m = Integer.MIN_VALUE;
			String[] dir = {"Left", "Right"};
			// 거리 최대값을 구하는 문제이기에 무조건 모든 ?에 같은 방향이 들어가야 한다고 생각해서
			// L이 들어가는 경우와 R이 들어가는 경우를 계산함.
			for(String x : dir) {
				int n = 0;
				for(int j=0; j<in.length(); j++) {
					if(in.charAt(j) == 'L') {
						n -= 1;
					}
					else if(in.charAt(j) == 'R') {
						n += 1;
					}
					else {
						if(x.equals("Left"))
							n -= 1;
						else if(x.equals("Right"))
							n += 1;
					}
					m = Math.max(m, Math.abs(n));
				}
			}
			sb.append(m).append("\n");
		}
		System.out.println(sb);
	}
}