package assign1;

import javax.persistence.*;

@Entity
@Table(name = "calculations")
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "number1")
    private int number1;
    
    @Column(name = "number2")
    private int number2;
    
    @Column(name = "operation")
    private String operation;
    
    public Calculation() {
    }
    
    public Calculation(int number1, int number2, String operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getNumber1() {
        return number1;
    }
    
    public void setNumber1(int number1) {
        this.number1 = number1;
    }
    
    public int getNumber2() {
        return number2;
    }
    
    public void setNumber2(int number2) {
        this.number2 = number2;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
}