<%-- 
    Document   : index
    Created on : Jan 8, 2017, 8:41:30 PM
    Author     : Murta
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.CommandResult"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tourism guide</title>
    </head>
    <body>
         <body bgcolor="#E6E6FA">
        <%
            MongoClient mongoClient = new MongoClient("192.168.56.12");
            DB db = mongoClient.getDB("nmbp"); 
            DBCollection collection = db.getCollection("news");
        %>
        <%
            int i = 1;
            //db.getCollection('news').find({}).sort({date:-1}).skip(0).limit(10)
            for(DBObject obj : collection.find().sort(new BasicDBObject("date", -1)).skip(0).limit(10)){%>
        <table style="margin-left: auto; margin-right: auto;" border="0" width="800">
            <tbody>
                <tr><td>
                <%Object author = obj.get("author");
                Object title = obj.get("title");
                Object date = obj.get("date");
                Object review = obj.get("review");
                BasicDBObject comms = (BasicDBObject)(obj.get("comments"));
                int l = comms.size();
                %>
                <h3><%out.print(title);%></h3>
                <h5><%out.print(author+", Date: "+date);%></h5>
                <%out.print(review);
                %>
                <br><br>
                </td>
                <td>
              <%String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary((byte[])(obj.get("image")));%>
                    <img src="data:image/jpg;base64, <%=b64%>" alt="Image not found" />
                    </td></tr>
                </tbody>
        </table><center>
            <% if(l!=0){%>
            <table style="margin-left: auto; margin-right: auto;" border="0" width="500">
            <tbody>
            
            <%
                for(int j = 0; j < l; j++){
                    out.print("<tr><td>");
                    out.print(comms.get("c"+j).toString()+"<br>");
                    out.print("</td></tr>");
                }
            }
            %>
            
                </tbody>
            </table>
            <%
                
            %>
         
            <form method="POST" enctype="application/x-www-form-urlencoded" name="comment" action="succ_comment.jsp"><textarea name="comment" rows="1" cols="55">anon: </textarea>
                <input type="hidden" name="id" value="<% out.write(obj.get("_id").toString()); %>" />
                <input type="submit" value="Comment" name="" />
            </form>
         </center>
            <%
            }
            %>
        
        
        
        
        <%             
            mongoClient.close();
        %>
    </body>
</html>
