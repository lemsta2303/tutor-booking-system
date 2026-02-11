package com.slemanski.backend.features.auth.dto;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.model.Role;

public record InfoAboutMeDto(long id, String username, Role role) {
    public static InfoAboutMeDto from(MyUser user) {
        return new InfoAboutMeDto(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}
