package com.ingsw.restservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ingsw.restservice.config.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class FacebookLoginService {

    @Autowired
    private UserDaoSql userDetailsService;

    public Long verifyFbToken(String token) throws IOException {
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
            if (((JsonObject) response.get("data")).get("is_valid").getAsBoolean()) {
                return ((JsonObject)response.get("data")).get("user_id").getAsLong();
            }
        }
        return (long) -1;
    }


    public Users registerUserFromIdFacebook(Long userId, String token) throws IOException {


        String urlFbDetailsUser = "https://graph.facebook.com/v8.0/" + userId
                + "?fields=id%2Cfirst_name%2Clast_name%2Cemail&access_token="
                + token;
        URL urlString = new URL(urlFbDetailsUser);
        HttpURLConnection connection;
        connection = (HttpURLConnection) urlString.openConnection();
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader json = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JsonElement jsonTree = JsonParser.parseReader(json);
            JsonObject response = jsonTree.getAsJsonObject();
            String userEmail = response.get("email").getAsString();
            String name = response.get("first_name").getAsString();
            String lastName = response.get("last_name").getAsString();

            PasswordGenerator passwordGenerator = new PasswordGenerator();

            Users newUser = new Users();
            newUser.setEmail(userEmail);
            newUser.setNome(name);
            newUser.setCognome(lastName);
            newUser.setNickname(userId.toString());
            newUser.setPwd(passwordGenerator.generateRandomPassword(8));

            return userDetailsService.save(newUser);


        }
        return  null;
    }
}
