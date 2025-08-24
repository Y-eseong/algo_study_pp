import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	
//	static int N;
//	static int maxWeight;
//	static int[] ropes;
//	static int minWeight;
//	static boolean[] isUsed;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] ropes = new Integer[N];
        for (int i = 0; i < N; i++) {
        	ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes, Collections.reverseOrder()); // 내림차순
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, ropes[i] * (i + 1));
        }
        System.out.println(ans);
    }

//	private static void dfs(int idx) {
//		if (idx == N) {
//			int rope_MinWeight = Integer.MAX_VALUE; // 사용된 로프 중 가장 작은 로프의 길이
//			int cnt = 0;
//					
//			for (int i = 0; i < N; i++) {
//				if (isUsed[i]) {
//					rope_MinWeight = Math.min(rope_MinWeight, ropes[i]);
//					cnt++;
//				}
//			}
//			
//			int rope_MaxWeight = rope_MinWeight * cnt; // 현재 사용된 로프의 한계 중량
//			
//			maxWeight = Math.max(maxWeight, rope_MaxWeight);		
//			return;
//		}
//
//		isUsed[idx] = true;
//		dfs(idx + 1);
//		
//		isUsed[idx] = false;
//		dfs(idx + 1);
//	}
}
