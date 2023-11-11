package com.duvi.vuttr.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public record AuthDTO(String login, String password) {
}
