import org.sql2o.*;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-50-19-254-63.compute-1.amazonaws.com:5432/ddt05tau08kufl", "qqjseqsocqppjc", "2ad6e9edd7e2863a96ecf9384e72b1cf000b045457fe4d95c1cd3cd0fbe4bf91");
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "zeus", "null");
}