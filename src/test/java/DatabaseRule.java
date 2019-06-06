import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "zeus", "null");
    }

    @Override
    protected void after(){
        try(Connection con = DB.sql2o.open()){
            String sqlSpecies = "DELETE FROM species *; ";
            String sqlAnimals = "DELETE FROM animals *;";
            String sqlRangers = "DELETE FROM rangers *;";
            String sqlLocation = "DELETE FROM locations *;";
            String sqlSightings = "DELETE FROM sightings *;";
            con.createQuery(sqlAnimals).executeUpdate();
            con.createQuery(sqlLocation).executeUpdate();
            con.createQuery(sqlRangers).executeUpdate();
            con.createQuery(sqlSightings).executeUpdate();
            con.createQuery(sqlSpecies).executeUpdate();
        }
    }

}