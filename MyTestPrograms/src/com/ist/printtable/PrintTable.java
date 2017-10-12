package com.ist.printtable;

import java.util.Scanner;

public class PrintTable {

	public static void main(String[] args) {
		System.out.println("Welcome to table generation program");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the table of which number?");
		int tableofwhichnumber = scanner.nextInt();
		System.out.println("Enter the till which multiple?");
		int myInt = scanner.nextInt();
		scanner.close();
		
		System.out.println("Your table of "+tableofwhichnumber +" till multiple "+myInt+" is - \n"+multiple(tableofwhichnumber,myInt));

	}

	private static String multiple(int tableofwhichnumber, int myInt) {
		StringBuffer sb=new StringBuffer();
		for(int i=1;i<=myInt;i++){
			sb.append((i*tableofwhichnumber)+"\n");
		}
		return sb.toString();
	}

}
