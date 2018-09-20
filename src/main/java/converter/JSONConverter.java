
package converter;

import com.google.gson.Gson;
import entity.Pet;
import java.util.List;


public class JSONConverter
{

    public static Pet getPetFromJson(String json)
    {
        return new Gson().fromJson(json, Pet.class);
    }
    
    public static String getJSONFromPet(Pet pet)
    {
        return new Gson().toJson(pet);
    }  

    public static String getJSONFromPets(List<Pet> pets)
    {
        return new Gson().toJson(pets);
    }      
}
