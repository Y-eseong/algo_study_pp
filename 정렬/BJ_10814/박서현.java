import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Object[]> members = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			members.add(new Object[] {Integer.parseInt(st.nextToken()), st.nextToken()});
		}

		Collections.sort(members, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				return (int)o1[0] - (int)o2[0];
			};
		});
		
		for(Object[] member : members) {
			sb.append(member[0]).append(" ").append(member[1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
