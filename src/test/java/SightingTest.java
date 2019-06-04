import org.junit.*;
import org.sql2o.*;
import java.util.*;
import static org.junit.Assert.*;

public class SightingTest{
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiates(){
        Animal testAnimal = new Animal("Buffalo", "adult","healthy", 1);
        testAnimal.save();
        Sighting testSighting = new Sighting("Zeus","Zone A", testAnimal.getId());
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void save_true(){
        Animal testAnimal = new Animal("Buffalo", "newborn", "okay",1);
        testAnimal.save();
        Sighting testSighting = new Sighting("Zeus","Zone A", testAnimal.getId());
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_true(){
        Animal oneAnimal = new Animal("Buffalo","newborn","okay",1);
        oneAnimal.save();
        Animal twoAnimal = new Animal("Giraffe", "adult","okay",2);
        twoAnimal.save();
        Sighting oneSighting = new Sighting("Zeus","Zone A", oneAnimal.getId());
        oneSighting.save();
        Sighting twoSighting = new Sighting("Zeus","Zone B", twoAnimal.getId());
        twoSighting.save();
        assertTrue(Sighting.all().get(0).equals(oneSighting));
        assertTrue(Sighting.all().get(1).equals(twoSighting));
    }

    @Test
    public void find_returnsSightingWithSameId(){
        Animal oneAnimal = new Animal("Buffalo","newborn","okay",1);
        oneAnimal.save();
        Animal twoAnimal = new Animal("Giraffe", "adult","okay",2);
        twoAnimal.save();
        Sighting oneSighting = new Sighting("Zeus","Zone A", oneAnimal.getId());
        oneSighting.save();
        Sighting twoSighting = new Sighting("Zeus","Zone B", twoAnimal.getId());
        twoSighting.save();
        assertEquals(Sighting.find(twoSighting.getId()), twoSighting);
    }
}