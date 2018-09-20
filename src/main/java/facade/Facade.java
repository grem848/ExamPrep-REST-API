package facade;

import entity.Pet;
import entity.PetDTO;
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
//        public List<PersonDTO> getPersons()
//    {
//        EntityManager em = emf.createEntityManager();
//
//        List<PersonDTO> persons = null;
//        
//        try
//        {
//            em.getTransaction().begin();
////            persons = em.createQuery("Select p from Person p").getResultList();
//            //TypedQuery<PersonDTO> qt = em.createQuery("SELECT NEW entity.PersonDTO(p.firstName, p.lastName, p.phoneNumber) from Person p", PersonDTO.class);
//            //persons = qt.getResultList();
//            persons = em.createQuery("SELECT NEW entity.PersonDTO(p.firstName, p.lastName, p.phoneNumber) from Person p", PersonDTO.class).getResultList();
//                    
//            em.getTransaction().commit();
//            return persons;
//        }
//        finally
//        {
//            em.close();
//        }
//    }
    
        public long getTotalAmountOfPets()
    {
        EntityManager em = getEntityManager();

        Query q = em.createQuery("select COUNT(c) from Pet c WHERE c.id IS NOT NULL");

        long result = (long) q.getSingleResult();
        return result;

    }
    
    
}
