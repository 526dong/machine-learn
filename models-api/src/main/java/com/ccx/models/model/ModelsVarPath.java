package com.ccx.models.model;

public class ModelsVarPath {
    private Integer id;

    private Long dataFileId;

    private String htmlUrl;

    private String ivUrl;

    public ModelsVarPath() {
    }

    public ModelsVarPath(Integer id, Long dataFileId, String htmlUrl,String ivUrl) {
        this.id = id;
        this.dataFileId = dataFileId;
        this.htmlUrl = htmlUrl;
        this.ivUrl=ivUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDataFileId() {
        return dataFileId;
    }

    public void setDataFileId(Long dataFileId) {
        this.dataFileId = dataFileId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl == null ? null : htmlUrl.trim();
    }

    public String getIvUrl() {
        return ivUrl;
    }

    public void setIvUrl(String ivUrl) {
        this.ivUrl = ivUrl;
    }
}