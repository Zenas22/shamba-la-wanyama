import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest{

    @Test
    public void animals_instantiates(){
        Animal testAnimal = new Animal("Buffalo", "newborn","okay");
        assertTrue(testAnimal instanceof Animal);
    }

    @Test
    public void animals_instantiatesWithName(){
        Animal testAnimal = new Animal("Giraffe", "adult","okay");
        assertEquals("Giraffe", testAnimal.getName());
    }

    @Test
    public void animal_instantiatesWithAge(){
        Animal testAnimal = new Animal("Buffalo", "newborn","okay");
        assertEquals("newborn", testAnimal.getAge());
    }

    @Test
    public void animal_instantiatesWithHealth(){
        Animal testAnimal = new Animal("Buffalo", "newborn","okay");
        assertEquals("okay", testAnimal.getHealth());
    }
}