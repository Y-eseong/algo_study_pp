package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1260_sgb {
	static StringBuilder sb = new StringBuilder();
	static Node[] graph;
	static boolean[] Visited;
	
	public static class Node
	{
		public int num;
		public int[] neighbor = new int[0];
		
		public Node(int n)
		{
			this.num = n;
		}
		
		public void Add(Node newNode)
		{
			for(int i=0; i<neighbor.length; i++)
			{
				if(neighbor[i] == newNode.num-1) return;
			}
			
			int[] newArray = Arrays.copyOf(neighbor, neighbor.length + 1);
			newArray[newArray.length-1] = newNode.num-1;
			
			neighbor = newArray;
			Arrays.sort(neighbor);
		}
	}
	
	public static void dfs(Node node)
	{
		int num = node.num;
		Visited[num-1] = true;
		
		sb.append(num + " ");
		
		for(int next : node.neighbor)
		{
			if(!Visited[next])
				dfs(graph[next]);
		}
	}
	
	public static void bfs(Node node)
	{ 
		ArrayDeque<Node> bfsList = new ArrayDeque<Node>();
		int num = node.num;
		Visited[num-1] = true;
		bfsList.offer(node);
		
		while(!bfsList.isEmpty())
		{
			Node searchNode = bfsList.poll();
			num = searchNode.num;
			sb.append(num + " ");
			
			for(int next : searchNode.neighbor)
			{
				if(!Visited[next]) 
				{
					Visited[next] = true;
					bfsList.offer(graph[next]);
				}
			}
		}
		
	}	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, M, V;
		String s;
		
		s = in.readLine();
		st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken()) - 1;
		
		graph = new Node[N];
		for(int i=0; i<N; i++)
		{
			graph[i] = new Node(i+1);
		}
		
		for(int i=0; i<M; i++)
		{
			s = in.readLine();
			st = new StringTokenizer(s, " ");
			int firstNode = Integer.parseInt(st.nextToken())-1;
			int secondNode = Integer.parseInt(st.nextToken())-1;
			graph[firstNode].Add(graph[secondNode]);
			graph[secondNode].Add(graph[firstNode]);
		}
		
		Visited = new boolean[N];
		dfs(graph[V]);
		sb.append("\n");
		
		Visited = new boolean[N];
		bfs(graph[V]);
		
		System.out.println(sb);
	}
}
