import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());

		int left = 0, right = n - 1, sum = 0, cnt = 0;
		
		while (left < right) {
			sum = arr[left] + arr[right];
			if (sum < x) {
				left++;
			} else if (sum == x) {
				cnt++;
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(cnt);
	}
}
