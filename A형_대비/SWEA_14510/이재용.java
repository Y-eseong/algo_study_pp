/**
 * @RunningTime 86 ms
 * @Memory 25472 KB
 * @Strategy 그리디(Greedy) 알고리즘
 * 모든 나무를 가장 높은 나무의 높이와 같게 만드는 최소 일수를 구하는 문제입니다.
 * 홀수 날에는 나무 하나에 높이 1만큼, 짝수 날에는 높이 2만큼 물을 줄 수 있습니다.
 * 1.  먼저 모든 나무가 도달해야 할 목표 높이(`maxHeight`)를 찾습니다.
 * 2.  각 나무가 목표 높이에 도달하기 위해 필요한 총 성장을 계산합니다. 이 성장을 +2 성장(짝수 날)과 +1 성장(홀수 날)으로 분해하여, 필요한 짝수 날의 총 횟수(`nT`)와 홀수 날의 총 횟수(`nO`)를 계산합니다.
 * 3.  홀수 날과 짝수 날은 번갈아 오므로, 필요한 `nO`와 `nT`의 개수를 최대한 비슷하게 맞춰야 효율적입니다. 만약 필요한 짝수 날(`nT`)이 홀수 날(`nO`)보다 2 이상 많으면, 짝수 날에 할 +2 성장을 홀수 날에 할 +1 성장 두 개로 바꿀 수 있습니다. (`nT`를 1 줄이고 `nO`를 2 늘림) 이 과정을 통해 `nT`와 `nO`의 차이를 1 이하로 맞춥니다.
 * 4.  조정이 끝난 후,
 * - `nT >= nO` 이면, 짝수 날의 횟수가 더 많거나 같으므로 짝수 날이 일수를 결정합니다. 필요한 총 일수는 `nT * 2`일이 됩니다.
 * - `nO > nT` 이면, 홀수 날의 횟수가 더 많으므로 홀수 날이 일수를 결정합니다. 필요한 총 일수는 `nO * 2 - 1`일이 됩니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
            // 해당 테스트 케이스의 정답(최소 일수)을 저장할 변수입니다.
            int answer = 0;

            // 나무의 개수 N을 입력받습니다.
            int N = Integer.parseInt(br.readLine());

            // 각 나무의 높이를 저장할 배열 H를 선언합니다.
            int[] H = new int[N];
            // 가장 높은 나무의 높이를 저장할 변수를 선언합니다.
            int maxHeight = Integer.MIN_VALUE;
            // 필요한 홀수 날(+1 성장)의 총 횟수를 저장할 변수입니다.
            int nO = 0;
            // 필요한 짝수 날(+2 성장)의 총 횟수를 저장할 변수입니다.
            int nT = 0;
            
            // 나무들의 높이를 한 줄로 입력받습니다.
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                // 각 나무의 높이를 배열에 저장합니다.
                H[i] = Integer.parseInt(st.nextToken());
                // 현재까지 가장 높은 나무의 높이를 갱신합니다.
                maxHeight = Math.max(maxHeight, H[i]);
            }
            
            // 모든 나무를 순회하며 필요한 성장의 양을 계산합니다.
            for (int i = 0; i < N; i++) {
                // 목표 높이와 현재 나무 높이의 차이를 구합니다.
                int diff = maxHeight - H[i];
                // 차이를 2로 나눈 몫만큼 +2 성장이 필요합니다 (짝수 날).
                nT += diff / 2;
                // 차이를 2로 나눈 나머지만큼 +1 성장이 필요합니다 (홀수 날).
                nO += diff % 2;
            }
            
            // 필요한 짝수 날(nT)과 홀수 날(nO)의 횟수 차이를 조절합니다.
            // nT가 nO보다 2 이상 크면 비효율적이므로, +2 성장 하나를 +1 성장 두 개로 바꿉니다.
            while ((nT - nO) > 1) {
                nT -= 1; // +2 성장 필요 횟수 1 감소
                nO += 2; // +1 성장 필요 횟수 2 증가
            }
            
            // 조정된 nT와 nO를 바탕으로 최소 일수를 계산합니다.
            // 짝수 날 필요 횟수가 홀수 날 필요 횟수보다 크거나 같다면
            if (nT >= nO) {
                // 짝수 날이 병목 지점이므로, nT번의 짝수 날이 필요합니다.
                // nT번의 짝수 날을 가지려면 최소 2*nT 일이 필요합니다.
                answer = nT * 2;
            }
            // 홀수 날 필요 횟수가 짝수 날 필요 횟수보다 많다면
            else { // nO > nT
                // 홀수 날이 병목 지점이므로, nO번의 홀수 날이 필요합니다.
                // nO번의 홀수 날을 가지려면 최소 2*nO - 1 일이 필요합니다.
                answer = (nO * 2) - 1;
            }

            // 출력 형식에 맞게 결과를 StringBuilder에 추가합니다.
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // 모든 테스트 케이스의 결과를 한 번에 출력합니다.
        System.out.print(sb);
    }
}