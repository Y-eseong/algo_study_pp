import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<Long> inputs = new ArrayList<>();
		
		// first line
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int cnt = temp.length;
		String[] strings = Arrays.copyOf(temp, N + 1);
		
		while(cnt < N + 1) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				strings[cnt] = st.nextToken();
				cnt++;
			}
		}


		for(int i = 1; i <= N; i++) {
			sb = new StringBuilder();
			sb.append(strings[i]).reverse();
			inputs.add(Long.parseLong(sb.toString()));
		}
		
		Collections.sort(inputs);
		
		sb = new StringBuilder();
		for(Long num : inputs) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb);
	}
}
