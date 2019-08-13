/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.dto.fulltext;


import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.Author;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.AuthorGroup;


import java.util.ArrayList;
import java.util.List;


public class AuthorGroupDtoAssembler {

    protected AuthorGroupDto createAuthorGroupDto(AuthorGroup authorGroup) {
        AuthorGroupDto authorGroupDto = new AuthorGroupDto();
        authorGroupDto.setAffiliationList(authorGroup.getAffiliationList());
        authorGroupDto.setAuthorList(this.createAuthorListDto(authorGroup.getAuthorList()));
        return authorGroupDto;
    }

    private List<AuthorDto> createAuthorListDto(List<Author> authorList) {
        List<AuthorDto> authorDtoList = new ArrayList();
        for (Author author : authorList) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setEmail(author.getEmail());
            authorDto.setGivenName(author.getGivenName());
            authorDto.setSurname(author.getSurname());
            authorDtoList.add(authorDto);
        }
        return authorDtoList;

    }


}
