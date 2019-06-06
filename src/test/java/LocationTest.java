import org.junit.*;
import static org.junit.Assert.*;

public class LocationTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void location_instantiates(){
        Location testLocation = new Location("Zone A");
        assertTrue(testLocation instanceof Location);
    }

    @Test
    public void save(){
        Location testLocation =  new Location ("Zone A");
        testLocation.save();
        assertEquals(true, Location.all().get(0).equals(testLocation));
    }

    @Test
    public void all(){
        Location oneLocation =  new Location ("Zone A");
        oneLocation.save();
        Location twoLocation =  new Location ("Zone B");
        twoLocation.save();
        assertTrue(Location.all().get(0).equals(oneLocation));
        assertTrue(Location.all().get(1).equals(twoLocation));
    }

    @Test
    public void find(){
        Location location =  new Location ("Zone A");
        location.save();
        Location locationTwo =  new Location ("Zone B");
        locationTwo.save();
        assertEquals(locationTwo.getName(), Location.find(locationTwo.getId()).getName());
    }

    @Test
    public void update(){
        Location location =  new Location ("Zone A");
        location.save();
        location.update("Zone B");
        assertEquals("Zone B", Location.find(location.getId()).getName());
    }
}
