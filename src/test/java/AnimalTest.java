import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

import javax.lang.model.element.AnnotationMirror;

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
}