import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.util.*; 
 
// Extend HttpServlet class 
public class SessionDemo extends HttpServlet 
{ 
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{ 
// Create a session object if it is already not created. 
HttpSession session = request.getSession(true); 
// Get session creation time. 
Date createTime = new Date(session.getCreationTime()); 
// Get last access time of this web page. 
Date lastAccessTime = new Date(session.getLastAccessedTime()); 
 
String title = "Welcome Back to my website"; 
Integer visitCount = new Integer(0); 
String visitCountKey = new String("visitCount"); 
String userIDKey = new String("userID"); 
String userID = new String("Viru"); 
 
// Check if this is new comer on your web page. 
if (session.isNew()) 
{ 
title = "Welcome to my website"; 
session.setAttribute(userIDKey, userID); 
} 
else 
{ 
visitCount = (Integer)session.getAttribute(visitCountKey); 
visitCount = visitCount + 1; 
userID = (String)session.getAttribute(userIDKey); 
} 
session.setAttribute(visitCountKey, visitCount); 
 
// Set response content type 
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
 
String docType = 
"<!doctype html public \"-//w3c//dtd html 4.0 " + 
"transitional//en\">\n"; 
 
out.println(docType + 
"<html>" + 
"<head><title>" + title + "</title>"+
"<style> h3{color:blue;}</style></head>" + 

"<body bgcolor=\"yellow\">\n" + 
"<h1 align=\"center\">" + title + "</h1>\n" + 
"<h2 align=\"center\">Session Infomation</h2>\n" + 

"<h3>Session info:<h4>"+session.getId()+"</h4></h3>"+
"<h3>creation time:<h4>"+createTime+"</h4></h3>"+
"<h3>Time of Last Access:<h4>"+lastAccessTime+"</h4></h3>"+
"<h3>User ID:<h4>"+userID+"</h4></h3>"+
"<h3>Number of visits:<h4>"+visitCount+"<h4></h3>"+

"</body></html>"); 
} 
}