import java.io.*;
import java.util.*;
   
public class Main {
     
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int n = Integer.parseInt(br.readLine());
        int[] w = new int[n];
        for(int i = 0; i<n; i++) {
        	w[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(w);
        int max = w[n-1];
        int count = 2;
        for(int i=n-2; i>=0; i--) {
        	max = Math.max(max, w[i]*count++);
        }
        sb.append(max);
        System.out.println(sb);
    }
}