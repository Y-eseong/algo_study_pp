import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in = br.readLine().split(" ");
		
		for(int i=1; i<in.length; i++) {
			StringBuilder name = new StringBuilder();
			if(in[i].charAt(0)=='*' || in[i].charAt(0)=='[' || in[i].charAt(0)=='&') {
				sb.append(in[0]);
				for(int j=0; j<in[i].length()-1; j++) {
					if(in[i].charAt(j)!='*' && in[i].charAt(j)!='[' && in[i].charAt(j) != ']' && in[i].charAt(j)!='&') {
						name.append(in[i].charAt(j));
					}
					else sb.append(in[i].charAt(j));
				}
				sb.append(" ").append(name).append(";\n");
			}
			else {		
				sb.append(in[0]);
				for(int j=0; j<in[i].length()-1; j++) {
					if(in[i].charAt(j) == '*' || in[i].charAt(j) == '[' || in[i].charAt(j) == '&'){						
						for(int k=in[i].length()-2; k>=j; k--) {							
							if(in[i].charAt(k) == ']') {
								sb.append("[]");
								k--;
							}
							else sb.append(in[i].charAt(k));
						}
						break;
					}
					name.append(in[i].charAt(j));
				}
				sb.append(" ").append(name).append(";\n");
			}
		}
		System.out.println(sb);
	}
}