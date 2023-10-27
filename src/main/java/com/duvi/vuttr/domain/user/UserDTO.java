package com.duvi.vuttr.domain.user;

public record UserDTO(String username, String email, String password, UserRole userRole) {
}
