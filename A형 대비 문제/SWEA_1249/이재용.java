/**
 * @RunningTime 119ms
 * @Memory 37088kb
 * @Strategy BFS (너비 우선 탐색) / 다익스트라(Dijkstra) 알고리즘
 * 이 문제는 각 칸을 정점(vertex)으로, 각 칸의 복구 시간을 간선(edge)의 가중치(weight)로 하는
 * 최단 경로 문제로 볼 수 있습니다. (0, 0)에서 (N-1, N-1)까지 가는 데 걸리는 최소 복구 시간을 구해야 합니다.
 * * 1. 각 위치까지의 최소 복구 시간을 저장할 2차원 배열 `time`을 만들고, 모두 무한대(Integer.MAX_VALUE)로 초기화합니다.
 * 2. 시작점 (0, 0)의 복구 시간은 0이므로 `time[0][0] = 0`으로 설정하고, 큐에 시작점의 좌표를 넣습니다.
 * 3. 큐가 빌 때까지 다음을 반복합니다:
 * - 큐에서 현재 위치를 꺼냅니다.
 * - 현재 위치에서 상하좌우로 인접한 칸을 탐색합니다.
 * - 인접한 칸이 지도 범위 내에 있고, `(현재 위치까지의 최소 시간 + 인접한 칸의 복구 시간)`이 
 * `기존에 알려진 인접한 칸까지의 최소 시간`보다 작다면, 해당 경로가 더 효율적이므로
 * `time` 배열을 새로운 최소 시간으로 갱신하고, 인접한 칸의 좌표를 큐에 추가합니다.
 * 4. 탐색이 모두 끝나면 `time[N-1][N-1]`에 저장된 값이 출발지에서 도착지까지의 최소 복구 시간이 됩니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) throws Exception {
        // 여러 테스트 케이스의 출력을 한 번에 처리하기 위해 StringBuilder를 사용합니다.
        StringBuilder sb = new StringBuilder();
        // 빠른 입력을 위해 BufferedReader를 사용합니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 총 테스트 케이스의 수 T를 입력받습니다.
        int T = Integer.parseInt(br.readLine());
        // 각 테스트 케이스에 대해 반복합니다.
        for (int tc = 1; tc <= T; tc++) {
            // 해당 테스트 케이스의 정답을 저장할 변수입니다.
            int answer = 0;

            // 지도의 크기 N을 입력받습니다.
            int N = Integer.parseInt(br.readLine());
            // 각 지역의 복구 시간 정보를 저장할 2차원 배열 map을 선언합니다.
            int[][] map = new int[N][N];
            // N x N 크기의 지도 정보를 입력받습니다.
            for (int i = 0; i < N; i++) {
                // 지도 정보를 한 줄씩 문자열로 읽어옵니다.
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    // 문자 '0'~'9'를 정수 0~9로 변환하여 map 배열에 저장합니다.
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            // BFS를 위한 큐를 선언합니다. 큐에는 [row, col] 좌표가 저장됩니다.
            Queue<int[]> queue = new ArrayDeque<>();
            // (0,0)에서 각 좌표까지의 최소 복구 시간을 저장할 배열 time을 선언합니다.
            int[][] time = new int[N][N];
            
            // time 배열의 모든 값을 최댓값(무한대 역할)으로 초기화합니다.
            for (int i = 0; i < N; i++) {
                Arrays.fill(time[i], Integer.MAX_VALUE);
            }
            
            // 시작점 (0,0)의 복구 시간은 0입니다.
            time[0][0] = 0;
            // 탐색할 네 방향(상, 하, 좌, 우)을 정의합니다.
            int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            // 큐에 시작점 (0,0)을 추가합니다.
            queue.offer(new int[] { 0, 0 });
            
            // 큐가 비어있지 않은 동안 계속 탐색을 진행합니다.
            while (!queue.isEmpty()) {
                // 큐에서 현재 위치 정보를 꺼냅니다.
                int[] current = queue.poll();
                // 현재 위치에서 상, 하, 좌, 우 네 방향으로 이동을 시도합니다.
                for (int[] d : dir) {
                    // 다음 위치의 행(nr)과 열(nc) 좌표를 계산합니다.
                    int nr = current[0] + d[0];
                    int nc = current[1] + d[1];
                    
                    // 다음 위치가 지도 범위 내에 있고,
                    // 현재 위치를 거쳐 다음 위치로 가는 경로의 복구 시간 합이
                    // 기존에 알려진 다음 위치까지의 최소 복구 시간보다 작을 경우
                    if (0 <= nr && nr < N && 0 <= nc && nc < N
                            && map[nr][nc] + time[current[0]][current[1]] < time[nr][nc]) {
                        // 최소 복구 시간을 새로운 값으로 갱신합니다.
                        time[nr][nc] = map[nr][nc] + time[current[0]][current[1]];
                        // 다음 탐색을 위해 다음 위치를 큐에 추가합니다.
                        queue.offer(new int[] { nr, nc });
                    }
                }
            }
            // BFS가 종료되면 도착지(N-1, N-1)까지의 최소 복구 시간이 계산됩니다.
            answer = time[N - 1][N - 1];
            // 출력 형식에 맞게 결과를 StringBuilder에 추가합니다.
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // 모든 테스트 케이스의 결과를 한 번에 출력합니다.
        System.out.print(sb);
    }
}