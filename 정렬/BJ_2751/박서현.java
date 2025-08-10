import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Integer> arr = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int n=0; n<N; n++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(arr);
		
		for(int a:arr) {
			sb.append(a);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
