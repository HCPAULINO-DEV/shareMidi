package com.projects.my.shareMidi_api.security;

public record AuthenticationDto(
        String username,
        String password
) {
}
