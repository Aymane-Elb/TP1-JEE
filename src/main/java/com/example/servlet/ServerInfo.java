package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/info")
public class ServerInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!doctype html><html><head><meta charset='utf-8'><title>Request info</title>");
        out.println("<style>body{font:14px/1.45 system-ui,Arial} h2{margin-top:1.1rem}"
                + ".k{color:#336}.v{color:#060}</style></head><body>");

        // ===== Request =====
        out.println("<h2>Request</h2>");
        line(out, "requestURL", req.getRequestURL().toString());
        line(out, "requestURI", req.getRequestURI());
        line(out, "contextPath", req.getContextPath());
        line(out, "servletPath", req.getServletPath());
        line(out, "queryString", req.getQueryString());
        line(out, "method", req.getMethod());
        line(out, "protocol", req.getProtocol());
        line(out, "getParameter text1", getParam(req, "text1"));
        line(out, "getParameter text2", getParam(req, "text2"));

        // ===== Server info =====
        out.println("<h2>Server info:</h2>");
        line(out, "serverName", req.getServerName());
        line(out, "serverPort", String.valueOf(req.getServerPort()));

        // ===== Client info =====
        out.println("<h2>Client info:</h2>");
        line(out, "remoteAddr", req.getRemoteAddr());
        line(out, "remoteHost", req.getRemoteHost());
        line(out, "remotePort", String.valueOf(req.getRemotePort()));
        line(out, "remoteUser", String.valueOf(req.getRemoteUser()));

        // ===== Headers =====
        out.println("<h2>headers:</h2>");
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            line(out, name, req.getHeader(name));
        }

        // ===== Context =====
        out.println("<h2>Servlet Context info:</h2>");
        line(out, "realPath", getServletContext().getRealPath("/"));

        out.println("</body></html>");
    }

    private static String getParam(HttpServletRequest req, String name) {
        String v = req.getParameter(name);
        return v == null ? "" : v;
    }

    private static void line(PrintWriter out, String key, String value) {
        if (value == null) value = "null";
        out.println("<div><span class='k'>" + esc(key) + ":</span> "
                + "<span class='v'>" + esc(value) + "</span></div>");
    }

    private static String esc(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp); // allow POST to show the same info
    }
}
