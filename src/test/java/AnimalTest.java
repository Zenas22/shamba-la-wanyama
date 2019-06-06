import org.junit.*;
import static org.junit.Assert.*;


public class AnimalTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animals_instantiates(){
        Animal testAnimal = new Animal("Buffalo", "newborn","okay",1);
        assertTrue(testAnimal instanceof Animal);
    }

    @Test
    public void animals_instantiatesWithName(){
        Animal testAnimal = new Animal("Giraffe", "adult","okay",2);
        assertEquals("Giraffe", testAnimal.getName());
    }

    @Test
    public void animal_instantiatesWithAge(){
        Animal testAnimal = new Animal("Buffalo", "newborn","okay",1);
        assertEquals("newborn", testAnimal.getAge());
    }

    @Test
    public void animal_instantiatesWithHealth(){
        Animal testAnimal = new Animal("Buffalo", "newborn","okay",1);
        assertEquals("okay", testAnimal.getHealth());
    }

    @Test
    public void animal_instantiatesWithSpeciesid(){
        Animal testAnimal = new Animal("Buffalo","newborn","okay",1);
        assertEquals(1, testAnimal.getSpeciesid());
    }

    @Test
    public void animal_save_true(){
        Animal testAnimal = new Animal("Buffalo","newborn","okay",1);
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void all_returnsAllAnimals(){
        Animal oneAnimal = new Animal("Buffalo","newborn","okay",1);
        oneAnimal.save();
        Animal twoAnimal = new Animal("Giraffe", "adult","okay",2);
        twoAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(oneAnimal));
        assertEquals(true, Animal.all().get(1).equals(twoAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId(){
        Animal oneAnimal = new Animal("Buffalo","newborn","okay",1);
        oneAnimal.save();
        Animal twoAnimal = new Animal("Giraffe", "adult","okay",2);
        twoAnimal.save();
        assertEquals(twoAnimal, Animal.find(twoAnimal.getId()));
    }

    @Test
    public void update_updatesAnimal(){
        Animal testAnimal = new Animal("Buffalo", "newborn", "okay",1);
        testAnimal.save();
        testAnimal.update("Buffalo", "adult","healthy");
        assertEquals("adult",testAnimal.getAge());
        assertEquals("healthy",testAnimal.getHealth());
        assertEquals("Buffalo",testAnimal.getName());
    }

}