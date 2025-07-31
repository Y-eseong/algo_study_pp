import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] days = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			days[i] = Integer.parseInt(st.nextToken());
		}
		int window = 0;
		for (int i = 0; i < K; i++) {
			window += days[i];
		}
		int ans = window;
		for (int i = 0; i < N - K; i++) {
			window = window - days[i] + days[i + K];
			ans = Math.max(ans, window);
		}
		System.out.println(ans);
	}
}
