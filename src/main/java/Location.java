import org.sql2o.Connection;

import java.util.List;

public class Location{
    private String name;
    private int id;


    public Location(String name){
        this.name = name;


    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherLocation){
        if(!(otherLocation instanceof Location)){
            return false;
        } else {
            Location location = (Location) otherLocation;
            return this.getName().equals(location.getName());


        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO locations (name) VALUES (:name)";
            this.id =(int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<Location> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM locations";
            return    con.createQuery(sql)
                    .executeAndFetch(Location.class);
        }
    }
    public static Location find(int id){
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM locations WHERE id =:id";
            Location location  = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
            return location;
        }
    }
    public void update(String name){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE locations SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
