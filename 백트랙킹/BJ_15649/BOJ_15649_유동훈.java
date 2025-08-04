import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {

	static int N;
	static int M;
	static boolean[] isUsed;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer sb = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(sb.nextToken());
		M = Integer.parseInt(sb.nextToken());
		
		isUsed = new boolean[N];
		arr = new int[N];
		int k = 0;
		
		func(k);
	}

	private static void func(int k) {
		if (k == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			} System.out.println("");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
				arr[k] = i + 1;
				isUsed[i] = true;
				func(k + 1);
				isUsed[i] = false;
			}
		}
	}

}
