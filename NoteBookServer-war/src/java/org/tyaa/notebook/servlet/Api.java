/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.notebook.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tyaa.notebook.entity.State;
import org.tyaa.notebook.facade.Order1Facade;
import org.tyaa.notebook.facade.StateFacade;

/**
 *
 * @author Администратор
 */
public class Api extends HttpServlet {
    
    @EJB
    private Order1Facade order1Facade;
    @EJB
    private StateFacade stateFacade; 

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
        
        //response.setContentType("text/html;charset=UTF-8");
        Gson gson = new Gson();
                /*new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();*/
        response.setContentType("text/plain;charset=UTF-8");
        
        //order1Facade.count();
        try (PrintWriter out = response.getWriter()) {
            
            if (request.getParameterMap().keySet().contains("action")) {
                
                switch(request.getParameter("action")) {
                    case "get_states" : {
                    
                        try {
                            out.print(gson.toJson(stateFacade.findAll()));
                        } catch (Exception e) {
                            out.print(gson.toJson("error"));
                        }
                        break;
                    }
                    case "get_orders" : {
                    
                        try {
                            out.print(gson.toJson(order1Facade.findAll()));
                        } catch (Exception e) {
                            out.print(gson.toJson("error"));
                        }
                        break;
                    }
                    /*case "create_order" : {
                    
                        try {
                            String customerName = request.getParameter("customer_name");
                            String description = request.getParameter("description");
                            State state = stateFacade.findByName("created");
                            
                            if (state == null) {
                                
                                out.print(gson.toJson("State not exists"));
                                break;
                            }
                            
                            Order1 order1 = new Order1();
                            order1.setCustomerName(customerName);
                            order1.setDescription(description);
                            order1.setCreatedAt(new Date());
                            order1.setStateId(state);
                            order1Facade.create(order1);
                            out.print(gson.toJson("ok"));
                        } catch (Exception e) {
                            out.print(gson.toJson("error"));
                        }
                    }*/
                }
            }
            
            //out.print(gson.toJson(stateFacade.findAll()));
            /*stateFacade.findAll().forEach((state) -> {
                out.printf("id = %d; name = %s\n", state.getId(), state.getName());
            });*/ /*out.println("</body>");
            out.println("</html>");*/
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
        processRequest(request, response);
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

}
