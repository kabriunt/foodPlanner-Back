package com.uca.foodPlanner.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.GenericFilterBean;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import io.jsonwebtoken.Jwts;

@Controller
public class JwtFilter extends GenericFilterBean {
	
//	@Autowired
//	MyUserDetailsService userDetail = new MyUserDetailsService();
	
	@Autowired
	private MongoClient mongoClient = new MongoClient();

	@Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

    	// Obtenemos el token que viene en el encabezado de la peticion
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        List<String> listRoles = new ArrayList<>();
        String[] list_roles = new String[1];
        String dRole = new String();

        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(JwtUtil.KEYSECRET) //la clave secreta esta declara en JwtUtil
                    .parseClaimsJws(token) //este metodo es el que valida
                    .getBody()
                    .getSubject();        //extraigo el nombre de usuario del token

	        /*De manera "sucia" vamos a obtener los roles del usuario*/
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<org.bson.Document> collection = database.getCollection("user");
            FindIterable<Document> d = collection.find(Filters.eq("username", username));
            org.bson.Document document = d.first();
            if (document != null) {
    			dRole = document.getString("role");
            }
            listRoles.add(dRole);
            listRoles.toArray(list_roles);
        }
        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request, list_roles);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
