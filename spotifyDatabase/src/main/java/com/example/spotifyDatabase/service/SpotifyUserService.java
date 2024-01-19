package com.example.spotifyDatabase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetUsersProfileRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpotifyUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyUserService.class);
    private final ObjectMapper mapper;
    @Value("${spotify.api-key}")
    private static String API_KEY;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(API_KEY).build();

    private static GetUsersProfileRequest getUsersProfileRequest(String userId) {
        return spotifyApi.getUsersProfile(userId).build();
    }

    private static GetRecommendationsRequest getUserRecommendationsRequest(String genre) {
        return spotifyApi.getRecommendations().seed_genres(genre).build();
    }
    public static ResponseEntity<String> getUserProfile(String userId) throws IOException, ParseException, SpotifyWebApiException {
        User user = getUsersProfileRequest(userId).execute();
        return ResponseEntity.ofNullable(user.getDisplayName());
    }

    public static ResponseEntity<Integer> getUserRecommendations(String genre) throws IOException, ParseException, SpotifyWebApiException {
        Recommendations recommendations = getUserRecommendationsRequest(genre).execute();
        return ResponseEntity.ofNullable(recommendations.getTracks().length);
    }





}
