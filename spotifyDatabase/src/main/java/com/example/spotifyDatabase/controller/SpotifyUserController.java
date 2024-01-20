package com.example.spotifyDatabase.controller;

import com.example.spotifyDatabase.model.SpotifyUser;
import com.example.spotifyDatabase.service.SpotifyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class SpotifyUserController {

    private final SpotifyUserService spotifyUserService;
    private String bearerToken;

    public SpotifyUserController(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @GetMapping("/bearerToken")
    public ResponseEntity<HttpStatus> getSpotifyBearerToken(@RequestBody SpotifyUser user) {
        bearerToken = spotifyUserService.getAuthToken(user.getClientId(), user.getClientSecret()).getBody();
        log.info(bearerToken);
        return bearerToken != null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

// need to get refresh token for this and other requests like it to work

//    @GetMapping(value = "/getProfile")
//    ResponseEntity<String> getSpotifyUserProfile() {
//        return spotifyUserService.getUserProfile(bearerToken);
//    }

}
