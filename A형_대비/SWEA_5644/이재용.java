/**
 * @RunningTime 105ms
 * @Memory 29440KB
 * @Strategy 시뮬레이션 + 완전 탐색(Brute-force)
 * 1.  먼저 0초부터 M초까지 각 사용자의 시간에 따른 위치를 모두 계산합니다.
 * 2.  계산된 위치를 바탕으로, 각 사용자가 매 시간 어떤 BC(Battery Charger)의 범위에 들어가는지 미리 파악하여 배열에 저장해 둡니다.
 * (AtimeBC[시간][BC번호], BtimeBC[시간][BC번호])
 * 3.  다시 0초부터 M초까지 시간을 진행시키면서, 매 시간마다 충전할 수 있는 최댓값을 구합니다.
 * 4.  각 시간의 최댓값은, 사용자 A가 선택할 수 있는 BC와 사용자 B가 선택할 수 있는 BC의 모든 조합을 고려하여 계산합니다.
 * - 두 사용자가 서로 다른 BC를 선택하는 경우: 두 BC의 충전량을 합산합니다.
 * - 두 사용자가 동일한 BC를 선택하는 경우: 해당 BC의 충전량만 계산합니다. (나눠 가짐)
 * - 한 사용자만 BC를 선택하는 경우: 해당 BC의 충전량만 계산합니다.
 * 5.  각 시간대별로 구한 최댓값을 모두 더하여 최종 답을 구합니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	// 이동 방향을 정의한 배열 (0:이동안함, 1:상, 2:우, 3:하, 4:좌) - Y, X 좌표계 기준
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	// A사용자가 각 시간(i)에 각 BC(j)의 범위에 있는지 여부를 저장 (1이면 범위 내)
	static int[][] AtimeBC;
	// B사용자가 각 시간(i)에 각 BC(j)의 범위에 있는지 여부를 저장 (1이면 범위 내)
	static int[][] BtimeBC;
	// BC의 총 개수
	static int A;

	public static void main(String[] args) throws Exception {
		// 출력을 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		// 입력을 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 수 T 입력
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 최종 정답(총 충전량)을 저장할 변수
			int answer = 0;

			// 한 줄을 읽어 총 이동 시간(M)과 BC의 개수(A)를 파싱
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			// 사용자의 이동 정보를 저장할 배열 (0초: 이동안함 + M초까지의 이동)
			int[] userA = new int[M + 1];
			int[] userB = new int[M + 1];
			// 각 사용자가 시간별로 BC 범위에 있는지 저장할 배열 초기화
			AtimeBC = new int[M + 1][A];
			BtimeBC = new int[M + 1][A];

			// 사용자 A의 이동 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자 B의 이동 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}

			// BC 정보를 저장할 객체 배열
			BC[] bc = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				bc[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// --- 1단계: 시간에 따른 사용자 위치 및 BC 접속 가능 여부 미리 계산 ---
			// 사용자 A의 초기 위치
			int rowA = 1;
			int colA = 1;
			// 사용자 B의 초기 위치
			int rowB = 10;
			int colB = 10;
			// 0초(시작 위치)부터 M초(모든 이동 완료 후)까지 반복
			for (int i = 0; i <= M; i++) {
				// i초 시점의 사용자 위치 갱신 (i=0일 때는 userA[0]=0이므로 이동 안함)
				rowA += dir[userA[i]][0];
				colA += dir[userA[i]][1];
				rowB += dir[userB[i]][0];
				colB += dir[userB[i]][1];
				// 모든 BC에 대해 현재 사용자와의 거리를 계산
				for (int j = 0; j < A; j++) {
					// 사용자 A가 j번째 BC의 충전 범위 내에 있는지 확인 (맨해튼 거리)
					if(Math.abs(bc[j].X - colA) + Math.abs(bc[j].Y - rowA) <= bc[j].C) {
						AtimeBC[i][j] = 1; // 범위 내에 있다면 1로 표시
					}
					// 사용자 B가 j번째 BC의 충전 범위 내에 있는지 확인
					if(Math.abs(bc[j].X - colB) + Math.abs(bc[j].Y - rowB) <= bc[j].C) {
						BtimeBC[i][j] = 1; // 범위 내에 있다면 1로 표시
					}
				}
			}

			// --- 2단계: 매 시간마다 최대 충전량 계산 ---
			// 0초부터 M초까지 각 시간대별로 최대 충전량을 계산
			for (int i = 0; i <= M; i++) {
				// 현재 시간(i)에서의 최대 충전량
				int max = 0;
				// 사용자 A가 선택할 BC(j)와 사용자 B가 선택할 BC(k)의 모든 조합을 탐색
				for (int j = 0; j < A; j++) { // A가 선택할 BC
					for (int k = 0; k < A; k++) { // B가 선택할 BC
						// Case 1: A와 B가 동일한 BC(j==k)에 접속 가능할 경우
						if(AtimeBC[i][j] == 1 && BtimeBC[i][k] == 1 && j == k) {
							max = Math.max(max, bc[j].P); // 하나의 충전량만 더함
						}
						// Case 2: A와 B가 서로 다른 BC(j!=k)에 접속 가능할 경우
						else if(AtimeBC[i][j] == 1 && BtimeBC[i][k] == 1 && j != k) {
							max = Math.max(max, bc[j].P + bc[k].P); // 두 충전량을 합산
						}
						// Case 3: A만 BC(j)에 접속 가능하고, B는 BC(k)에 접속 불가능할 경우
						else if(AtimeBC[i][j] == 1 && BtimeBC[i][k] != 1) {
							max = Math.max(max, bc[j].P); // A의 충전량만 더함
						}
						// Case 4: B만 BC(k)에 접속 가능하고, A는 BC(j)에 접속 불가능할 경우
						else if(AtimeBC[i][j] != 1 && BtimeBC[i][k] == 1) {
							max = Math.max(max, bc[k].P); // B의 충전량만 더함
						}
					}
				}
				// 현재 시간(i)에 얻은 최대 충전량을 최종 정답에 더함
				answer += max;
			}
			// 테스트 케이스별 결과 저장
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		// 모든 결과 한 번에 출력
		System.out.print(sb);
	}

	// BC(Battery Charger)의 정보를 담는 클래스
	static class BC {
		// X좌표, Y좌표, 충전범위(C), 처리량(P)
		int X, Y, C, P;

		public BC(int X, int Y, int C, int P) {
			this.X = X;
			this.Y = Y;
			this.C = C;
			this.P = P;
		}
	}
}