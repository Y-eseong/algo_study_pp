/**
 * @RunningTime 228ms
 * @Memory 30912KB
 * @Strategy 브루트포스(Brute-force)
 * 가능한 모든 기준점 (x, y)와 경계 길이 (d1, d2)의 조합을 탐색합니다.
 * 각 조합마다 5개의 선거구로 나누고, 각 선거구의 인구수를 계산합니다.
 * 계산된 인구수의 최댓값과 최솟값의 차이를 구하고, 이 차이가 최소가 되는 경우를 찾아 업데이트합니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {

		// 빠른 입력을 위해 BufferedReader를 사용합니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 지도의 크기 N을 입력받습니다.
		int N = Integer.parseInt(br.readLine());
		// 각 구역의 인구수를 저장할 2차원 배열 A를 선언합니다. (1-based 인덱싱을 위해 N+1 크기로)
		int[][] A = new int[N + 1][N + 1];
		// 전체 인구수를 저장할 변수 total을 선언합니다.
		int total = 0;
		// 인구 차이의 최솟값을 저장할 변수 answer를 정수 최댓값으로 초기화합니다.
		int answer = Integer.MAX_VALUE;

		// N x N 크기의 지도에 각 구역의 인구수 정보를 입력받습니다.
		for (int i = 1; i <= N; i++) {
			// 한 줄을 공백 기준으로 나누기 위해 StringTokenizer를 사용합니다.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				// (i, j) 위치의 인구수를 배열 A에 저장합니다.
				A[i][j] = Integer.parseInt(st.nextToken());
				// 전체 인구수에 현재 인구수를 더합니다.
				total += A[i][j];
			}
		}

		// 기준점 (x, y)와 경계 길이 (d1, d2)를 정하기 위해 모든 경우의 수를 탐색합니다.
		// x: 기준점의 행 좌표
		for (int x = 1; x <= N; x++) {
			// y: 기준점의 열 좌표
			for (int y = 1; y <= N; y++) {
				// d1: 경계 길이 1
				for (int d1 = 1; d1 <= N; d1++) {
					// d2: 경계 길이 2
					for (int d2 = 1; d2 <= N; d2++) {
						// 정해진 (x, y, d1, d2)로 만들어지는 선거구가 지도 범위를 벗어나는지 확인합니다.
						if (1 <= x && x < x + d1 + d2 && x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N) {
							// 5번 선거구의 경계를 표시하기 위한 boolean 배열을 선언합니다.
							boolean[][] bound = new boolean[N + 1][N + 1];

							// 5번 선거구의 경계선을 그립니다. (총 4개의 직선)
							// 1. (x, y)부터 (x+d1, y-d1)까지
							for (int i = 0; i <= d1; i++) {
								bound[x + i][y - i] = true;
							}
							// 2. (x, y)부터 (x+d2, y+d2)까지
							for (int i = 0; i <= d2; i++) {
								bound[x + i][y + i] = true;
							}
							// 3. (x+d1, y-d1)부터 (x+d1+d2, y-d1+d2)까지
							for (int i = 0; i <= d2; i++) {
								bound[x + d1 + i][y - d1 + i] = true;
							}
							// 4. (x+d2, y+d2)부터 (x+d1+d2, y-d1+d2)까지
							for (int i = 0; i <= d1; i++) {
								bound[x + d2 + i][y + d2 - i] = true;
							}

							// 각 선거구(1~5)의 인구수를 저장할 배열을 선언합니다.
							int[] area = new int[6];

							// 1번 선거구의 인구수를 계산합니다.
							for (int r = 1; r < x + d1; r++) {
								for (int c = 1; c <= y; c++) {
									// 5번 선거구의 경계를 만나면 해당 행의 계산을 중단합니다.
									if (bound[r][c]) {
										break;
									}
									area[1] += A[r][c];
								}
							}
							// 2번 선거구의 인구수를 계산합니다.
							for (int r = 1; r <= x + d2; r++) {
								for (int c = N; c > y; c--) {
									// 5번 선거구의 경계를 만나면 해당 행의 계산을 중단합니다.
									if (bound[r][c]) {
										break;
									}
									area[2] += A[r][c];
								}
							}
							// 3번 선거구의 인구수를 계산합니다.
							for (int r = x + d1; r <= N; r++) {
								for (int c = 1; c < y - d1 + d2; c++) {
									// 5번 선거구의 경계를 만나면 해당 행의 계산을 중단합니다.
									if (bound[r][c]) {
										break;
									}
									area[3] += A[r][c];
								}
							}
							// 4번 선거구의 인구수를 계산합니다.
							for (int r = x + d2 + 1; r <= N; r++) {
								for (int c = N; c >= y - d1 + d2; c--) {
									// 5번 선거구의 경계를 만나면 해당 행의 계산을 중단합니다.
									if (bound[r][c]) {
										break;
									}
									area[4] += A[r][c];
								}
							}

							// 5번 선거구의 인구수는 전체 인구수에서 1~4번 선거구의 인구수를 빼서 계산합니다.
							area[5] = total;
							for (int i = 1; i < 5; i++) {
								area[5] -= area[i];
							}

							// 현재 조합에서 인구가 가장 많은 선거구와 가장 적은 선거구를 찾습니다.
							int max = Integer.MIN_VALUE;
							int min = Integer.MAX_VALUE;
							for (int i = 1; i <= 5; i++) {
								max = Math.max(max, area[i]);
								min = Math.min(min, area[i]);
							}
							// (최대 인구 - 최소 인구) 값을 기존의 최솟값과 비교하여 더 작은 값으로 업데이트합니다.
							answer = Math.min(answer, max - min);
						}
					}
				}
			}
		}
		// 모든 경우의 수 탐색 후, 최종적으로 구한 최솟값을 출력합니다.
		System.out.println(answer);
	}
}