package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9205 {
	public static class Convinience {
		short x;
		short y;
		
		public Convinience(short x, short y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Sangkeun {
		short x;
		short y;
		short beerCnt;
		boolean state = true;
		
		public Sangkeun (short homeX, short homeY, short beerCnt) {
			this.x = homeX;
			this.y = homeY;
			this.beerCnt = beerCnt;
		}
		
		public void buyBeer(boolean noConvi) {
			if (noConvi) return;
			if (beerCnt < 0) beerCnt = 0;
			else if (beerCnt >= 0 && beerCnt < 20) beerCnt = 20; 
		}
	}
	
	
	
	public static class RockFestival {
		short x;
		short y;
		
		public RockFestival (short homeX, short homeY) {
			this.x = homeX;
			this.y = homeY;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			short x1 = Short.parseShort(st.nextToken());
			short y1 = Short.parseShort(st.nextToken());
			short beer = 20;
			Sangkeun home = new Sangkeun(x1, y1, beer);
			
			short[] conbinisDist = new short[N + 1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				short x3 = Short.parseShort(st.nextToken());
				short y3 = Short.parseShort(st.nextToken());
				
				conbinisDist[i] = (short) (Math.abs(home.x - x3) + Math.abs(home.y - y3));
			}
			
			Arrays.sort(conbinisDist);
			
			st = new StringTokenizer(br.readLine());
			short x2 = Short.parseShort(st.nextToken());
			short y2 = Short.parseShort(st.nextToken());
			RockFestival rock = new RockFestival(x2, y2);
			
			short maxDist = (short) (Math.abs(home.x - rock.x) + Math.abs(home.y - rock.y));
			if (maxDist <= 1000) {
				sb.append("happy").append("\n");
				continue;
			}
			int convinisIdx = 0;
			boolean noConvi = false;
			// 거리의 관점으로 치환
			for (int dist = 50; dist <= maxDist; dist += 50) {
			    home.beerCnt--;
			    // dist보다 가까운 모든 편의점 처리
			    if (dist >= conbinisDist[convinisIdx]) {
			        home.buyBeer(noConvi);
			        if (convinisIdx < N) {
			        	convinisIdx++;
		        	} else {
		        		noConvi = true;
		        	}
			    }
//			    System.out.println(dist + ") " + (home.beerCnt));

			    if (home.beerCnt < 0) {
			        home.state = false;
			        break;
			    }
			}

			if (home.state) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");				
			}
		}
		System.out.print(sb);
	}

}
