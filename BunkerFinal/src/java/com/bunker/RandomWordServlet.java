package com.bunker;

import java.io.IOException;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CORE CONTROLLER - INTERCEPTS INTERACTIVE CLIENT TRAFFIC
 * Architecture Layer: HttpServlet (Servlet Container)
 * * Mapped under the optimized lowercase URL pattern "/randomword".
 * Acts as the bridge between the public JSP frontend and the EJB backend core.
 */
@WebServlet(name = "RandomWordServlet", urlPatterns = {"/randomword"})
public class RandomWordServlet extends HttpServlet {

    // EJB Dependency Injection (Automated resource discovery via GlassFish Container)
    @EJB 
    private WordService wordService;
    
    @EJB 
    private SecurityService securityService;

    /**
     * Intercepts and processes synchronous POST requests dispatched from index.jsp
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Enforce international character encoding to protect Asian/Hindi Unicode strings
        request.setCharacterEncoding("UTF-8");
        
        // Volatile local repository containing Operational Technology (OT) directives
        String[] securityTips = {
            "Zero Trust Architecture: Never Trust, Always Verify.",
            "Network Segmentation is critical to isolate IT environments from OT production lines.",
            "Enforce strict multi-factor authentication (MFA) for vault administration access."
        };

        Random rand = new Random();
        
        try {
            // 1. Fetch synchronized linguistic array from the Stateless WordService EJB
            String[] words = wordService.getRandomWordSet();
            
            // 2. Inject raw data sets into the HTTP Request Context attributes
            request.setAttribute("aleman", words[0]);
            request.setAttribute("espanol", words[1]);
            request.setAttribute("mandarin", words[2]);
            request.setAttribute("japones", words[3]);
            request.setAttribute("hindi", words[4]);
            
            // 3. Invoke Security Engine to compute cryptography vectors and tokens
            request.setAttribute("sha256", securityService.generateSHA256(words[0])); // Hashing German Pivot Key
            request.setAttribute("token", securityService.generateSecureToken());
            request.setAttribute("key", securityService.generateEphemeralKey());
            
            // 4. Select and push a random architecture tip into the view context
            request.setAttribute("tip", securityTips[rand.nextInt(securityTips.length)]);

        } catch (Exception e) {
            // Error mitigation: Intercept runtime container exceptions and report breach
            request.setAttribute("error", "Vault Access Breach: " + e.getMessage());
        }
        
        // 5. Secure internal server forwarding back to the public tactical dashboard
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}