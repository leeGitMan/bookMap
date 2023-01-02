package bookMap.method.model.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookMapService {
	
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> favorites = new ArrayList<Map<String, Object>>();
	static int favNum = 0;
	Scanner sc = new Scanner(System.in);
	
	
	
	public void display() {

		int menuNum = 0;
		
			do {
			System.out.println("\n===== 메뉴 선택 =====");
			System.out.println("1. 도서 등록");
			System.out.println("2. 도서 조회");
			System.out.println("3. 도서 수정");
			System.out.println("4. 도서 삭제");
			System.out.println("5. 즐겨찾기 등록");
			System.out.println("6. 즐겨찾기 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("번호 입력 : ");
			
			try {
			menuNum = sc.nextInt();
			sc.nextLine();
			}catch(InputMismatchException e) {
				
				System.out.println("유효하지 않은 입력");
				e.printStackTrace();
				menuNum = -1;
			}
		
			
			switch(menuNum) {
			case 1 : addBook(); break;
			case 2 : selectBook(); break;
			case 3 : updateBook(); break;
			case 4 : deleteBook(); break;
			case 5 : addFavorite(); break;
			case 6 : removeFavorite(); break;
			case 0 : System.out.println("프로그램 종료");break; 
			
			}
			
			}while(menuNum != 0);
			
	}
	
	
	public void addBook() {
		
		System.out.println("\n == 도서 등록 == ");
		
		System.out.print("제목 : ");
		String bookName = sc.next();
		System.out.print("작가 : ");
		String author = sc.next();
		System.out.print("가격 : ");
		String price = sc.next();
		System.out.print("출판사 : ");
		String publisher = sc.next();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("도서제목", bookName);
		map.put("작가", author);
		map.put("가격", price);
		map.put("출판사", publisher);
		
		list.add(map);
	}

	public void selectBook() {
		
		System.out.println("\n == 도서정보 조회 == ");
		for(Map<String, Object> map : list) {
			System.out.println(map);
			fileWriter();
		}
	}
	
	public void updateBook() {
		
		System.out.println("\n == 도서 정보 수정 == ");
		
		selectBook();
		System.out.println("수정할 도서 제목을 입력해주세요! ");

		System.out.print("제목 : ");
		String str = sc.next();
		
		
		for(int i = 0; i < list.size(); i++) {
			if(str.equals(list.get(i).get("도서제목"))) {
				System.out.println("변경전 목록");
				System.out.println(list.get(i));
				
				System.out.print("제목 : ");
				String bookName = sc.next();
				System.out.print("작가 : ");
				String author = sc.next();
				System.out.print("가격 : ");
				String price = sc.next();
				System.out.print("출판사 : ");
				String publisher = sc.next();
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("도서제목", bookName);
				map.put("작가", author);
				map.put("가격", price);
				map.put("출판사", publisher);
				
				Map<String, Object> temp = list.set(i, map);
				System.out.println(temp.get("도서제목")+ "의 목록이 변경되었습니다.");
			}
		}
	}
	
	
	public void deleteBook() {
		System.out.println(" == 도서 목록 삭제 == ");
		selectBook();
		
		System.out.println("삭제할 도서의 제목을 입력해주세요!");
		System.out.print("도서제목 : ");
		String title = sc.next();
		
		for(int i = 0; i < list.size(); i++) {
			if(title.equals(list.get(i).get("도서제목"))){
				Map<String, Object> re = list.remove(i);
				System.out.println(re.get("도서제목") + "을 삭제 합니다.");
			}
		}
	}
	

	public void addFavorite() {
		System.out.println("도서 목록 즐겨찾기 등록");
		selectBook();
		
		System.out.println("즐겨찾기를 원하는 도서의 제목을 입력해주세요!");
		System.out.print("도서제목 : ");
		String title = sc.next();
		
		for(Map<String, Object> result : list) {
			if(title.equals(result.get("도서제목"))) {
				
				Map<String, Object> fav = new HashMap<String,Object>();
				
				fav.put("num", favNum++);
				fav.put("도서제목", result.get("도서제목"));
				fav.put("작가", result.get("작가"));
				
				if(favorites.add(fav)) {
					System.out.println("성공");
					fileWriter();
				}
				
			}
		}
	}
	
	
	public void removeFavorite() {
		System.out.println("도서 목록 즐겨찾기 삭제");
		System.out.println(favorites);
		
		System.out.println("즐겨찾기에서 삭제할 도서의 제목을 입력해주세요!");
		System.out.print("도서제목 : ");
		String title = sc.next();
		
		for(int i = 0; i < favorites.size(); i++) {
			if(title.equals(favorites.get(i).get("도서제목"))) {
				
				Map<String, Object> temp = favorites.remove(i);
				System.out.println(temp.get("도서제목") + "의 즐겨찾기 내용이 삭제되었습니다.");
			}
			
		}
	}
	
	
	public void fileWriter() {
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("favorites.txt");
			
			for(Map<String, Object> input : favorites) {
				fw.write(input.toString());
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
}
