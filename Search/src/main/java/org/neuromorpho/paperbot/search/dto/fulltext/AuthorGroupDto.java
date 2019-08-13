
package org.neuromorpho.paperbot.search.dto.fulltext;


import java.util.List;

public class AuthorGroupDto {

    private List<AuthorDto> authorList;
    private List<String> affiliationList;

    public AuthorGroupDto() {
    }

    public List<AuthorDto> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorDto> authorList) {
        this.authorList = authorList;
    }

    public List<String> getAffiliationList() {
        return affiliationList;
    }

    public void setAffiliationList(List<String> affiliationList) {
        this.affiliationList = affiliationList;
    }
}
