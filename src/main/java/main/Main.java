
package main;

import facade.Facade;
import javax.persistence.Persistence;


public class Main
{

    public static void main(String[] args)
    {
        Facade facade = new Facade(Persistence.createEntityManagerFactory("pu"));

        System.out.println("Find all pets in the system: \n" + facade.getAllPets());
    }
}
