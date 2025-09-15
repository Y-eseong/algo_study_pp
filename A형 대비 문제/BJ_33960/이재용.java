/**
 * @RunningTime 시간 초과
 * @Memory 시간 초과
 * @Strategy 백트래킹(Backtracking) / 완전 탐색(Brute-force)
 * 합이 10이 되는 연속 부분 배열을 찾아 제거하는 작업을 최대 몇 번 할 수 있는지 찾는 문제입니다.
 * 이 코드는 백트래킹을 사용하여 가능한 모든 '제거 순서'를 탐색합니다.
 *
 * 1. 현재 배열 상태에서 합이 10이 되는 모든 연속 부분 배열을 찾습니다.
 * 2. 찾은 부분 배열 중 하나를 선택하여 제거(배열 값을 0으로 변경)하고, 점수를 1 올린 뒤 다음 단계를 재귀적으로 호출합니다.
 * 3. 재귀 호출이 끝나면, 배열을 원래 상태로 복원(백트래킹)하고 다음 선택지를 탐색합니다.
 * 4. 더 이상 제거할 부분 배열이 없으면, 현재까지의 점수를 최대 점수와 비교하여 갱신합니다.
 *
 * @TimeLimitReason (시간 초과 원인)
 * 이 방식은 제거할 수 있는 부분 배열이 여러 개일 때, 어떤 것을 먼저 제거할지에 대한 모든 순서 조합을 탐색합니다.
 * 배열의 크기가 커지면 탐색해야 할 경우의 수가 기하급수적으로 늘어나(조합 폭발), 정해진 시간 내에 모든 경우를 탐색할 수 없게 됩니다.
 * 각 재귀 호출마다 O(N^3)에 가까운 연산을 반복하므로 매우 비효율적입니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, answer; // N: 배열의 크기, answer: 최대 점수
	static int[] A; // 입력받은 정수 배열

	public static void main(String[] args) throws Exception {
		// 최대 점수를 저장할 변수를 가장 작은 값으로 초기화
		answer = Integer.MIN_VALUE;
		// 빠른 입력을 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 배열의 크기 N 입력
		N = Integer.parseInt(br.readLine());
		// N 크기의 배열 A 할당
		A = new new int[N];
		// 배열의 원소들을 공백 기준으로 입력받음
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// 백트래킹 함수를 초기 점수 0으로 호출
		backtrack(0);
		// 최종적으로 계산된 최대 점수 출력
		System.out.println(answer);
	}

	// 가능한 모든 제거 순서를 탐색하는 재귀 함수
	private static void backtrack(int score) {
		// 이중 for문으로 모든 가능한 연속 부분 배열을 탐색
		// i: 부분 배열의 길이 (윈도우의 크기)
		for (int i = 1; i <= N; i++) {
			// j: 부분 배열의 시작 인덱스
			for (int j = 0; j <= N - i; j++) {
				// 현재 탐색 중인 부분 배열의 합을 계산
				int sum = 0;
				for (int k = j; k < j + i; k++) {
					sum += A[k];
				}

				// 만약 부분 배열의 합이 10이라면
				if (sum == 10) {
					// 1. 상태 저장: 재귀 호출 후 배열을 복원하기 위해 원래 값을 임시 배열에 저장
					int[] temp = new int[i];
					int cnt = 0;
					for (int k = j; k < j + i; k++) {
						temp[cnt++] = A[k];
						// 2. 상태 변경: 부분 배열을 제거했다는 의미로 해당 위치의 값을 0으로 변경
						A[k] = 0;
					}

					// 3. 재귀 호출: 점수를 1 증가시키고 다음 상태를 탐색
					backtrack(score + 1);

					// 4. 상태 복원 (백트래킹): 재귀 호출이 끝난 후, 다음 탐색을 위해 배열을 원래 상태로 되돌림
					cnt = 0;
					for (int k = j; k < j + i; k++) {
						A[k] = temp[cnt++];
					}
				}
			}
		}
		// 더 이상 합이 10인 부분 배열을 찾을 수 없을 때 (for문이 모두 끝났을 때),
		// 현재까지의 점수(score)를 최대 점수(answer)와 비교하여 갱신
		answer = Math.max(answer, score);
	}
}