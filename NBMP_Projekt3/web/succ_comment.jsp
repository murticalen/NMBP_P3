<%-- 
    Document   : succ_comment
    Created on : Jan 9, 2017, 10:52:26 AM
    Author     : Murta
--%>

<%@page import="org.bson.types.ObjectId"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comment successful</title>
    </head>
    <body>
        <center>
            <h4>
            <%
                //dodaj obradu komentara
                try{
                    Map<String, String[]> map = request.getParameterMap();
                    MongoClient mongoClient = new MongoClient("192.168.56.12");
                    DB db = mongoClient.getDB("nmbp"); 
                    DBCollection collection = db.getCollection("news");
                    String[] paramValues = map.get("id");
                    DBObject obj = collection.findOne(new BasicDBObject("_id", new ObjectId(map.get("id")[0])));
                    BasicDBObject comms = (BasicDBObject)(obj.get("comments"));
                    int l = comms.size();
                    comms.append("c"+l, map.get("comment")[0]);
                    obj.removeField("comments");
                    obj.put("comments", comms);
                    collection.update(new BasicDBObject("_id", new ObjectId(map.get("id")[0])), obj);
                    out.write("Successfully added your comment!");
                }
                catch(Exception e){
                    out.write("There was an error adding your comment. Try again.");
                }
            %>
            </h4>
            
            <h4>Return to the previous page by pressing:</h4>
            <form name="return" action="index.jsp" method="POST" enctype="multipart/form-data">
                <input type="submit" value="Return" />
            </form>
        </center>
    </body>
</html>
