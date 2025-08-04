import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15650 {

	static int N;
	static int M;
	static boolean[] isUsed;
	static List<Integer> li;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer sb = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(sb.nextToken());
		M = Integer.parseInt(sb.nextToken());
		
		isUsed = new boolean[N];
		li = new ArrayList<Integer>(); 
		int k = 0;
		
		func(k);
	}

	private static void func(int k) {
		if (k == M) {
//			Collections.sort(li); 사실 필요 없는 코드임. 원래 로직은 그냥 리스트에 값 넣고 sort하면 되는거 아닌가? 하고 풀었는데, 이러면 중복 순열도 출력하는 문제가 있었음.
			for (int a : li) {
				System.out.print(a + " ");
			} System.out.println("");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
				if (li.isEmpty() || li.get(k - 1) < i + 1) { // 제일 최근 값을 가져와서 지금 넣으려는 숫자가 제일 최근 값보다 클 때만 li에 추가. 자동으로 오름차순 구현.
					li.add(i + 1);
					isUsed[i] = true;
					func(k + 1);
					isUsed[i] = false;
					li.remove(k);
				}
			}
		}
	}

	
	/*
	 * 풀고 나서 쉬운 문제데 코드가 조금 지저분하지 않나라는 생각 + 인덱스로 실수할 수 있는 여지가 너무 많은 것 같아서
	 * 조금 찾아봤더니, 15649문제처럼 배열을 사용하면서 백트래킹을 돌 때 k와 idx를 같이 넘겨주는 방식이 더 괜찮은 것 같다고 느낌.
	 * 
	 * 현재 41번째 줄부터 51번째 줄까지의 for문을 다음과 같이 정의하면 됨.
	 * 
	 *  for (int i = idx; i <= N; i++) {
     *      arr[k] = i;
     *      func(k + 1, i + 1);
     *  }
     *  
     *  결국 k번째 수를 선택할 때, 현재 숫자를 idx로 넘겨줘서 해당 숫자 이후부터 선택될 수 있게끔 하는 방식
     *  즉, 무조건 이전에 선택한 수 보다 큰 수부터 선택하기 때문에 오름차순이 보장되는 것.
     *  대신, main에서 호출할 때는 func(0, 1) 이렇게 넘겨줘야 함. 0을 제외하기 위해서
	 */
}
