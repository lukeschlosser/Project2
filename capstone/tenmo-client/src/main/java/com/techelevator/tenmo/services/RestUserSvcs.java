package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestUserSvcs {

    private static final String API_BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> listUsernameAndId(AuthenticatedUser user){
        HttpEntity<AuthenticatedUser> entity = createCredentialsEntity(user);
        User[] users = null;
        List<User> userList = new ArrayList<>();

        try { ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "/tenmo_user", HttpMethod.GET, entity, User[].class);
            users = response.getBody();
            userList = Arrays.asList(users);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userList;
    }

    private HttpEntity<AuthenticatedUser> createCredentialsEntity(AuthenticatedUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(user, headers);
    }
}
