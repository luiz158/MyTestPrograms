package com.p.string;

import java.util.StringTokenizer;

public class Test {
	
	public static void main(String[] args) {
		String data="2617453,1,604206,82638451970289,44139,75,1950,1,1000000011731383,25,5151,1,590123,08756,,2,6001,1260962707,1,,111111111111100,P,08756,NIFTY, ,OPTIDX,1261180800,1220000,CE,,,,,,,,";
		
//		Utility.splitData(data);
		Utility.splitDataWithStringTokenizer(data);
	}

}

class Utility{
	static void splitData(String data) {
		String[] t = data.split(",");
		
		System.out.println("t.length() : "+((t!=null)?t.length:0));
		int i=0;
		for(String s:t) {
			System.out.println("t["+i+"] : "+t[i]);
			i++;
		}
	}
	
	static void splitDataWithStringTokenizer(String data) {
		StringTokenizer stt = null;
		stt = new StringTokenizer(data, ",");
		
		System.out.println("t.length() : "+stt.countTokens());
		
		int i = 0;
		while (stt.hasMoreTokens()) {
			System.out.println("t["+i+"] : "+stt.nextToken());
			i++;
		}
	}
}