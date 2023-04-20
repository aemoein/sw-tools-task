package assign1;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class CalcPost {
    @PersistenceContext
    EntityManager entityManager;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/calc")
    public Response createCalculation(Calculation calculation) {
        int result=0;
        try {
            // perform the calculation based on the operation
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
                    throw new Exception("Invalid operation: " + calculation.getOperation());
            }
            
            // save the calculation to the database
            entityManager.persist(calculation);
            
            // return the result
            return Response.ok().entity("{\"Result\":" + result + "}").build();
        } catch (Exception e) {
            // return an error response if an exception occurs
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
   
    @GET
    @Path("/calculation")
    public Response getAllCalculations() {
        List<Calculation> query = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
        return Response.ok().entity(query).build();
    }
}