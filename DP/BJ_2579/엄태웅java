import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		int[] score = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//길이가 1일 경우에는 그 즉시 종류
		if (n == 1) {
		    System.out.println(score[1]);
		    return;
		}
		//길이가 2일 경우에는 1과 2를 더한게 최대값
		if (n == 2) {
		    System.out.println(score[1] + score[2]);
		    return;
		}
		//길이가 3일 경우에는 조건에 따라서 1칸 3칸, 2칸 3칸 가는 식으로
		if (n == 3) {
		    System.out.println(Math.max(score[1] + score[3], score[2] + score[3]));
		    return;
		}

		
		//첫 번째 값은 누적될 수 없기에 그대로 시작
		dp[1] = score[1];
		//두 번째 값의 최대값은, 첫 번재 계단과 두 번째 계단을 더한 값
		dp[2] = score[1]+score[2];
		//세 번째 값의 최대값은, 1과 3계단 or 2와 3계단의 합 중 최대값을 의미한다
		dp[3] = Math.max(score[1]+score[3], score[2]+score[3]);
		
		
		//점화식을 실행하기 위한 최소값인 3까지 완료했기에, 4부터 각 값을 찾아야한다
		for (int i = 4; i <= n; i++) {
			dp[i] = Math.max(
					//한 칸 건너뛰고 진행하기 때문에 dp의 값은 최대값
					dp[i-2]+score[i],
					//dp의 경우에는 누적된 값을 사용 가능하나, 1칸 뛰고 2개 연속된 값 사용하기에 나머지는 기준값과 그 이전값
					dp[i-3]+score[i-1]+score[i]
							
							);
		}
		System.out.println(dp[n]);
	}
}
