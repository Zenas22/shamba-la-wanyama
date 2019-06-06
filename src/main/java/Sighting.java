import org.sql2o.*;
import java.sql.Timestamp;
import java.util.*;

public class Sighting{
    private String ranger;
    private int locationid;
    private int animalid;
    private int endangeredid;
    private Timestamp timestamp;
    private int id;

    public Sighting(String ranger, int locationid, int animalid,int endangeredid) {
        if(ranger.equals("")){
            throw new IllegalArgumentException("Please enter a valid Ranger");
        }
        this.ranger = ranger;
        this.locationid = locationid;
        this.animalid = animalid;
        this.endangeredid = endangeredid;
    }

    public String getRanger() {
        return ranger;
    }

    public int getLocationid() {
        return locationid;
    }

    public int getAnimalid() {
        return animalid;
    }

    public int getSpeciesid(){
        return endangeredid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getId() {
        return id;
    }

    public static Animal getAnimal(int id){
        return Animal.find(id);
    }

    public static Endangered getSpecies(int id){
        return Endangered.find(id);
    }

    public static Location getLocation(int id){
        return Location.find(id);
    }

    @Override
    public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Sighting)){
            return false;
        }
        else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getRanger().equals(newSighting.getRanger()) &&
                    this.getLocationid() == newSighting.getLocationid() &&
                    this.getAnimalid() == newSighting.getAnimalid() &&
                    this.getSpeciesid() == newSighting.getSpeciesid() &&
                    this.getId() == newSighting.getId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings(ranger,locationid,animalid,timestamp,endangered) VALUES (:ranger, :locationid, :animalid, :timestamp, :endangeredid);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("ranger", this.ranger)
                    .addParameter("locationid", this.locationid)
                    .addParameter("animalid", this.animalid)
                    .addParameter("timestamp", this.timestamp)
                    .addParameter("endangeredid", this.endangeredid)
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