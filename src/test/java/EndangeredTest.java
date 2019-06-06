import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void endangered_instantiates(){
        Endangered testEndangered = new Endangered("Buffalo", 0622, false);
        assertEquals(true,testEndangered instanceof Endangered );
    }

    @Test
    public void getName_instantiatesWithName(){
        Endangered testEndangered = new Endangered("Buffalo", 0622, false);
        assertEquals("Buffalo", testEndangered.getName());
    }

    @Test
    public void getPopulation_instantiatesWithPopulation(){
        Endangered testEndangered = new Endangered("Buffalo", 0622, false);
        assertEquals(0622, testEndangered.getPopulation());
    }
    @Test
    public void getEndangered_instantiatesWithEndangered(){
        Endangered testEndangered = new Endangered("Buffalo", 0622, false);
        assertEquals(false, testEndangered.isEndangered());
    }

    @Test
    public void save_true(){
        Endangered testDanger = new Endangered("Buffalo", 0622, false);
        testDanger.save();
        assertEquals(true, Endangered.all().get(0).equals(testDanger));
    }

    @Test
    public void find_returnsAnObjectWithSimilarId(){
        Endangered oneDanger= new Endangered("Buffalo", 1800, false);
        oneDanger.save();
        Endangered twoDanger = new Endangered("Antelope", 6220, false);
        twoDanger.save();
        assertEquals(Endangered.find(twoDanger.getId()), twoDanger);
    }

    @Test
    public void update_updatesTheDetailsOfASpecies(){
        Endangered testDanger = new Endangered("Buffalo", 1000, false);
        testDanger.save();
        testDanger.update("Buffalo", 1281, false);
        assertEquals(1281, Endangered.find(testDanger.getId()).getPopulation());
    }
}
