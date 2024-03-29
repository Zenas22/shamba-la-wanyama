import org.sql2o.*;
import java.util.List;

public class Animal{
    private int id;
    private String name;
    private String age;
    private String health;
    private int speciesid;

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAge(){
        return age;
    }

    public String getHealth() {
        return health;
    }

    public int getSpeciesid(){
        return speciesid;
    }

    public Animal(String name, String age, String health, int speciesid) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.speciesid = speciesid;
    }

    public static List<Animal> all(){
        String sql = "SELECT * FROM animals";
        try (Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql ="SELECT * FROM animals where id=:id";
            Animal newAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return newAnimal;
        } catch (IndexOutOfBoundsException exception){
            return null;
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, age, health, speciesid) VALUES(:name, :age, :health, :speciesid)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("speciesid", this.speciesid)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String age, String health){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE animals SET age= :age, health= :health WHERE id= :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("age", age)
                    .addParameter("health", health)
                    .executeUpdate();
        }
    }

    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animal)){
            return false;
        }
        else {
            Animal newAnimal = (Animal) otherAnimal;
            return  this.getName().equals(newAnimal.getName()) &&
                    this.getSpeciesid() == newAnimal.getSpeciesid() &&
                    this.getAge().equals(newAnimal.getAge()) &&
                    this.getHealth().equals(newAnimal.getHealth()) &&
                    this.getId() == newAnimal.getId() ;
        }
    }
}