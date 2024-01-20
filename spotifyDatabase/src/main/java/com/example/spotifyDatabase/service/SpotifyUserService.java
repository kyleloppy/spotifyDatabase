package com.example.spotifyDatabase.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpotifyUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyUserService.class);
    private final String CLIENT_CREDENTIALS = "client_credentials";

    public ResponseEntity<String> getAuthToken(String clientId, String clientSecret) {
        WebClient webClient = WebClient.builder().build();

        String bearer =
                Objects.requireNonNull(
                webClient.post()
                .uri("https://accounts.spotify.com/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("client_id", clientId)
                    .with("client_secret", clientSecret)
                    .with("grant_type", CLIENT_CREDENTIALS))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block()
                        .get("access_token").asText());

        return ResponseEntity.ofNullable(bearer);


    }

//    public ResponseEntity<String> getUserProfile(String bearerToken) {
//        WebClient webClient = WebClient.builder().build();
//
//        String response = webClient.get()
//                .uri("https://api.spotify.com/v1/me")
//                .header("Authorization", String.format("Bearer %s", bearerToken))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//            return ResponseEntity.ofNullable(response);
//    }
}
