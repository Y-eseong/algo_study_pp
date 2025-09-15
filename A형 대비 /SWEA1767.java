import java.io.*;
import java.util.*;

public class SWEA1767 
{
	static int maxConnect;
	static ArrayList<ans> ansCore;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	
	static public class core
	{
		int x;
		int y;

		public core(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static public class ans
	{
		int x;
		int y;

		public ans(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean canConnect(int arr[][], int N, int x, int y, int dir)
	{
		int nx = x;
		int ny = y;
		
		while(true)
		{
			nx += dx[dir];
			ny += dy[dir];
			
			// 끝에 도달하면, 연결 가능
			if(nx < 0 || ny < 0 || nx >= N || ny >= N)
			{
				return true;
			}
			
			// 코어로 막혀있거나, 경로(0)가 없다면 연결 불가능
			if(arr[nx][ny] == 1 || arr[nx][ny] != 0)
			{
				return false;
			}
		}
	}
	
	static int setConnect(int arr[][], int N, int x, int y, int dir, int visited)
	{
		int nx = x;
		int ny = y;
		
		int dist = 0;
		while(true)
		{
			nx += dx[dir];
			ny += dy[dir];
			
			// 끝에 도달하면, 연결완료
			if(nx < 0 || ny < 0 || nx >= N || ny >= N)
			{
				return dist;
			}
			
			// 방문여부를 boolean 대신에 0과 1이 아닌, 다른 값으로 체크.
			arr[nx][ny] = visited;
			dist++;
		}
	}
	
	static void process(int arr[][], ArrayList<core> cores, int N, int coreIdx, int coreConnect, int dist)
	{
		if(coreIdx == cores.size())
		{
			if(coreConnect >= maxConnect)
			{
				maxConnect = coreConnect;
				ansCore.add(new ans(coreConnect, dist));
			}
			return;
		}
		
		int x = cores.get(coreIdx).x;
		int y = cores.get(coreIdx).y;
		
		// 선택하지 않은 경우.
		process(arr, cores, N, coreIdx + 1, coreConnect, dist);
		
		for(int dir = 0; dir < 4; dir++)
		{
			//연결 가능한 경우
			if(canConnect(arr, N, x, y, dir))
			{
				// 코어연결, 연결 경로를 2로 저장.
				int connectDist= setConnect(arr, N, x, y, dir, 2);
				
				// 연결된 상태를 기반으로 호출.
				process(arr, cores, N, coreIdx + 1, coreConnect + 1, dist + connectDist);
				
				// 상태 복원, != 0인 조건을 위해 연결경로를 다시 0으로 복원(초기화)
				setConnect(arr, N, x, y, dir, 0);
			}
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++)
		{
			int N = Integer.parseInt(br.readLine());
			
			int arr[][] = new int [N][N];
			ArrayList<core> cores = new ArrayList<>();
			ansCore = new ArrayList<>();
			maxConnect = 0;
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
				{
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1)
					{
						if(i != 0 && i != N - 1 && j != 0 && j != N - 1)
						{
							cores.add(new core(i, j));
						}						
					}
				}
			}
			
			process(arr, cores, N, 0, 0, 0);
			
			// 코어개수는 많게, 길이는 짧게.
			// (연결코어개수, 길이) = (내림차순, 오름차순)
			ansCore.sort((a, b) ->
			{
				// 연결된 코어 개수가 같은 경우
				if(a.x == b.x)
				{
					// 길이가 짧은(오름차순) 순서로 정렬
					return Integer.compare(a.y, b.y);
				}
				// 내림차순
				return Integer.compare(b.x, a.x);
			});
			sb.append("#").append(tc).append(" ").append(ansCore.get(0).y).append("\n");
		}
		System.out.println(sb);
	}
}

