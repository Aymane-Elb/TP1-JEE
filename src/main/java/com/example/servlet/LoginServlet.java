package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/submit")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // Encodage pour bien lire/écrire les accents
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String username  = req.getParameter("username");
        String password  = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String gender    = req.getParameter("gender");
        String address   = req.getParameter("address");

        // petite fonction pour échapper le HTML (anti-XSS dans cet exercice)
        java.util.function.Function<String,String> esc = s ->
                s == null ? "" : s.replace("&","&amp;")
                        .replace("<","&lt;")
                        .replace(">","&gt;")
                        .replace("\"","&quot;");

        PrintWriter out = resp.getWriter();
        out.println("""
      <!doctype html>
      <html lang="fr"><head>
        <meta charset="UTF-8">
        <title>Résultat</title>
        <style>
          body{font-family: system-ui, sans-serif; max-width:720px; margin:24px auto;}
          table{border-collapse:collapse; width:100%}
          td,th{border:1px solid #ddd; padding:8px;}
          th{background:#f5f5f5; text-align:left;}
          .muted{color:#888}
        </style>
      </head><body>
        <h2>Résultats du formulaire</h2>
        <table>
          <tr><th>Champ</th><th>Valeur</th></tr>
    """);

        out.printf("<tr><td>User Name</td><td>%s</td></tr>%n", esc.apply(username));
        out.printf("<tr><td>Password</td><td class='muted'>(%d chars)</td></tr>%n",
                password == null ? 0 : password.length());
        out.printf("<tr><td>First Name</td><td>%s</td></tr>%n", esc.apply(firstName));
        out.printf("<tr><td>Last Name</td><td>%s</td></tr>%n", esc.apply(lastName));
        out.printf("<tr><td>Gender</td><td>%s</td></tr>%n", esc.apply(gender));
        out.printf("<tr><td>Address</td><td><pre style='margin:0'>%s</pre></td></tr>%n",
                esc.apply(address));

        out.println("""
        </table>
        <p><a href="index.html">⟵ Retour au formulaire</a></p>
      </body></html>
    """);
    }
}
