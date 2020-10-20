package com.ingsw.restservice.model;

import com.ingsw.restservice.config.PasswordGenerator;
import com.ingsw.restservice.config.UserRolesCV;
import com.ingsw.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoSql implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findUserByNickname(username);
		List<UserRolesCV> roles = new ArrayList<>();
		roles.add(new UserRolesCV(user.getUserRole()));
		if (user.getNickname().equals(username)) {
			return new User(user.getNickname(), user.getPwd(), roles);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public Integer getUserIdByNickname(String nickname) {
		return userRepo.findUserByNickname(nickname).getId();
	}

	public Users getUserById(int id) {
		return userRepo.findUserById(id);
	}


	public Users save(Users user) {
		Users newUser = new Users();
		newUser.setNickname(user.getNickname());
		newUser.setNome(user.getNome());
		newUser.setCognome(user.getCognome());
		newUser.setPwd(bcryptEncoder.encode(user.getPwd()));
		newUser.setEmail(user.getEmail());
		newUser.setUserRole("ROLE_USER");
		newUser.setShowNickname(true);
		return userRepo.save(newUser);
	}

	public int setShowNickname(int id, boolean value) {
		return userRepo.setShowNickname(id, value);
	}

	public UserDetails authenticateByFacebookToken(String token){

		try {

			String url = "https://graph.facebook.com/debug_token?input_token=" + token +
					"&access_token=378139930005882|M1foQMocmRROF6_1HvBgnSKAoFQ";
			URL urlString = new URL(url);
			HttpURLConnection connection;
			connection = (HttpURLConnection) urlString.openConnection();
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader json = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				JsonElement jsonTree = JsonParser.parseReader(json);
				JsonObject response = jsonTree.getAsJsonObject();
				if( ((JsonObject)response.get("data")).get("is_valid").getAsBoolean()){
					Long userId= ((JsonObject)response.get("data")).get("user_id").getAsLong();
					String urlFbDetailsUser="https://graph.facebook.com/v8.0/"+userId+"?fields=name%2Cemail&access_token="+token;
					urlString=new URL(urlFbDetailsUser);
					connection = (HttpURLConnection) urlString.openConnection();
					connection.setRequestMethod("GET");
					if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
						json = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						jsonTree = JsonParser.parseReader(json);
						response = jsonTree.getAsJsonObject();
						String userEmail=response.get("email").getAsString();
						String username=response.get("name").getAsString();
						Users user = userRepo.findUserByNickname(userEmail);
						if(user==null){
							//REGISTER USER
							PasswordGenerator passwordGenerator = new PasswordGenerator();
							Users newUser= new Users();
							newUser.setEmail(userEmail);
							newUser.setNome(username);
							newUser.setCognome(" ");
							newUser.setNickname(userEmail);
							newUser.setShowNickname(true);
							newUser.setPwd(bcryptEncoder.encode(passwordGenerator.generateRandomPassword(8)));
							newUser.setUserRole("ROLE_USER");
							user=userRepo.save(newUser);
						}
						List<UserRolesCV> roles = new ArrayList<>();
						roles.add(new UserRolesCV(user.getUserRole()));
						return new User(user.getNickname(), user.getPwd(), roles);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
