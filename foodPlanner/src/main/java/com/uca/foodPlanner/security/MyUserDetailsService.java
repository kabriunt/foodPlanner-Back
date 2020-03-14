package com.uca.foodPlanner.security;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.uca.foodPlanner.dto.UserDto;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MongoClient mongoClient;

	private MongoCollection<org.bson.Document> collection;

	private MongoDatabase database;

	private FindIterable<Document> d;

	@Override
	public UserDetails loadUserByUsername(String username) {
		database = mongoClient.getDatabase("test");
		collection = database.getCollection("user");
		d = collection.find(Filters.eq("username", username));
		org.bson.Document document = d.first();

		if (document != null) {
			String dId = document.getString("id");
			String dUsername = document.getString("username");
			String dEmail = document.getString("email");
			String dPassword = document.getString("password");
			String dConfirm = document.getString("confirm");
			String dRole = document.getString("role");
			boolean dNonExpiredAccount = document.getBoolean("nonExpiredAccount");
			boolean dNonExpiredCredentials = document.getBoolean("nonExpiredCredentials");
			boolean dNonLocked = document.getBoolean("nonLocked");
			boolean dEnabled = document.getBoolean("enabled");
			MongoUserDetails mongoUserDetails = new MongoUserDetails(new UserDto(dId, dUsername, dEmail, dPassword,
					dConfirm, dRole, dNonExpiredAccount, dNonLocked, dNonExpiredCredentials, dEnabled));

			return mongoUserDetails;
		} else {

			throw new UsernameNotFoundException("username not found");
		}
	}
	
	
	public MongoDatabase getDataSource() {
		return mongoClient.getDatabase("test");
	}
}