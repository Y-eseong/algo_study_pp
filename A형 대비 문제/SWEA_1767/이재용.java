/**
 * @RunningTime 161 ms
 * @Memory 29,684 KB
 * @Strategy 백트래킹
 * - 각 코어가 설치할 수 있는 전선의 모든 방향에 대한 조합을 탐색 (전선을 연결하지 않는 경우 포함)
 * - 탐색 과정에서 가장 많이 연결된 코어의 수를 기준으로, 전선 길이의 최솟값을 갱신
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int N, maxCoreCount, minLineLength;
	static int[][] map;
	static ArrayList<int[]> cores;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			maxCoreCount = 0;
			minLineLength = Integer.MAX_VALUE;

			// N 입력받아 NxN 지도 초기화
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			// 끝에 있는 코어는 이미 연결된 코어로 간주
			int connectedCore = 0;
			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						// 코어의 위치가 지도의 끝에 있는 경우
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							// 가지치기 #1: 연결된 코어로 간주하고 탐색하지 않음
							connectedCore++;
						}
						// 끝에 있지 않은 코어들은 리스트에 저장해서 탐색에 사용
						else {
							cores.add(new int[] { i, j });
						}
					}
				}
			}

			solve(0, connectedCore, 0);

			sb.append("#").append(tc).append(" ").append(minLineLength).append("\n");
		}
		System.out.print(sb);
	}

	/**
	 * @description 각각의 코어가 연결할 수 있는 방향을 모두 고려합니다. 마지막 코어까지 고려가 끝나면 가능한 모든 경우에서, 최대한
	 *              많이 연결되는 코어의 전선 길이의 최솟값을 계산합니다.
	 * @param coreCount: 리스트에서 순차적으로 탐색하기 위한 코어의 인덱스
	 * @param connectedCore: 현재까지 탐색하면서 전선 설치를 성공시킨 코어의 개수
	 * @param lineLength: 현재까지 설치에 성공한 전선의 길이 총합
	 */
	private static void solve(int coreCount, int connectedCore, int lineLength) {
		// 기저 조건: 모든 코어를 고려했다면, 최소전선길이를 계산하고 메소드 끝내기
		if (coreCount == cores.size()) {
			// 연결에 성공한 코어의 개수가 최댓값인지 확인
			// 최댓값이면, 최댓값을 새로 갱신하고, 이 연결이 가지는 전선의 길이로 최소전선길이를 초기화
			if (maxCoreCount < connectedCore) {
				maxCoreCount = connectedCore;
				minLineLength = lineLength;
			}
			// 연결에 성공한 코어가 최댓값과 같으면,
			// 이 연결이 가지는 전선의 길이와 기존 최소전선길이를 비교하여 최솟값 갱신 시도
			if (maxCoreCount == connectedCore) {
				minLineLength = Math.min(minLineLength, lineLength);
			}
			return;
		}

		// 현재 코어의 모든 방향 탐색
		for (int[] d : dir) {
			// 현재 코어의 위치
			int row = cores.get(coreCount)[0];
			int col = cores.get(coreCount)[1];
			
			// 가지치기 #2: 전선 연결이 가능한 경우에만 전선을 연결
			if (isPossible(row, col, d)) {
				int length = setLine(row, col, d, 2);
				// 이 상태에서 다음 코어를 고려
				solve(coreCount + 1, connectedCore + 1, lineLength + length);
				// 다음 방향 확인을 위해 설치한 전선을 회수
				setLine(row, col, d, 0);
			}
		}
		
		// 현재 코어에서 전선을 설치하지 않은 경우도 고려
		solve(coreCount + 1, connectedCore, lineLength);
	}

	/**
	 * @description 지도에 전선을 설치하거나 회수합니다. type 매개변수에 2를 입력해서 전선을 설치하고, 0을 입력해서 전선을 회수합니다.
	 * @param row
	 * @param col
	 * @param d
	 * @param type 2: 전선 설치 | 0: 전선 회수
	 * @return 전선 설치가 끝나면, 해당 전선의 길이를 반환합니다.
	 */
	private static int setLine(int row, int col, int[] d, int type) {
		int length = 0;
		while (true) {
			row += d[0];
			col += d[1];
			length++;
			map[row][col] = type;
			if (row == 0 || row == N - 1 || col == 0 || col == N - 1) {
				return length;
			}
		}
	}

	/**
	 * @Description 코어의 전선을 연결할 수 있는지 미리 탐색하여 확인합니다. 
	 * @param row
	 * @param col
	 * @param d
	 * @return 끝까지 갈 수 있다면 true를 반환, 중간에 막히면 false를 반환합니다.
	 */
	private static boolean isPossible(int row, int col, int[] d) {
		while (true) {
			row += d[0];
			col += d[1];
			// 중간에 갈 수 없는 길을 만나면 false 리턴
			if (map[row][col] != 0) {
				return false;
			}
			
			// 끝까지 도달했다면 true 리턴
			if (row == 0 || row == N - 1 || col == 0 || col == N - 1) {
				return true;
			}
		}
	}
}