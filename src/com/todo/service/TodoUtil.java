package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		System.out.println("<�׸� �߰�>");
		
		System.out.print("�߰��� �׸��� ������ �Է��ϼ���: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ��Ǵ� �����Դϴ�!");
			return;
		}
		
		System.out.print("�߰��� �׸��� ������ �Է��ϼ���: ");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�׸��� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<�׸� ����>");
		
		System.out.print("������ �׸��� ������ �Է��ϼ���: ");
		
		String title = sc.next();
		
		if (!l.isDuplicate(title)) {
			System.out.printf("�ش� �׸��� �����ϴ�!");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		
		System.out.println("�׸��� �����Ǿ����ϴ�.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<�׸� ����>");
		System.out.print("������ �׸��� ������ �Է��ϼ���: ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�ش� �׸��� �����ϴ�!");
			return;
		}

		System.out.print("�׸��� ���ο� ������ �Է��ϼ���: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��� �� �����ϴ�!");
			return;
		}
		
		System.out.println("�׸��� ���ο� ������ �Է��ϼ���: ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�Ը��� �����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ���>");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "]" +  " : " + item.getDesc() + " <" + item.getCurrent_date() + "> ");
		}
	}
	
	public static void saveList (TodoList l, String filename) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));

		for(TodoItem item : l.getList()) {
			out.write(item.toSaveString());
		}
		
		out.close();
	}
	
	public static void loadList(TodoList l, String filename) throws IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filename));	
		}
		catch(FileNotFoundException e) {
			System.out.println("������ ã�� ���߽��ϴ�");
		}
		int count = 0;
		String str = null;
		
		while((str = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str,"##");
			count ++;
			l.addItem(new TodoItem(st.nextToken(),st.nextToken()));
		}
		System.out.println(count + "���� �׸��� �о����ϴ�.");
		in.close();
	}
}
