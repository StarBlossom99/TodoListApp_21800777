package com.todo.main;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.*;



public class TodoMain  {
	   
	   public static void start() throws IOException {
	   
	      Scanner sc = new Scanner(System.in);
	      TodoList l = new TodoList();
	      boolean isList = false;
	      boolean quit = false;
	      Menu.displaymenu();
	      TodoUtil.loadList(l, "todolist.txt");
	      do {
	    	 System.out.print("실행할 명령을 입력하세요 >");
	         isList = false;
	         String choice = sc.next();
	         switch (choice) {

	         case "add":
	            TodoUtil.createItem(l);
	            break;
	         
	         case "del":
	            TodoUtil.deleteItem(l);
	            break;
	            
	         case "edit":
	            TodoUtil.updateItem(l);
	            break;
	            
	         case "ls":
	            TodoUtil.listAll(l);
	            break;

	         case "ls_name_asc":
	            l.sortByName();
	            isList = true;
	            break;

	         case "ls_name_desc":
	            l.sortByName();
	            l.reverseList();
	            isList = true;
	            break;
	            
	         case "ls_date":
	            l.sortByDate();
	            isList = true;
	            break;
	            
	         case "help":
	        	Menu.prompt();
	        	break;
	        	
	         case "exit":
	            quit = true;
	            break;

	         default:
	            System.out.println("해당 명령은 유효하지 않습니다 - 도움말(help)");
	            break;
	         }
	         
	         if(isList) l.listAll();
	      } while (!quit);
	      TodoUtil.saveList(l, "todolist.txt");
	   }
	}