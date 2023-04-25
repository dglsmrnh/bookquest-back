package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.usecase.ClassEnum;

import java.util.Map;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDataTransfer toCreateUserDto(UserEntrypoint user) {
        var classeMap = Map.of("ExternalId__c", ClassEnum.NEWBIE.getId());
        return new UserDataTransfer()
                .setSenha(user.senha())
                .setName(user.name())
                .setClasse(classeMap)
                .setEmail(user.email())
                .setCoins(0)
                .setLevelXp(0)
                .setAccountType("Free");
    }

    public static UserEntrypoint toEntrypoint(UserDataTransfer user, String userClass) {
        return new UserEntrypoint(null, null, user.getName(), userClass, user.getCoins(), user.getLevelXp(), user.getAccountType(), user.getEmail());
    }

    public static UserDataTransfer updateXp(Integer xp) {
        return new UserDataTransfer()
                .setLevelXp(xp);
    }

    public static UserDataTransfer updateInfo(UserEntrypoint user) {
        var classe = user.classe() != null ?
                Map.of("ExternalId__c", user.classe()) : null;
        return new UserDataTransfer()
                .setName(user.name())
                .setEmail(user.email())
                .setClasse(classe);
    }
}
