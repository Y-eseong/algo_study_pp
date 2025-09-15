import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		int answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		
		for (int i = 0; i < n; i++) {
			//현 위치만으로도 길이는 m이다
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				//현 위치가 다음 값보다 커지면 종료
				if (arr[i] > arr[j]) {
					//만일 작을 경우, 현재 위치의 값과, 그 이전까지 누적된 값들 중 가장 큰거에다가 j 자기 자신까지 더해 +1을 한다
                    //그리고 매번 최대값 갱신
					dp[i] = Math.max(dp[i], dp[j]+1);
					
				}
			}
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
		
	}
}
