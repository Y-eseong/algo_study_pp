import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {

	static int N;
	static int M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		int k = 0;
		
		func(k);
		
		System.out.print(sb.toString());
	}

	private static void func(int k) {
		if (k == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i]).append(" ");
			} sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			arr[k] = i;
			func(k + 1);
		}
	}

}

/* 기존에 시간 초과 나던 코드
 *  private static void func(int k) {
 *		if (k == M) {
 *			for (int i = 0; i < M; i++) {
 * 				System.out.print(arr[i] + " ");
 * 			} System.out.println("");
 *			return;
 *		}
 *		
 *		for (int i = 1; i <= N; i++) {
 *			arr[k] = i;
 *			func(k + 1);
 *		}
 *	}
 *
 * 로직이 무조건 맞는데 왜 시간초과가 날까에 대해서 생각해봤음. 로직상으로는 진짜 아무런 문제가 없어서 출력이 문젠가 싶어서 대충 계산 때려보니 최악의 경우 7의 7승이니까 80만줄이 넘게 출력되어야 함
 * 그럼 80만줄에 + " " 연산도 80만번, 그리고 "" 출력도 80만번.. 아마 그래서 그런 게 아닐까? 하고 스트링 빌더로 바꿈. 그리고 스트링 토크나이저는 st인데, sb로 잘못 쓴 걸 이제서야 알게 됨
 * 
 * 아 그리고 위 코드 이전에는 다른 의미로 시간 초과가 났었는데
 * 
 * 	private static void func(int k, int start) {
 *		if (k == M) {
 *			for (int i = 0; i < M; i++) {
 *				System.out.print(arr[i] + " ");
 *			} System.out.println("");
 *			return;
 *		}
 *		
 *		for (int i = start; i <= N; i++) {
 *			arr[k] = i;
 *			func(k + 1 , start);
 *		}
 *	}
 * 
 * 이때는 func에게 15649번 문제처럼 조합으로 접근할 수 있게 start 값을 넘겨주되 (start + 1)을 안해서 이전 수와 똑같은 수를 선택할 수 있게끔 보내줬음.
 * 근데 중요한 게 이러면 start 값은 평생 초기 호출시에 넘겨줬던 1로만 사용돼서 사실상 의미 없는 코드가 되어버림 + 불필요한 메모리, 시간 발생해버림
 * 
 * 지금까지 N과 M 시리즈 3문제 풀었는데, 한 문제 풀 때마다 이제 느낌 알았다 완벽히 이해했다 하는데
 * 다음 문제 풀어보면 당연히 이렇게 하면 되지 -> 틀림 -> 아 이렇게? -> 틀림
 * 무한 반복 중
 * 나는 절대 이 시리즈를 완벽히 정복할 수 없을지도 모르겠다는 생각도 해봄.
 * 
 * 미묘한 차이가 결과에 있어서는 크게 차이가 나는데 아직 그 미묘한 부분을 컨트롤 하기가 힘든 것 같음.
 */