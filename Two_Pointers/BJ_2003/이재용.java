import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		int[] S = new int[N]; // A 배열의 0부터 index까지의 합 배열
		st = new StringTokenizer(br.readLine(), " ");
		int sum = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			sum += A[i];
			S[i] = sum;
			if (S[i] == M) {
				count++;
			}
		}
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (S[j] - S[i] == M) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
