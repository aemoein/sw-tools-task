import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CalculationRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Calculation> getAllCalculations() {
        TypedQuery<Calculation> query = em.createQuery("SELECT c FROM Calculation c", Calculation.class);
        return query.getResultList();
    }
}