package io.bookquest.usecase.categories;

public enum CategoriesEnum {

    ADVENTURE("Adventure", "Aventura"),
    CLASSIC("Classic", "Classico"),
    CRIME("Crime", "Crime"),
    FAIRY_TALE("Fairy tale", "Conto de fadas"),
    FANTASY("Fantasy", "Fantasia"),
    HISTORICAL_FICTION("Historical fiction", "Ficção Histórica"),
    HORROR("Horror", "Terror"),
    HUMOUR("Humour", "Humor"),
    FICTION("Fiction", "Ficção"),
    ROMANCE("Romance", "Romance"),
    MYSTERY("Mystery", "Mistério"),
    WAR("War", "Guerra"),
    SCIENCE_FICTION("Science fiction", "Ficção Científica"),
    THRILLER("Triller", "Triller"),
    AUTOBIOGRAPHY("Autobiography", "Autobiografia"),
    SELF_HELP("Self help", "Auto ajuda");
    private final String category;
    private final String translateCategory;

    CategoriesEnum(String category, String translateCategory) {
        this.category = category;
        this.translateCategory = translateCategory;
    }

    public String getCategory() {
        return category;
    }

    public String getTranslateCategory() {
        return translateCategory;
    }
}


