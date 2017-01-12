import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Inserter {
	
	public static void main(String[] args) throws Exception {
        try {
            
            MongoClient mongoClient = new MongoClient("192.168.56.12");
             
                DB db = mongoClient.getDB("nmbp");
                 
                DBCollection collection = db.getCollection("news");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        		String line;
            	BasicDBObject document = new BasicDBObject();
            	
            	System.out.println("Unesite ime autora: ");
            	line = reader.readLine();
            	document.put("author", line);
            	System.out.println("Unesite naslov teksta: ");
            	line = reader.readLine();
            	document.put("title", line);
            	System.out.println("Unesite datum nastanka u YYYY-MM-DD formatu: ");
            	line = reader.readLine();
            	document.put("date", line);
            	System.out.println("Unesite tekst: ");
            	line = reader.readLine();
            	document.put("review", line);
            	System.out.println("Unos ovim putem pretpostavlja da se ne unosi slika.");
            	document.put("image", null);
            	BasicDBObject documentDetail = new BasicDBObject();
            	document.put("comments", documentDetail);
            	collection.insert(document);

            	DBCursor cursorDoc = collection.find();
            	while (cursorDoc.hasNext()) {
            		System.out.println(cursorDoc.next());
            	}
            mongoClient.close();
             
        } catch (IllegalArgumentException ex) {
        	ex.printStackTrace();
        	throw new IllegalArgumentException();
        }
	}
	
}
