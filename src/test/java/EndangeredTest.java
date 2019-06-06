import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void endangered_instantiates(){
        Endangered testEndangered = new Endangered("Buffalo", 2000, false);
        assertEquals(true,testEndangered instanceof Endangered );
    }

    @Test
    public void getName_instantiatesWithName(){
        Endangered testEndangered = new Endangered("Buffalo", 2000, false);
        assertEquals("Buffalo", testEndangered.getName());
    }

    @Test
    public void getPopulation_instantiatesWithPopulation(){
        Endangered testEndangered = new Endangered("Buffalo", 2000, false);
        assertEquals(2000, testEndangered.getPopulation());
    }
    @Test
    public void getEndangered_instantiatesWithEndangered(){
        Endangered testEndangered = new Endangered("Buffalo", 2000, false);
        assertEquals(false, testEndangered.isEndangered());
    }

    @Test
    public void equals_assertsThatObjectsWithSameValueAreEqual(){
       Endangered oneDanger = new Endangered("Buffalo", 2000, false);
        Endangered twoDanger = new Endangered("Buffalo", 2000, false);
        assertTrue(oneDanger.equals(twoDanger));
    }

    @Test
    public void save_insertsObjectIntoDatabase(){
        Endangered testDanger = new Endangered("Buffalo", 2000, false);
        testDanger.save();
        assertEquals(true, Endangered.all().get(0).equals(testDanger));
    }

    @Test
    public void find_retrievesAnObjectWithSimilarId(){
        Endangered oneDanger= new Endangered("Buffalo", 2000, false);
        oneDanger.save();
        Endangered twoDanger = new Endangered("Antelope", 20000, false);
        twoDanger.save();
        assertEquals(Endangered.find(twoDanger.getId()), twoDanger);
    }

    @Test
    public void update_updatesTheDetailsOfASpecies(){
        Endangered testDanger = new Endangered("Buffalo", 2000, false);
        testDanger.save();
        testDanger.update("Buffalo", 1281, false);
        assertEquals(1281, Endangered.find(testDanger.getId()).getPopulation());

    }
}
