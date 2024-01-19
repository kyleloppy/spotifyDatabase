package com.example.spotifyDatabase.controller;

import com.example.spotifyDatabase.service.SpotifyUserService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

@RestController
public class SpotifyUserController {

    private final SpotifyUserService spotifyUserService;

    public SpotifyUserController(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @GetMapping(value = "/getProfile")
    ResponseEntity<String> getSpotifyUserProfile(@RequestBody String userId) throws IOException, ParseException, SpotifyWebApiException {
        return SpotifyUserService.getUserProfile(userId);
    }

    @GetMapping(value = "/getRecommendations")
    ResponseEntity<Integer> getSpotifyUserRecommendations(@RequestBody String genre) throws IOException, ParseException, SpotifyWebApiException {
        return SpotifyUserService.getUserRecommendations(genre);
    }
}
