import org.sql2o.*;
import java.util.*;

public class Ranger {
    private String name;
    private int badge;
    private int id;


    public Ranger(String name, int badge){
        this.name = name;
        this.badge = badge;

    }

    public String getName(){
        return name;
    }
    public int getBadge(){
        return badge;
    }


    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherRanger){
        if(!(otherRanger instanceof Ranger)){
            return false;
        } else {
            Ranger ranger = (Ranger) otherRanger;
            return this.getName().equals(ranger.getName()) &&
                    this.getBadge() == ranger.getBadge();

        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO rangers (name, badge) VALUES (:name,:badge)";
            this.id =(int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("badge", this.badge)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<Ranger> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM rangers";
            return    con.createQuery(sql)
                    .executeAndFetch(Ranger.class);
        }
    }
    public static Ranger find(int id){
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM rangers WHERE id =:id";
            Ranger ranger  = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }
    public void update(String name, int badge){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE rangers SET name = :name, badge = :badge WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("badge", badge)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
