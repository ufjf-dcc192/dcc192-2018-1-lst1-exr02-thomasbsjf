package br.ufjf.dcc192;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MapServlet", urlPatterns = {"/map.html"})
public class MapServlet extends HttpServlet {
    
    private Map<String, String> paises;
    
    public Map MapServlet(){
        Map<String,String> p = new HashMap<>();
        p.put("Brasil", "Verde");
        p.put("Canadá", "Vermelho");
        p.put("Uruguai", "Azul");
        p.put("Japão", "Vermelho");
        p.put("Itália", "Verde");
        return p;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String,ArrayList<String>> cores = new HashMap<>();
        String comando = request.getParameter("comando");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            paises = MapServlet();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Países</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Países</h1>");
            out.println("<dl>");
            
            for(Map.Entry<String,String> pais : paises.entrySet()){
                out.println("<dt>" + pais.getKey() +"</dt" 
                       + "<dd>  " + pais.getValue() + "</dd>");                
            }
            
            
            if("cor".equals(comando)){
                for(Map.Entry<String, String> pais : paises.entrySet()){
                    if(!cores.containsKey(pais.getValue())){
                        ArrayList<String> corPais = new ArrayList();
                        corPais.add(pais.getKey());
                        cores.put(pais.getValue(), corPais);
                    }else{
                        cores.get(pais.getValue()).add(pais.getKey());
                    }
                }
                for(Map.Entry<String, ArrayList<String>> cor : cores.entrySet()){
                    out.println("<dt>" + cor.getKey() + "</dt>");
                    for(String pais : cor.getValue()){
                        out.println("<dd>" + pais + "</dd>");
                    }
                }
            }
            
            
            out.println("</dl>");
            out.println("<p> <a href='?comando=cor'> Juntar cores </a> </p>");
            out.println("</body>");
            out.println("</html>");
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
