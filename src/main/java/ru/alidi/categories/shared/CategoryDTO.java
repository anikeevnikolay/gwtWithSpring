package ru.alidi.categories.shared;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Long id;
    private String description;
    private String imageId;
    private String name;
    private CategoryDTO parent;
    private Boolean published;
    private String url;
    private Boolean active;
    private Integer orderfield;
    private Long articleId;
    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;
    private String previewId;
    private CategoryDTO[] subcategories = {};

    public CategoryDTO() {}

    public CategoryDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getParent() {
        return parent;
    }

    public void setParent(CategoryDTO parent) {
        this.parent = parent;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getOrderfield() {
        return orderfield;
    }

    public void setOrderfield(Integer orderfield) {
        this.orderfield = orderfield;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getPreviewId() {
        return previewId;
    }

    public void setPreviewId(String previewId) {
        this.previewId = previewId;
    }

    public CategoryDTO[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(CategoryDTO[] subcategories) {
        this.subcategories = subcategories;
    }
}
