import org.sql2o.*;

import java.util.*;

public class Endangered{
    private String name;
    private int population;
    private boolean endangered;
    private int id;

    public Endangered(String name, int population, boolean endangered){
        this.name = name;
        this.population = population;
        this.endangered = endangered;

    }

    public String getName(){
        return name;
    }

    public int getPopulation(){
        return population;
    }
    public boolean isEndangered(){
        return endangered;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherEndangered){
        if(!(otherEndangered instanceof Endangered)){
            return false;
        } else {
            Endangered endangered = (Endangered) otherEndangered;
            return this.getName().equals(endangered.getName()) &&
                    this.getPopulation() == endangered.getPopulation() &&
                    this.isEndangered() == endangered.isEndangered();

        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO species (name, population, endangered) VALUES (:name, :population, :endangered)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("population", this.population)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Endangered> all(){
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM endangered";
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

    public static Endangered find(int id){
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM endangered WHERE id =:id";
            Endangered endangered  = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Endangered.class);
            return endangered;
        }
    }

    public void update(String name, int population, boolean endangered){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE endangered SET name = :name, population = :population, endangered = :endangered WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("population", population)
                    .addParameter("endangered", endangered)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
