package calculations;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;



@Stateless
@Path("calc")
public class CalcPost {
    @PersistenceContext(unitName = "calcPU")
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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
    
    private CalculationRepository repository;

    @Inject
    public void CalculationResource(CalculationRepository repository) {
        this.repository = repository;
    }

    @Path("calculations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCalculations() {
        try {
            List<Calculation> calculations = repository.getAllCalculations();
            if (calculations.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(calculations).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
