package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;

import java.util.Map;

public class UserMapper {

    public static UserDataTransfer toDto(UserEntrypoint user) {
        Map<String, String> classeMap = null;
        if (user.classe() != null) {
            classeMap = Map.of("ExternalId__c", user.classe());
        }

        return new UserDataTransfer(user.senha(), user.name(), classeMap, user.coins(),
                user.levelXp(), user.accountType());
    }
}
