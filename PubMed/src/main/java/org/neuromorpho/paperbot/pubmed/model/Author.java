package org.neuromorpho.paperbot.pubmed.model;

public class Author {

    private String name;
    private String email;

    public Author(String name, String email) {
        this.name = name;
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void mergeAuthorEmail(Author newAuthor) {
        if (newAuthor.email != null && this.email == null) {
            this.email = newAuthor.email;
        }
    }

    public void mergeAuthorName(Author newAuthor) {
        if (newAuthor.name.length() > this.name.length()) {
            this.name = newAuthor.name;
        }
    }

    public Boolean hasContactEmail() {
        return this.email != null && !this.email.isEmpty();
    }

}
