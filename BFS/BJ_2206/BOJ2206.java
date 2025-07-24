import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
			
		char arr[][] = new char[N][M];
		boolean visited[][] = new boolean[N][M];
		for(int i = 0; i < N; i++)
		{
			String str = br.readLine();
			for(int j = 0; j < M; j++)
			{
				arr[i][j] = str.charAt(j);
			}
		}

		int ans = BFS(arr, visited, N, M);
		System.out.println(ans);
	}
	
	static int BFS(char arr[][], boolean visited[][], int N, int M)
	{
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, 1, 0});
		
		int mincnt = Integer.MAX_VALUE;
		while(!q.isEmpty())
		{
			int info[] = q.poll();
			int x = info[0];
			int y = info[1];
			int cnt = info[2];
			int wall = info[3];
			
			if(x == N - 1 && y == M - 1)
			{
				mincnt = Math.min(mincnt, cnt);
				return mincnt;
			}
			
			for(int dir = 0; dir < 4; dir++)
			{
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny])
				{
					if(arr[nx][ny] == '0')
					{
						q.add(new int[] {nx,ny,cnt+1, wall});
						visited[nx][ny] = true;
					}
					else if(arr[nx][ny] == '1' && wall == 0)
					{
						q.add(new int[] {nx,ny,cnt+1, 1});
						visited[nx][ny] = true;
					}
				}
			}
		}
		return -1;
	}
}
