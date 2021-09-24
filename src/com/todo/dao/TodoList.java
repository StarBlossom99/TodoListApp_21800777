package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByDate_reverse;
import com.todo.service.TodoSortByName;
import java.util.HashSet;
import java.util.Set;


public class TodoList {
	private List<TodoItem> list;
	private HashSet<String> ls_cate = new HashSet<String>();
	
	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(int num) {
		list.remove(list.get(num));
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		System.out.println("제목 순으로 정렬되었습니다.");
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "정렬된 목록입니다.");
		for (TodoItem myitem : list) {
			System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " : " + myitem.getDesc() + " - " + myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
		}
	}
	
	public void listAll_title(String keyword) {
		int count_key = 0;
		for (TodoItem myitem : list) {
			if(myitem.getTitle().contains(keyword)) {
				System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " : " + myitem.getDesc() + " - " + myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
				count_key ++;
			}
		}
		
		System.out.println("총 " + count_key + "개의 항목을 찾았습니다.");
	}
	
	public void listAll_desc(String keyword) {
		int count_key = 0;
		for (TodoItem myitem : list) {
			if(myitem.getDesc().contains(keyword)) {
				System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " : " + myitem.getDesc() + " - " + myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
				count_key ++;
			}
		}
		
		System.out.println("총 " + count_key + "개의 항목을 찾았습니다.");
	}
	
	public void listAll_category(String keyword) {
		int count_key = 0;
		for (TodoItem myitem : list) {
			if(myitem.getCategory().contains(keyword)) {
				System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " : " + myitem.getDesc() + " - " + myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
				count_key ++;
			}
		}
		System.out.println("총 " + count_key + "개의 항목을 찾았습니다.");
	}
	
	public void reverseList() {
		System.out.println("제목 역순으로 정렬되었습니다.");
		Collections.reverse(list);
	}

	public void sortByDate() {
		System.out.println("날짜 순으로 정렬되었습니다.");
		Collections.sort(list, new TodoSortByDate());
	}
	
	public void sortByDate_reverse() {
		System.out.println("날짜 역순으로 정렬되었습니다.");
		Collections.sort(list, new TodoSortByDate_reverse());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}

	public int getSize() {
		return list.size();
	}
	
	public void search_cate() {
		for(TodoItem item : list) {
			ls_cate.add(item.getCategory());
		}
	}
	
	public void show_ls_cate() {
		search_cate();
    	Iterator<String> iterset = ls_cate.iterator();
    	
    	while(iterset.hasNext()) {
    		System.out.print("<" + iterset.next() + ">");
    	}
    	
    	System.out.println("\n총 " + ls_cate.size()+ "개의 카테고리가 등록되어 있습니다.");
    }
}