package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		System.out.println("<항목 추가>");
		
		System.out.print("추가할 항목의 카테고리를 입력하세요: ");
		category = sc.next();
		
		System.out.print("추가할 항목의 제목을 입력하세요: ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("중복되는 제목입니다!");
			return;
		}
		
		System.out.print("추가할 항목의 내용을 입력하세요: ");
		sc.nextLine();
		desc = sc.nextLine();
		
		System.out.print("추가할 항목의 마감일자을 입력하세요: ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(category,title,desc,due_date);
		list.addItem(t);
		System.out.println("항목이 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<항목 삭제>");
		
		System.out.print("삭제할 항목의 일련번호를 입력하세요: ");
		
		int num = sc.nextInt();
		
		if (num<=0 || num > l.getSize()) {
			System.out.printf("목록의 범위를 초과했습니다!");
			return;
		}
		
		l.deleteItem(num-1);
		
		System.out.println("항목이 삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<항목 수정>");
		
		System.out.print("수정할 항목의 일련번호를 입력하세요: ");
		
		int num = sc.nextInt();
		
		if (num<=0 || num > l.getSize()) {
			System.out.printf("목록의 범위를 초과했습니다!");
			return;
		}
		
		l.deleteItem(num-1);
		System.out.print("항목의 새로운 카테고리를 입력하세요: ");
		String new_category = sc.next().trim();
		
		System.out.print("항목의 새로운 제목을 입력하세요: ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다!");
			return;
		}
		
		System.out.print("항목의 새로운 내용을 입력하세요: ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		System.out.print("항목의 새로운 마감일자를 입력하세요: ");
		
		String new_duedate = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_duedate);
		l.addItem(t);
		System.out.println("함목이 수정되었습니다.");
		
		

	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록, 총 " + l.getSize() + "개>" );
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
			System.out.println(count + "개의 항목을 읽었습니다.");
			in.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("파일을 찾지 못했습니다");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
