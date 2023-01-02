package bookMap.method.run;

import bookMap.method.model.service.BookMapService;

public class BookRun {
	
	public static void main(String[] args) {
		BookMapService bms = new BookMapService();
		bms.display();
	}

}
