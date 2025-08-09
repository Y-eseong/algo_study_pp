import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] isUsed;
	static int[][] S;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		S = new int[N][N];
		isUsed = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		track(0, 0);
		System.out.println(ans);
	}

	private static void track(int i, int depth) {
		if (depth == N / 2) {
			calc();
			return;
		}
		for (int k = i; k < N; k++) {
			if(!isUsed[k]) {
				isUsed[k] = true;
				track(k + 1, depth + 1);
				isUsed[k] = false;
			}
		}
	}

	private static void calc() {
		int start = 0;
		int link = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if(isUsed[i] && isUsed[j]) {
					start += S[i][j] + S[j][i];
				}
				else if (!isUsed[i] && !isUsed[j]){
					link += S[i][j] + S[j][i];
				}
			}
		}
		ans = Math.min(ans, Math.abs(start - link));
	}
}