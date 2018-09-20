package facade;

import entity.Pet;
import entity.PetDTO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
        EntityManager em = emf.createEntityManager();

        List<Pet> pets = null;
        try
        {
            em.getTransaction().begin();
            TypedQuery<Pet> qt = em.createQuery("SELECT NEW entity.Pet(p.id, p.name, p.birth, p.species) from Pet p", Pet.class);
            pets = qt.getResultList();

            em.getTransaction().commit();
            return pets;
        } finally
        {
            em.close();
        }
    }

    public List<PetDTO> getAllPetsInfo()
    {
        EntityManager em = emf.createEntityManager();

        List<PetDTO> pets = null;
        try
        {
            em.getTransaction().begin();
            TypedQuery<PetDTO> qt = em.createQuery("SELECT NEW entity.PetDTO(p.id, p.name, p.birth, p.species, p.death, p.owner.firstName, p.owner.lastName) from Pet p", PetDTO.class);
            pets = qt.getResultList();
            em.getTransaction().commit();
            return pets;
        } finally
        {
            em.close();
        }
    }

    public long getTotalAmountOfPets()
    {
        EntityManager em = getEntityManager();

        Query q = em.createQuery("select COUNT(c) from Pet c WHERE c.id IS NOT NULL");

        long result = (long) q.getSingleResult();
        return result;

    }

    public List<Pet> getAllLivingPets()
    {
        EntityManager em = emf.createEntityManager();

        List<Pet> pets = null;
        try
        {
            em.getTransaction().begin();
            TypedQuery<Pet> qt = em.createQuery("SELECT NEW entity.Pet(p.id, p.name, p.birth, p.species) from Pet p where p.death IS NULL", Pet.class);
            pets = qt.getResultList();

            em.getTransaction().commit();
            return pets;
        } finally
        {
            em.close();
        }
    }

    public List<Pet> getAllPetsWithEventsOnGivenDay(Date date)
    {
        EntityManager em = emf.createEntityManager();

        List<Pet> pets = null;
        try
        {
            em.getTransaction().begin();
            TypedQuery<Pet> qt = em.createQuery("SELECT p.pet.name from Event p where p.date = :date", Pet.class);
            qt.setParameter("date", date);
            pets = qt.getResultList();

            em.getTransaction().commit();
            return pets;
        } finally
        {
            em.close();
        }
    }

}
