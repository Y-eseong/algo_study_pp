import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0, sum = 0, cnt = 0;
        
        // 2249번 문제랑 다른 점은, 구하고자 하는 슬라이딩 윈도우의 크기가 정해져있냐 그렇지 않냐로 나뉨. for로 접근하면 풀 수 없는 것 같음.
        while (true) {
            if (sum >= M) {
                sum -= arr[left]; // 로직은 같지만, 투 포인터를 운용해야 함. sum이 기준보다 작으면 right++ 크면 left++ 이것만 기억하면 됨.
                left++;
            } else if (right == N) {
                break;
            } else {
                sum += arr[right];
                right++;
            }

            if (sum == M) {
            	cnt++;
            }
        }

        System.out.println(cnt);
	}
}
