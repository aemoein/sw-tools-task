package calculations;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
//import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Entity
@Table(name = "calculation")
class Calculation {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		@Column(name = "number1")
		private int number1;

		@Column(name = "number2")
		private int number2;

		@Column(name = "operation")
		private String operation;

		public Calculation(int number1, int number2, String operation) {
		    this.number1 = number1;
		    this.number2 = number2;
		    this.operation = operation;
		}

		public Calculation() {
		}

		public long getId() {
		    return id;
		}

		public void setId(long id) {
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
