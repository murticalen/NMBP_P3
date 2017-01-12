import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
 
public class Queryier {
 
    public static void main(String[] args) throws Exception {
        try {
             
            MongoClient mongoClient = new MongoClient("192.168.56.12");
             
                DB db = mongoClient.getDB("nmbp");
                 
                DBCollection collection = db.getCollection("news");
                
        		File file = new File("D:\\FER\\Documents\\Java\\NBMP_Projekt3\\R dataset\\dataNoTitle.txt");
        		BufferedReader reader = new BufferedReader
        				(new InputStreamReader(new FileInputStream(file)));
        		String line;
        		int i = 3;
        		while((line = reader.readLine()) != null){
        			
        			String[] dat = line.split(";");
        			if(dat.length != 4){
        				throw new IllegalArgumentException();
        			}
                	System.out.println("BasicDBObject example...");
                	BasicDBObject document = new BasicDBObject();
                	
                	File image = new File("D:\\FER\\Documents\\Java\\NBMP_Projekt3\\R dataset\\images\\"+i+".Scaled.png");
                	System.out.println(image);
                	BufferedImage img = ImageIO.read(image);
                	
                	ByteArrayOutputStream baos = new ByteArrayOutputStream();
                	ImageIO.write(img, "png", baos);
                	byte[] bytes = baos.toByteArray();
                	
                	document.put("author", dat[0]);
                	document.put("title", dat[1]);
                	document.put("date", dat[2]);
                	document.put("review", dat[3]);
                    document.put("image", bytes);
                	
                	BasicDBObject documentDetail = new BasicDBObject();
                	//documentDetail.put("records", 99);
                	//documentDetail.put("index", "vps_index1");
                	//documentDetail.put("active", "true");
                	document.put("comments", documentDetail);

                	collection.insert(document);

                	DBCursor cursorDoc = collection.find();
                	while (cursorDoc.hasNext()) {
                		System.out.println(cursorDoc.next());
                	}
                	i++;
                	//collection.remove(new BasicDBObject());
        		}
             reader.close();
            mongoClient.close();
             
        } catch (IllegalArgumentException ex) {
        	ex.printStackTrace();
        	throw new IllegalArgumentException();
        }
         
    }
}
