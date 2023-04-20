package calculations;

import javax.ejb.Stateful;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Stateful
@Path("/calc")
public class CalcPost {
	@PersistenceContext(unitName = "calcPU")
	private EntityManager entityManager;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCalculation(Calculation calculation) {
	    try {
	        int result;
	        switch (calculation.getOperation()) {
	            case "+":
	                result = calculation.getNumber1() + calculation.getNumber2();
	                break;
	            case "-":
	                result = calculation.getNumber1() - calculation.getNumber2();
	                break;
	            case "*":
	                result = calculation.getNumber1() * calculation.getNumber2();
	                break;
	            case "/":
	                result = calculation.getNumber1() / calculation.getNumber2();
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid operation: " + calculation.getOperation());
	        }

	        Calculation newCalculation = new Calculation(calculation.getNumber1(), calculation.getNumber2(), calculation.getOperation());
	        entityManager.persist(newCalculation);

	        return Response.ok().entity("{\"Result\":" + result + "}").build();
	    } catch (Exception e) {
	        return Response.serverError().build();
	    }
	}

}
