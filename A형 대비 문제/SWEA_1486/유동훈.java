import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * @link: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw
 * @performance: 83 ms, 25,344 kb
 * @note: 조합은 N개 중 K 개를 뽑아서 검사하는 것, 백트래킹은 탐색을 해 나가면서 가지치기 + 탐색 종료 시 갱신 처리와 같이 구분 할 수 있음
 * 사실 그냥 느낌으로 구분했었는데 뭔가 명확한 가이드라인이 필요할 것 같아서 정리해봄.
 * 따라서 해당 문제는 선반의 높이 B보다 크거나 같아지는 점원들의 조합을 뽑은 뒤, 점원들이 합체한 키랑 선반의 높이의 차를 구해서 최소값을 갱신하는 문제
 * @timecomplex: 
 */
public class Solution {

	static int N; // 점원 수
	static int B; // 선반의 높이
	static StringTokenizer st;
	static int ans;
	static int[] Hei;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder();
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			Hei = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Hei[i] = Integer.parseInt(st.nextToken());
			}
			
			if (N == 1) {
				sb.append("#").append(test_case).append(" ").append(0);
				System.out.println(sb);
				continue;
			}
			if (N == 2) {
				sb.append("#").append(test_case).append(" ").append(Hei[0] + Hei[1] - B);
				System.out.println(sb);
				continue;
			}
			
			// 큰 값들 부터 넣기 시작하면 훨씬 빨리 ans를 갱신해주고 이후 탐색에서 가지치기 될 확률이 올라가니까
			// 유의미한 시간 차이를 내지 않을까 실험 해보려고 추가한 코드
			// 그런데 테케가 많이 없어서 그런지 차이가 없음
//			Arrays.sort(Hei);
//			for (int i = 0; i < N / 2; i++) {
//			    int temp = Hei[i];
//			    Hei[i] = Hei[N - 1 - i];
//			    Hei[N - 1 - i] = temp;
//			}
			
			dfs(0, 0);
			
			
			sb.append("#").append(test_case).append(" ").append(ans);
			System.out.println(sb);
		}
	}

	private static void dfs(int idx, int curHei) {
		// 종료 조건: 
		if (curHei >= B) {
			ans = Math.min(ans, (curHei - B));
			return;
		}
		
		if (idx == N) return;
		if (ans != Integer.MAX_VALUE && curHei >= B + ans) return;
		
		for (int i = idx; i < N; i++) {
			dfs(i + 1, curHei + Hei[i]);
//			dfs(i + 1, curHei); // 정신 나갔네 조합으로 푼다고 해놓고 백트래킹 함?
		}
	}

}
