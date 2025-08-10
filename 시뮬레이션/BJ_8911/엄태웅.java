import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_8911 {
	
	//상우하좌
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		for (int v = 0; v < t; v++) {
			
			//x와 y좌표의 최대값. 각각 플러스와 마이너스의 좌표
			int px = 0;
			int py = 0;
			int mx = 0;
			int my = 0;
			
			//현재 위치
			int x = 0;
			int y = 0;
			
			//시작하는 위치. dx dy의 위치로, 기본값은 위를 향하고 있기에 시작은 0. 그리고 뒤로 이동할 경우에는 m에 2를 더한 다음 이동하면 된다. 다만 직접 더하면 안된다!!!
			int m = 0;
			
			//움직임을 입력받을 배열을 만들고
			String[] arr = in.readLine().split("");
			
			for (int i = 0; i < arr.length; i++){
				//System.out.println(x + " " + y);
				if (arr[i].equals("F")) {
					x += dx[m];
					y += dy[m];
				}
				else if (arr[i].equals("B")) {
					int k = (m+2)%4;
					x += dx[k];
					y += dy[k];
				}
				else if (arr[i].equals("R")) {
					m = (m+1)%4;
				}
				else if (arr[i].equals("L")) {
					m = (m+3)%4;
				}
				
				//이제 최대값인지 확인
				if (x > 0) {
					px = Math.max(px, x);
				} else {
					mx = Math.max(mx, Math.abs(x));
				}
				
				if (y > 0) {
					py = Math.max(py, y);
				} else {
					my = Math.max(my, Math.abs(y));
				}
			}
			
			System.out.println((px+mx)*(py+my));
		}
		
	}
}
