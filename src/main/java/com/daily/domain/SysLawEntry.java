package com.daily.domain;

import java.util.Date;

public class SysLawEntry
{
    private String guid;
    private String discipline  = "";
    private String chapter  = "";
    private String content  = "";
    private Date createDate;
    private String konw  = "";
    private String aspect  = "";
    private String supplement  = "";

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKonw() {
        return konw;
    }

    public void setKonw(String konw) {
        this.konw = konw;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGuid() {
        return guid;
    }
}
