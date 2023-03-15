package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.integration.database.dto.RecordDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.CategoryRecord;
import io.bookquest.entrypoint.v1.integration.database.dto.TypeAttribute;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryMapper {

    public static RecordDataTransfer toCategoryDataTransfer(List<String> categories) {
        return new RecordDataTransfer(true, toListCategoryRecord(categories));
    }

    public static List<Record> toListCategoryRecord(List<String> categories) {
        var typeAttribute = new TypeAttribute("Product2");

        return categories.stream()
                .map(category -> toCategoryRecord(category, typeAttribute, "Skill"))
                .toList();
    }

    public static Record toCategoryRecord(String category, TypeAttribute typeAttribute, String family) {
        return new CategoryRecord(typeAttribute, category, family, normalizePortuguese(category));
    }

    static String normalizePortuguese(String category) {
        return category.replace(" ", "")
                .replace("á","a")
                .replace("ç","c")
                .replace("ã", "a")
                .replace("é", "e")
                .replace("í","i")
                .replace("ó","o");
    }
}
