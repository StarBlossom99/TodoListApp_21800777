package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		System.out.println("<�׸� �߰�>");
		
		System.out.print("�߰��� �׸��� ī�װ��� �Է��ϼ���: ");
		category = sc.next();
		
		System.out.print("�߰��� �׸��� ������ �Է��ϼ���: ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ��Ǵ� �����Դϴ�!");
			return;
		}
		
		System.out.print("�߰��� �׸��� ������ �Է��ϼ���: ");
		sc.nextLine();
		desc = sc.nextLine();
		
		System.out.print("�߰��� �׸��� ���������� �Է��ϼ���: ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(category,title,desc,due_date);
		list.addItem(t);
		System.out.println("�׸��� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<�׸� ����>");
		
		System.out.print("������ �׸��� �Ϸù�ȣ�� �Է��ϼ���: ");
		
		int num = sc.nextInt();
		
		if (num<=0 || num > l.getSize()) {
			System.out.printf("����� ������ �ʰ��߽��ϴ�!");
			return;
		}
		
		l.deleteItem(num-1);
		
		System.out.println("�׸��� �����Ǿ����ϴ�.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<�׸� ����>");
		
		System.out.print("������ �׸��� �Ϸù�ȣ�� �Է��ϼ���: ");
		
		int num = sc.nextInt();
		
		if (num<=0 || num > l.getSize()) {
			System.out.printf("����� ������ �ʰ��߽��ϴ�!");
			return;
		}
		
		l.deleteItem(num-1);
		System.out.print("�׸��� ���ο� ī�װ��� �Է��ϼ���: ");
		String new_category = sc.next().trim();
		
		System.out.print("�׸��� ���ο� ������ �Է��ϼ���: ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��� �� �����ϴ�!");
			return;
		}
		
		System.out.print("�׸��� ���ο� ������ �Է��ϼ���: ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		System.out.print("�׸��� ���ο� �������ڸ� �Է��ϼ���: ");
		
		String new_duedate = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_duedate);
		l.addItem(t);
		System.out.println("�Ը��� �����Ǿ����ϴ�.");
		
		

	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ���, �� " + l.getSize() + "��>" );
		for (TodoItem item : l.getList()) {
			System.out.println(l.indexOf(item)+1 +  ". " + "[" + item.getCategory() + "] " + item.getTitle() + 
					" : " + item.getDesc() + " - " + item.getDue_date() + " <" + item.getCurrent_date() + "> ");
		}
	}
	
	public static void saveList (TodoList l, String filename) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));

		for(TodoItem item : l.getList()) {
			out.write(item.toSaveString());
		}
		
		out.close();
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			int count = 0;
			String str = null;
			
			while((str = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(str,"##");
				TodoItem item = new TodoItem(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
				item.setCurrent_date(st.nextToken());
				l.addItem(item);
				count ++;
			}
			System.out.println(count + "���� �׸��� �о����ϴ�.");
			in.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("������ ã�� ���߽��ϴ�");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
