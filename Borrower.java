public class Borrower {
    private String name;
    private String birthDate;
    private Loan currentLoan;
    
    // 생성자
    public Borrower(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
    
    // getter
    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }
    
    public boolean isOnLoan() { // 대출 상태 확인
        return currentLoan != null;
    }

    public boolean isAvailable() { // 대출 가능한 상태 확인
        
    }


    // setter
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCurrentLoan(Loan loan) {
        this.currentLoan = loan;
    }
}
