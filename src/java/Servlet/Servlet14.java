/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author Hossain
 */
public class Servlet14 extends HttpServlet {
    @Resource(mappedName = "jms/newQueue")
    private Queue newQueue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

  

    @Resource(mappedName = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/newQueue")
    Queue queue;
    private static final String name = "HassanjjjjjjjjjjjjjIs";
    private static final String phone = "777777777777";
    private static final String mat = "wall";
    private static final String wallArea = "25";
    private static final String numCoat = "2";
    private static final String ceilArea = "1660";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet14</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet14 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            // context = connectionFactory.createContext();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        if (request.getParameter("action").equalsIgnoreCase("login")) {
            //when login is pressed
            if (confirmLogin(request, response)) { //check if the password is correct
                try {
                    org.json.JSONObject object = new org.json.JSONObject();
               
                    object.put("customerName", name);
                    object.put("phoneNumber", phone);
                    object.put("material", mat);
                    object.put("wallArea", wallArea);
                    object.put("numberOfCoatings", numCoat);
                    object.put("ceilingArea", ceilArea);
                  
                    System.out.print(object);

                    sendJMSMessageToNewQueue(object.toString());
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    
    //------------------------------Check user's credentials-------------------------------------------------------

    private boolean confirmLogin(HttpServletRequest request, HttpServletResponse response) {
        if (!request.getParameter("username").isEmpty() && !request.getParameter("password").isEmpty()) {
            return true;
        } else {
            try {
                response.sendRedirect("/14ClientSide/index.jsp?error=Invalid userInfo!");
            } catch (IOException ex) {
                System.out.println("Error while redirecting");
                ex.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendJMSMessageToNewQueue(String messageData) {
        context = connectionFactory.createContext();
        context.createProducer().send(newQueue, messageData);
    }

   

}
