package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;

import java.util.Map;

public class UserMapper {

    public static UserDataTransfer toDto(UserEntrypoint user) {
        return new UserDataTransfer(user.senha(), user.name(), Map.of("ExternalId__c", user.classe()), user.coins(),
                user.levelXp(), user.accountType());
    }
}
