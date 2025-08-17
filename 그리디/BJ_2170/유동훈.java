import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * 
 * @link: https://www.acmicpc.net/problem/2170
 * @note: 주석 처리 해놓은 부분이 직접 작성한 코드인데, 일단 로직은 똑같지만 시간 초과가 나는 부분을 챗지피티로 개선시킴
 * lines[i][0, 1] 이걸 생각 못해서 String으로 처리했는데 일단 거기서 문제 발생 1
 * 또 if문이 너무 남발되어서도 문제 발생 2
 */

public class Main {
	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int N = Integer.parseInt(br.readLine());
//		
//		String[] arr = new String[N];
//
//		long slice = 0;
//		
//		for (int i = 0; i < N; i++) {
//			arr[i] = br.readLine();
//		}
//		
//		Arrays.sort(arr, (s1, s2) -> {
//		    String[] p1 = s1.split(" ");
//		    String[] p2 = s2.split(" ");
//		    int x1 = Integer.parseInt(p1[0]);
//		    int y1 = Integer.parseInt(p1[1]);
//		    int x2 = Integer.parseInt(p2[0]);
//		    int y2 = Integer.parseInt(p2[1]);
//
//		    if (x1 != x2) return Integer.compare(x1, x2);
//		    return Integer.compare(y1, y2);
//		});
//		
//		StringTokenizer st = new StringTokenizer(arr[0]);
//		
//		int startX = Integer.parseInt(st.nextToken());
//		int startY = Integer.parseInt(st.nextToken());
//		
//		
//		for (int i = 1; i < N; i++) {
//			st = new StringTokenizer(arr[i]);
//			
//			int x = Integer.parseInt(st.nextToken());
//			int y = Integer.parseInt(st.nextToken());
//
//			// x 좌표 y 좌표 순으로 정렬되어 있음.
//			// 선이 끊기는 순간, x가 startY보다 작거나 같은 경우와 큰 경우 두 가지가 생김
//			// 전자는 startY를 갱신, 후자는 지금까지 길이를 더해주고 startX,Y를 갱신
//			if (x >= 0 && x >= startX) {
//				if (x <= startY) {
//					if (y <= startY) {
//						continue;
//					} else if (y > startY) {
//						startY = y;
//					}
//				} else { // x > startY
//					slice += startY - startX;
//					startX = x;
//					startY = y;
//				}
//			}
//		}
//		
//		slice += startY - startX;
//
//		System.out.println(slice);
//	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        long slice = 0;
        int start = lines[0][0];
        int end = lines[0][1];

        for (int i = 1; i < N; i++) {
            int x = lines[i][0];
            int y = lines[i][1];
            if (x <= end) { // 겹침
                end = Math.max(end, y);
            } else { // 안 겹침
                slice += end - start;
                start = x;
                end = y;
            }
        }
        slice += end - start;
        System.out.println(slice);
    }
		
}
