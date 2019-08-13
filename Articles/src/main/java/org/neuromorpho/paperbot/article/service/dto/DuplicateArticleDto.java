/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.service.dto;



public class DuplicateArticleDto {
    
    private String title1;
    private String title2;
    private Float distance;

    public DuplicateArticleDto(String title1, String title2, Float distance) {
        this.title1 = title1;
        this.title2 = title2;
        this.distance = distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public Float getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "DuplicateArticleDto{" +
                "title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", distance=" + distance +
                '}';
    }
}
