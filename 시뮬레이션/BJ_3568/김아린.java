
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		String[] arr = br.readLine().split(" ");
		String head = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			String temp = arr[i].replace(",", "").replace(";", "");
			String value = "";
			StringBuffer sbb = new StringBuffer();
			String type = "";
			for(int n = 0; n < temp.length(); n++) {
				if(((int) temp.charAt(n) > 64 && (int) temp.charAt(n) < 91) || ((int) temp.charAt(n) > 96 && (int) temp.charAt(n) < 123)) {
					value += temp.charAt(n);
				} else {
					if(temp.charAt(n) == '[') {
						type += temp.charAt(n+1);
						type += temp.charAt(n);
					} else if (temp.charAt(n) != ']') {
						type += temp.charAt(n);
					}
				}
			}
			sbb.append(type).reverse();
			
			sb.append(head).append(sbb).append(" ").append(value).append(";").append("\n");
		}
		System.out.println(sb);
		
	}
}
