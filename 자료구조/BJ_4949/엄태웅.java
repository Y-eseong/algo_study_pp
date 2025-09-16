import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BJ_4949 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			Stack<String> stack = new Stack<>();
			String[] arr = in.readLine().split("");
			//System.out.println(Arrays.toString(arr));
			if (arr[0].equals(".")) {break;}
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].equals("[") || arr[i].equals("(")) {
					stack.push(arr[i]);
				} else if (arr[i].equals("]")) {
					if(!(stack.isEmpty())) {
						String y = stack.peek();
						if (y.equals("[")) {
							stack.pop();
						}
						else {
							stack.push(arr[i]);
						}
					}
					else {
						stack.push(arr[i]);
					}
				} else if (arr[i].equals(")")) {
					if(!(stack.isEmpty())) {
						String y = stack.peek();
						if (y.equals("(")) {
							stack.pop();
						}
						else {
							stack.push(arr[i]);
						}
					}
					else {
						stack.push(arr[i]);
					}
				}
			}
			
			//System.out.println(stack);
			if (stack.isEmpty()) {
				System.out.println("yes");
			}
			else {System.out.println("no");}
			
			
		}
		
	}
}
