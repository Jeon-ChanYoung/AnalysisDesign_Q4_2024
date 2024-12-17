public class LibraryApplication {
    private Library library = new Library("Library");
       
    // 대출자 등록
    public boolean registerBorrower(String name, String birthDate) {
        Borrower br = new Borrower(name, birthDate);
        boolean isDuplicate = library.isDuplicateBorrower(br);

        if (!isDuplicate) {
            library.addBorrower(br);
        } 

        return !isDuplicate;
    }

    // 도서 등록
    public boolean registerBook(String title, String author, int uniqueNumber) {
        Book book = new Book(title, author, uniqueNumber);
        boolean isDuplicate = library.isDuplicateBook(book);
        
        if (!isDuplicate) {
            library.addBook(book);
        } 

        return !isDuplicate;
    }
    
    // 대출 가능한 도서 목록 표시
    public boolean displayLoanableBooks() {
        System.out.println("=== 대출 가능한 도서 목록 ===");
        boolean hasAvailableBooks = false;
        
        for (Book b : library.getBookCollection()) {
            if (b.isAvailable()) { 
                System.out.println(b.toString());
                hasAvailableBooks = true;
            }
        }

        return hasAvailableBooks;
    }

    // 대출중인 도서 목록 표시
    public boolean displayOnLoanBooks() {
        System.out.println("=== 대출중인 도서 목록 ===");
        boolean hasBorrowedBooks = false;

        for (Book book : library.getBookCollection()) {
            if (!book.isAvailable()) {
                System.out.println(book.toString());
                hasBorrowedBooks = true;
            }
        }

        return hasBorrowedBooks;
    }

    // 도서 대출
    public boolean borrowBook(int uniqueNumber, String name, String birthDate) {
        Book b = library.findBookByUniqueNumber(uniqueNumber);
        Borrower br = library.findBorrowerByNameAndBirthDate(name, birthDate);

        if (b != null && br != null && b.isAvailable() && br.isAvailable()) {
            Loan loan = new Loan(b, br); // 객체 생성

            b.setOnLoan(true);
            br.incrementBorrowedBooks();
            library.addLoan(loan);
            return true;
        }  else {
            return false;
        }
    }
    
    // 도서 반납
    public boolean returnBook(int uniqueNumber, String name, String birthDate) {
        Book b = library.findBookByUniqueNumber(uniqueNumber);
        Borrower br = library.findBorrowerByNameAndBirthDate(name, birthDate);
        Loan loan = library.findLoanByBookAndBorrower(b, br);

        if (loan != null) {
            loan.deleteLink();
            return true;
        } else {
            return false;
        }
    }
}
