package io.bookquest.usecase;

import io.bookquest.usecase.categories.CategoriesEnum;

public enum ClassEnum {
    NEWBIE("Newbie","Novato", null);

    private final String id;
    private final String className;
    private final CategoriesEnum relation;

    ClassEnum(String id, String className, CategoriesEnum relation) {
        this.id = id;
        this.className = className;
        this.relation = relation;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public CategoriesEnum getRelation() {
        return relation;
    }
}
