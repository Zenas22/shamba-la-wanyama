import static org.junit.Assert.*;
import org.junit.*;

public class RangerTest {
    @Test
    public void ranger_instantiates(){
        Ranger ranger = new Ranger ("Zeus", 022);
        assertTrue(ranger instanceof Ranger);
    }

    @Test
    public void ranger_instantiatedWithName(){
        Ranger ranger =  new Ranger ("Zeus", 022);
        assertEquals("Zeus", ranger.getName());
    }


    @Test
    public void save(){
        Ranger ranger =  new Ranger ("Zeus", 022);
        ranger.save();
        assertEquals(true, Ranger.all().get(0).equals(ranger));
    }

    @Test
    public void all_retrievesAllInstancesFromTheDatabase(){
        Ranger ranger =  new Ranger ("Zeus", 022);
        ranger.save();
        Ranger rangerTwo =  new Ranger ("Loki", 006);
        rangerTwo.save();
        assertTrue(Ranger.all().get(0).equals(ranger));
        assertTrue(Ranger.all().get(1).equals(rangerTwo));

    }

    @Test
    public void find_retrievesASpecificRangerUsingItsId(){
        Ranger ranger =  new Ranger ("Zeus", 022);
        ranger.save();
        Ranger rangerTwo =  new Ranger ("Loki", 006);
        rangerTwo.save();
        assertEquals(rangerTwo.getName(), Ranger.find(rangerTwo.getId()).getName());
    }

    @Test
    public void update_updatesAnRangerDetails(){
        Ranger ranger =  new Ranger ("Zeus", 022);
        ranger.save();
        ranger.update("Loki", 006);
        assertEquals("Loki", Ranger.find(ranger.getId()).getName());
    }
}
