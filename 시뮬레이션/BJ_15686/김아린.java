import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min;
	static int[] checked;
	static List<int[]> houses = new ArrayList<>();
	static List<int[]> chicken = new ArrayList<>();
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        for(int n = 0; n < N; n++) {
        	st = new StringTokenizer(br.readLine());
        	for(int nn = 0; nn < N; nn++) {
        		int t = Integer.parseInt(st.nextToken());
        		if(t == 1) houses.add(new int[]{n, nn});
        		else if(t == 2) chicken.add(new int[]{n, nn});
        	}
        }
        checked = new int[chicken.size()];
        calc(0, 0);
        System.out.println(min);
    }
	
	public static void calc(int start, int depth) {
		if(depth == M) {
			int sum = 0;
			// 치킨집 두 개 중에서 집에 더 가까운 것을 더함 
			for(int[] h : houses) {
				int tempMin = Integer.MAX_VALUE;
				for(int x = 0; x < chicken.size(); x++) {
					if(checked[x] == 1) {
						int temp = Math.abs(h[0] - chicken.get(x)[0]) + Math.abs(h[1] - chicken.get(x)[1]);
						if(tempMin > temp) tempMin = temp;
					}
				}
				sum += tempMin;
			}
			min = Math.min(min, sum);
			return;
		}
		
		for(int i = start; i < chicken.size(); i++) {
			if(checked[i] == 0) {
				checked[i] = 1;
				calc(i + 1, depth + 1);
				checked[i] = 0;
				
			}
		}
	}
}
