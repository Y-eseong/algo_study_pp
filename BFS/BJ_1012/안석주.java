import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] x = {0,1,0,-1}; // 상우하좌
		int[] y = {-1,0,1,0}; // 상 우 하 좌
		for(int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 가로 길이
			int N = Integer.parseInt(st.nextToken()); // 세로길이
			int K = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			Stack<Integer> stackX = new Stack<>(); 
			Stack<Integer> stackY = new Stack<>(); 
			int count = 0;
			for(int i = 0; i < K; i++) { // 맵 초기화
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int x2 = Integer.parseInt(st2.nextToken());
				int y2 = Integer.parseInt(st2.nextToken());
				map[y2][x2] = 1;
			}
			
			for(int i = 0; i < N ; i++ ) {
				for(int j = 0; j < M ; j++) {
					if(map[i][j] == 1) {
						map[i][j] = -1;
						stackX.push(j);
						stackY.push(i);
						
						while(!stackX.empty()) {
							int imsiX = stackX.pop();
							int imsiY = stackY.pop();
							map[imsiY][imsiX] = -1;
							
							for(int a = 0; a < 4; a++) { // 4방으로 탐색 하면서 1 찾기
								int X = imsiX + x[a];
								int Y = imsiY + y[a];
								if(X >= 0 && X < M && Y >= 0 && Y < N ) {
									if(map[Y][X] == 1){
										stackX.push(X);
										stackY.push(Y);
									}
								}
								
							}
							
						}
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
		
		
	}
	
	

}
