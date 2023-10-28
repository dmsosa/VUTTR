package com.duvi.vuttr.domain.user;

public record RegisterDTO (String login, String password, UserRole  role) {
}
