	package com.ssafy.project.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3568_sgb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		StringTokenizer st = new StringTokenizer(s);
		String type = st.nextToken();
		String[] varList = new String[st.countTokens()];
		
		for(int i=0; i<varList.length; i++)
		{
			StringBuilder curStr = new StringBuilder();
			StringBuilder done = new StringBuilder();
			int index;
			boolean find = true;
			
			curStr.append(st.nextToken());
			done.append(type);
			
			if((index = curStr.indexOf(",")) != -1)
			{
				curStr.delete(index, index + 1);
			}
			
			if((index = curStr.indexOf(";")) != -1)
			{
				curStr.delete(index, index + 1);
			}

			 while(find)
			{
				find = false;
				if(curStr.length()!=1)
				{
					if(curStr.charAt(curStr.length()-1) == '&')
					{
						done.append("&");
						curStr.delete(curStr.length()-1, curStr.length());
						find = true;
					}
					
					if(curStr.charAt(curStr.length()-1) == ']')
					{
						done.append("[]");
						curStr.delete(curStr.length()-2, curStr.length());
						find = true;
					}
					
					if(curStr.charAt(curStr.length()-1) == '*')
					{
						done.append("*");
						curStr.delete(curStr.length()-1, curStr.length());
						find = true;
					}
				}
				else break;
			}
			
			done.append(" ").append(curStr.toString()).append(";");
			
			varList[i] = done.toString();
		}
		
		for(String sb : varList)
		{
			System.out.println(sb);
		}
	}
}
