import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class SessionDemo extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    		 throws ServletException, IOException
    	{
           HttpSession session = request.getSession(true);
           Date createTime = new Date(session.getCreationTime());
           Date lastAccessTime = new Date(session.getLastAccessedTime());
           
           String  title = "Welcome to my website";
           int visitCount = 0;
           String visitCountKey ="visitcount";
           String userIDKey="userID";
           String userID = "MANOJ";

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
          session.setAttribute(visitCountKey,  visitCount);

          // Set response content type
          response.setContentType("text/html");
          PrintWriter out = response.getWriter();

    
           out.println(
               "<html>\n" +
               "<head></head>\n" +
               "<body bgcolor=cyan>" +
               "<h2 align=\"center\">Session Infomation</h2>\n" +
               "<table border=\"1\" align=\"center\">\n" +
               "<tr bgcolor=pink>\n" +
               "<th>Session info</th><th>value</th></tr>\n" +
               "<tr>\n" +
               "  <td>id</td>\n" +
               "  <td>" + session.getId() + "</td></tr>\n" +
               "<tr>\n" +
               "  <td>Creation Time</td>\n" +
               "  <td>" + createTime +
               "  </td></tr>\n" +
               "<tr>\n" +
               "  <td>Time of Last Access</td>\n" +
               "  <td>" + lastAccessTime +
               "  </td></tr>\n" +
               "<tr>\n" +
               "  <td>User ID</td>\n" +
               "  <td>" + userID +
               "  </td></tr>\n" +
               "<tr>\n" +
               "  <td>Number of visits</td>\n" +
               "  <td>" + visitCount + "</td></tr>\n" +
               "</table>\n" +
               "</body></html>");
    }
}