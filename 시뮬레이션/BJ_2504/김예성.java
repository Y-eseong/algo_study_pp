import java.io.*;
import java.util.*;

public class Main {
	
	static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String in = br.readLine();
		int sum = 0;
		int a = 1;
		boolean check = false;
		
		for(int i=0; i<in.length(); i++) {
			if(in.charAt(i) == '(') {
				a *= 2;
				if(i <= in.length()-2) {
					if(in.charAt(i+1) == ')')
						sum += a;
				}
				stack.push(in.charAt(i));
			}
			else if(in.charAt(i) == '[') {
				a *= 3;
				if(i<=in.length()-2) {
					if(in.charAt(i+1) == ']')
						sum += a;
				}
				stack.push(in.charAt(i));
			}
			else if(in.charAt(i) == ')') {
				if(stack.empty() || stack.peek() != '(') {
					check = true;
				}
				else stack.pop();
				a /= 2;
			}
			else if(in.charAt(i) == ']') {
				if(stack.empty() || stack.peek() != '[') {
					check = true;
				}
				else stack.pop();
				a /= 3;
			}
		}
		if(check || !stack.empty())
			sb.append(0);
		else
			sb.append(sum);
		System.out.println(sb);
	}
}