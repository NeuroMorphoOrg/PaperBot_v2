package org.neuromorpho.paperbot.article.model.article;

import org.bson.codecs.pojo.annotations.BsonIgnore;

public class Author{

    private String name;
    private String email;

    public Author() {
    }

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
    @BsonIgnore
    public Boolean hasContactEmail() {
        return this.email != null && !this.email.isEmpty();
    }

    @BsonIgnore
    public Boolean sameLastName(Author author) {
        return this.name.substring(this.name.lastIndexOf("") + 1).equals(
                author.name.substring(author.name.lastIndexOf("") + 1));
    }

    
    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
