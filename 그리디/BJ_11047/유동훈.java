import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int temp = K;
		
		int[] price = new int[N];
		
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(br.readLine());
			price[i] = p; 
		}
		
		int cnt = 0;
		
		for (int i = N - 1; i >= 0; i--) {
			while (temp >= price[i]) {
			    temp -= price[i];
			    cnt++;
			}
		}
		System.out.println(cnt);
	}
}
