import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ,;");
		ArrayList<String> tokens = new ArrayList<>();
		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		Deque<String> stack = new ArrayDeque<>();
		for (int i = 1; i < tokens.size(); i++) {
			st = new StringTokenizer(tokens.get(i), "&[]*", true);
			String varName = st.nextToken();
			sb.append(tokens.get(0));
			while (st.hasMoreTokens()) {
				stack.push(st.nextToken());
			}
			while (!stack.isEmpty()) {
				String current = stack.pop();
				if (current.equals("]")) {
					sb.append("[]");
					stack.pop();
				}
				else {
					sb.append(current);
				}
			}
			sb.append(" ").append(varName).append(";").append("\n");
		}
		System.out.println(sb);
	}
}