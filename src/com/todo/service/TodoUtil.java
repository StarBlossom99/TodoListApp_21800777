package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		System.out.println("<항목 추가>");
		
		System.out.print("추가할 항목의 제목을 입력하세요: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("중복되는 제목입니다!");
			return;
		}
		
		System.out.print("추가할 항목의 내용을 입력하세요: ");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("항목이 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<항목 삭제>");
		
		System.out.print("삭제할 항목의 제목을 입력하세요: ");
		
		String title = sc.next();
		
		if (!l.isDuplicate(title)) {
			System.out.printf("해당 항목이 없습니다!");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		
		System.out.println("항목이 삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<항목 수정>");
		System.out.print("수정할 항목의 제목을 입력하세요: ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("해당 항목이 없습니다!");
			return;
		}

		System.out.print("항목의 새로운 제목을 입력하세요: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다!");
			return;
		}
		
		System.out.println("항목의 새로운 내용을 입력하세요: ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("함목이 수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록>");
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
			System.out.println("파일을 찾지 못했습니다");
		}
		int count = 0;
		String str = null;
		
		while((str = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str,"##");
			count ++;
			l.addItem(new TodoItem(st.nextToken(),st.nextToken()));
		}
		System.out.println(count + "개의 항목을 읽었습니다.");
		in.close();
	}
}
