
 
import java.util.List;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.MongoClient;
 
public class Test {
 
    public static void main(String[] args) {
        try {
             
            MongoClient mongoClient = new MongoClient("192.168.56.12");
             
            List<String> databases = mongoClient.getDatabaseNames();
             
            for (String dbName : databases) {
                System.out.println("- Database: " + dbName);
                 
                DB db = mongoClient.getDB(dbName);
                 
                Set<String> collections = db.getCollectionNames();
                for (String colName : collections) {
                    System.out.println("\t + Collection: " + colName);
                }
            }
             
            mongoClient.close();
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
    }
}