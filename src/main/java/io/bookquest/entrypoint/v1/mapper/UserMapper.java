package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.usecase.ClassEnum;

import java.util.Map;

public class UserMapper {

    public static UserDataTransfer toCreateUserDto(UserEntrypoint user) {
        var classeMap = Map.of("ExternalId__c", ClassEnum.NEWBIE.getId());

        return new UserDataTransfer(user.senha(), user.name(), classeMap, 0, 0,
                "Free", null);
    }

    public static UserEntrypoint toEntrypoint(UserDataTransfer user, String userClass) {

        return new UserEntrypoint(null, null, user.name(), userClass, user.coins(), user.levelXp(), user.accountType(), null);
    }

    public static UserDataTransfer updateXp(Integer xp) {
        return new UserDataTransfer(null, null, null, null, xp, null, null);
    }
}
