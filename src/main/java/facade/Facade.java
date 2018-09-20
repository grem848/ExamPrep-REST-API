package facade;


import entity.Pet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class Facade
{

    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public List<Pet> getAllPets()
    {
        EntityManager em = getEntityManager();

        TypedQuery<Pet> qt = em.createNamedQuery("Pet.findAll", Pet.class);
        List<Pet> petlist = qt.getResultList();
        return petlist;

    }
}