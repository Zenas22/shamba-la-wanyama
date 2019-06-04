import org.sql2o.*;
import java.sql.Timestamp;
import java.util.*;

public class Sighting{
    private String ranger;
    private String location;
    private int animalid;
    private Timestamp timestamp;
    private int id;

    public Sighting(String ranger, String location, int animalid) {
        if(ranger.equals("")){
            throw new IllegalArgumentException("Please enter a valid Ranger");
        }
        if(location.equals("")){
            throw new IllegalArgumentException("Please enter a valid location");
        }
        this.ranger = ranger;
        this.location = location;
        this.animalid = animalid;
    }

    public String getRanger() {
        return ranger;
    }

    public String getLocation() {
        return location;
    }

    public int getAnimalid() {
        return animalid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Sighting)){
            return false;
        }
        else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getRanger().equals(newSighting.getRanger()) &&
                    this.getLocation().equals(newSighting.getLocation()) &&
                    this.getAnimalid() == newSighting.getAnimalid() &&
                    this.getId() == newSighting.getId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings(ranger,location,animalid,timestamp) VALUES (:ranger, :location, :animalid, :timestamp);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("ranger", this.ranger)
                    .addParameter("location", this.location)
                    .addParameter("animalid", this.animalid)
                    .addParameter("timestamp", this.timestamp)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        } catch (IndexOutOfBoundsException exception){
            return null;
        }
    }
}