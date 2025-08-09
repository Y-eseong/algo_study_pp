import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Member[] members = new Member[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			members[i] = new Member(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		Arrays.sort(members, (o1, o2) -> o1.age - o2.age);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(members[i].age).append(" ").append(members[i].Name).append("\n");
		}
		System.out.println(sb);
	}
	
	private static class Member {
		int age;
		String Name;
		
		public Member(int age, String name) {
			this.age = age;
			Name = name;
		}
	}
}