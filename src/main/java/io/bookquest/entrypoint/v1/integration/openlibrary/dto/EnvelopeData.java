package io.bookquest.entrypoint.v1.integration.openlibrary.dto;

import java.util.List;

public class EnvelopeData {

    private List<BookOpenLibrary> docs;

    public List<BookOpenLibrary> getDocs() {
        return docs;
    }

    public void setDocs(List<BookOpenLibrary> docs) {
        this.docs = docs;
    }
}
