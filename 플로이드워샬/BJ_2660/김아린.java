import java.io.*;
import java.util.*;

public class 회장뽑기 {

	static int N, INF = 1_000_000;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dist[i][j] = INF;
			}
			dist[i][i] = 0;
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (from == -1 && to == -1)
				break;

			dist[from][to] = dist[to][from] = 1;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		int[] sum = new int[N + 1];
		int min = INF;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				sum[i] = Math.max(sum[i], dist[i][j]);
			}

			min = Math.min(min, sum[i]);
		}

		int cnt = 0;
		sb.append(min).append(" ");
		
		StringBuilder body = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			if(sum[i] == min) {
				cnt++;
				body.append(i).append(" ");
			}
		}
		
		sb.append(cnt).append("\n");
		sb.append(body);

		System.out.println(sb);

	}

}
