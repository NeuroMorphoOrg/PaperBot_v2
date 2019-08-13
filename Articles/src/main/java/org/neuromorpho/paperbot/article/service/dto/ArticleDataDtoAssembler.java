/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.service.dto;


import org.neuromorpho.paperbot.article.model.article.ArticleData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleDataDtoAssembler {

    protected ArticleDataDto createDataDto(ArticleData data) {
        ArticleDataDto dataDto = new ArticleDataDto();
        dataDto.setAuthorList(data.getAuthorList());
        dataDto.setDoi(data.getDoi());
        dataDto.setJournal(data.getJournal());
        dataDto.setPmid(data.getPmid());
        dataDto.setPmcid(data.getPmcid());
        dataDto.setPublishedDate(data.getPublishedDate());
        dataDto.setOcDate(data.getOcDate());
        dataDto.setTitle(data.getTitle());
        dataDto.setLink(data.getLink());
        dataDto.setEvaluatedDate(data.getEvaluatedDate());
        dataDto.setApprovedDate(data.getApprovedDate());

        List<String> usageList = data.getDataUsage().stream().map(
                usage -> usage.getUsage()).collect(Collectors.toList());
        dataDto.setDataUsage(usageList);
        return dataDto;

    }

    public ArticleData createData(ArticleDataDto dataDto) {
        ArticleData data = new ArticleData();
        data.setAuthorList(dataDto.getAuthorList());
        data.setDoi(dataDto.getDoi());
        data.setJournal(dataDto.getJournal());
        data.setPmid(dataDto.getPmid());
        data.setPmcid(dataDto.getPmcid());
        data.setPublishedDate(dataDto.getPublishedDate());
        data.setOcDate(dataDto.getOcDate());
        data.setTitle(dataDto.getTitle());
        data.setLink(dataDto.getLink());
        data.setEvaluatedDate(dataDto.getEvaluatedDate());
        data.setApprovedDate(dataDto.getApprovedDate());
        List<ArticleData.DataUsage> dataUsageList = new ArrayList<>();
        if (dataDto.getDataUsage() != null) {
            dataUsageList = dataDto.getDataUsage().stream().map(
                    usage -> ArticleData.DataUsage.getUsage(usage)).collect(Collectors.toList());
        } else {
            dataUsageList.add(ArticleData.DataUsage.DESCRIBING_NEURONS);
        }
        data.setDataUsage(dataUsageList);
        return data;
    }

    public List<String> createDataUsage(List<String> dataUsageDto) {
        List<ArticleData.DataUsage> dataUsageList = dataUsageDto.stream().map(
                usage -> ArticleData.DataUsage.getUsage(usage)).collect(Collectors.toList());
        return dataUsageList.stream().map(
                usage -> usage.toString()).collect(Collectors.toList());

    }

}
