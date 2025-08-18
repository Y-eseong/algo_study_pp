import java.io.*;
import java.util.*;
   
public class Main {
     
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int k = Integer.parseInt(in[1]);
        int[] coin = new int[n];
        
        for(int i=n-1; i>=0; i--) {
        	coin[i] = Integer.parseInt(br.readLine());
        }
        
        int count = 0;
        int i = 0;
        while(k != 0) {
        	count += k / coin[i];
        	k %= coin[i];
        	i++;
        }
        sb.append(count);
        System.out.println(sb);
    }
}