package com.todo.menu;

public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. <�׸� �߰�> ( add )");
        System.out.println("2. <�׸� ����> ( del )");
        System.out.println("3. <�׸� ����> ( edit )");
        System.out.println("4. <��ü ���> ( ls )");
        System.out.println("5. <���� ����> ( ls_name_asc )");
        System.out.println("6. <���� ���� ����> ( ls_name_desc )");
        System.out.println("7. <��¥�� ����> ( ls_date )");
        System.out.println("8. <��¥�� ����> ( ls_date_desc )");
        System.out.println("9. <���� �˻�> ( find_title )");
        System.out.println("10. <���� �˻�> ( find_desc )");
        System.out.println("11. <ī�װ� �˻�> ( find_cate )");
        System.out.println("12. <ī�װ� ���> ( ls_cate )");
        System.out.println("13. <���α׷� ����> (exit)");
        
    }
    
    public static void prompt() {
    	displaymenu();
    }
}
