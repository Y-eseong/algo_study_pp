/**
 * @RunningTime 92ms
 * @Memory 13632kb
 * @Strategy 조합(Power Set) + 너비 우선 탐색(BFS)
 * 이 문제는 N개의 구역을 두 개의 선거구로 나누는 모든 경우의 수를 탐색하여,
 * 각 경우가 유효한지 확인하고 인구 수 차이의 최솟값을 찾는 방식으로 해결합니다.
 *
 * 1.  **선거구 나누기 (조합):**
 * 재귀 함수를 이용해 N개의 구역 중 첫 번째 선거구(A)에 포함될 구역들의 모든 조합(부분집합)을 생성합니다.
 * A 선거구에 포함되지 않은 나머지 구역들은 자동으로 두 번째 선거구(B)가 됩니다.
 *
 * 2.  **유효성 검사 (BFS):**
 * 생성된 하나의 조합에 대해, 두 선거구(A, B)가 각각의 조건을 만족하는지 확인합니다.
 * - 조건 1: A 선거구에 속한 구역들이 모두 연결되어 있는가? -> BFS를 통해 확인
 * - 조건 2: B 선거구에 속한 구역들이 모두 연결되어 있는가? -> BFS를 통해 확인
 * - BFS 탐색 시, A 선거구에 속한 임의의 한 구역에서 시작하여 같은 A 선거구에 속한 모든 구역을 방문할 수 있는지 체크합니다. B 선거구도 동일하게 확인합니다.
 *
 * 3.  **인구 차이 계산:**
 * 만약 두 선거구 모두 연결되어 있다면, 해당 조합은 유효한 나누기 방법입니다.
 * 두 선거구의 인구 수 총합을 각각 계산하고, 그 차의 절댓값을 구합니다.
 * 이 값을 기존의 최솟값과 비교하여 더 작으면 갱신합니다.
 *
 * 4.  **결과 출력:**
 * 모든 조합에 대한 탐색이 끝난 후, 한 번이라도 유효한 경우가 있었다면 계산된 최솟값을,
 * 유효한 경우가 한 번도 없었다면 -1을 출력합니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer; // N: 구역의 수, answer: 인구 차이 최솟값
    static ArrayList<Integer>[] adjList; // 각 구역의 인접 정보를 저장하는 인접 리스트
    static int[] popOfZone; // 각 구역의 인구 수를 저장하는 배열
    static boolean flag; // 유효한 선거구 조합이 하나라도 있었는지 확인하는 플래그

    public static void main(String[] args) throws Exception {
        // 최솟값을 찾아야 하므로 answer를 정수 최댓값으로 초기화
        answer = Integer.MAX_VALUE;
        // 유효한 조합이 아직 없으므로 flag를 false로 초기화
        flag = false;
        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 구역의 수 N 입력
        N = Integer.parseInt(br.readLine());
        // 각 구역의 인구 수 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        popOfZone = new int[N + 1]; // 1-based 인덱싱을 위해 N+1 크기로 할당
        for (int i = 1; i <= N; i++) {
            popOfZone[i] = Integer.parseInt(st.nextToken());
        }
        // 인접 리스트 초기화
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 각 구역의 인접 구역 정보 입력받아 인접 리스트 구성 (양방향)
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int numberOfZone = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numberOfZone; j++) {
                int adjNode = Integer.parseInt(st.nextToken());
                adjList[i].add(adjNode);
                // 양방향 그래프이므로 반대 방향도 추가하지만, 입력에서 중복으로 주어지므로 한 번만 해도 무방
                // adjList[adjNode].add(i); // 코드 원본 유지
            }
        }
        // 부분집합(조합)을 생성하여 모든 경우의 수를 탐색하는 재귀 함수 호출
        powSet(1, new boolean[N + 1]);
        
        // 만약 유효한 조합이 한 번도 없었다면(flag가 false이면)
        if (!flag) {
            // 문제 조건에 따라 -1로 변경
            answer = -1;
        }
        // 최종 결과 출력
        System.out.println(answer);
    }

    // 부분집합을 생성하는 재귀 함수 (cnt: 현재 고려할 구역 번호, isSelected: A 선거구 포함 여부)
    private static void powSet(int cnt, boolean[] isSelected) {
        // 기저 조건: 모든 구역에 대한 선택이 완료되었을 때
        if (cnt == N + 1) {
            // A 선거구와 B 선거구가 각각 연결되어 있는지 확인
            // --- A 선거구 연결성 검사 (BFS) ---
            boolean[] visited = new boolean[N + 1];
            Queue<Integer> queue = new ArrayDeque<>();
            // A 선거구에 속한 첫 번째 구역을 찾아 큐에 넣고 시작점으로 설정
            for (int i = 1; i <= N; i++) {
                if(isSelected[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    break;
                }
            }
            // BFS 실행
            while(!queue.isEmpty()) {
                int current = queue.poll();
                for (int next : adjList[current]) {
                    // 방문하지 않았고, A 선거구에 속한 구역이라면 큐에 추가
                    if (!visited[next] && isSelected[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            // A 선거구가 모두 연결되었는지 확인
            boolean isClearA = true;
            for (int i = 1; i <= N; i++) {
                // A선거구로 선택되었는데 방문되지 않았거나, B선거구인데 방문되었다면 연결 실패
                // (이 코드에서는 B선거구인데 방문되는 경우는 없음) => 굳이 조건 추가 안 해도 되는 부분!
                if (isSelected[i] && !visited[i]) {
                    isClearA = false;
                    break;
                }
            }
            
            // A 선거구가 연결되어 있다면, B 선거구도 확인
            if (isClearA) {
                // --- B 선거구 연결성 검사 (BFS) ---
                visited = new boolean[N + 1]; // 방문 배열 초기화
                // B 선거구에 속한 첫 번째 구역을 찾아 큐에 넣고 시작점으로 설정
                for (int i = 1; i <= N; i++) {
                    if (!isSelected[i]) {
                        queue.offer(i);
                        visited[i] = true;
                        break;
                    }
                }
                // BFS 실행
                while(!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int next : adjList[current]) {
                        // 방문하지 않았고, B 선거구에 속한 구역이라면 큐에 추가
                        if (!visited[next] && !isSelected[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                }
                // B 선거구가 모두 연결되었는지 확인
                boolean isClearB = true;
                for (int i = 1; i <= N; i++) {
                    // B선거구로 선택되었는데 방문되지 않았다면 연결 실패
                    if (!isSelected[i] && !visited[i]) {
                        isClearB = false;
                        break;
                    }
                }
                
                // A, B 선거구 모두 연결되어 있고, 둘 다 비어있지 않은 경우
                if (isClearB) {
                    // (코드가 isClearA, isClearB를 통과했다는 것은 양쪽 다 노드가 1개 이상 존재함을 내포함)
                    int popOfA = 0;
                    int popOfB = 0;
                    // 각 선거구의 인구수 계산
                    for (int i = 1; i <= N; i++) {
                        if (isSelected[i]) {
                            popOfA += popOfZone[i];
                        }
                        else {
                            popOfB += popOfZone[i];
                        }
                    }
                    flag = true; // 유효한 조합을 찾았음을 표시
                    // 인구 차이의 최솟값 갱신
                    answer = Math.min(answer, Math.abs(popOfA - popOfB));
                }
            }
            return; // 현재 조합에 대한 탐색 종료
        }
        
        // 재귀 파트: 현재 구역(cnt)을 A 선거구에 포함시키는 경우
        isSelected[cnt] = true;
        powSet(cnt + 1, isSelected);
        // 현재 구역(cnt)을 A 선거구에 포함시키지 않는 경우 (즉, B 선거구에 포함)
        isSelected[cnt] = false;
        powSet(cnt + 1, isSelected);
    }
}