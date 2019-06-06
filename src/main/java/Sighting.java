import org.sql2o.*;
import java.sql.Timestamp;
import java.util.*;

public class Sighting{
    private int rangerid;
    private int locationid;
    private int animalid;
    private int speciesid;
    private Timestamp timestamp;
    private int id;

    public Sighting(int rangerid, int locationid, int animalid,int speciesid) {
        this.rangerid = rangerid;
        this.locationid = locationid;
        this.animalid = animalid;
        this.speciesid = speciesid;
    }

    public int getRangerid() {
        return rangerid;
    }

    public int getLocationid() {
        return locationid;
    }

    public int getAnimalid() {
        return animalid;
    }

    public int getSpeciesid(){
        return speciesid;
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

    public static Ranger getRanger(int id){
        return Ranger.find(id);
    }

    @Override
    public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Sighting)){
            return false;
        }
        else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getRangerid() == newSighting.getRangerid() &&
                    this.getLocationid() == newSighting.getLocationid() &&
                    this.getAnimalid() == newSighting.getAnimalid() &&
                    this.getSpeciesid() == newSighting.getSpeciesid() &&
                    this.getId() == newSighting.getId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings(rangerid,locationid,animalid,timestamp,speciesid) VALUES (:rangerid, :locationid, :animalid, :timestamp, :speciesid);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerid", this.rangerid)
                    .addParameter("locationid", this.locationid)
                    .addParameter("animalid", this.animalid)
                    .addParameter("timestamp", this.timestamp)
                    .addParameter("speciesid", this.speciesid)
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