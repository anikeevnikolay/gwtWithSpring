package ru.alidi.categories.shared;

import java.io.Serializable;
import java.util.Date;

public class ItemDTO implements Serializable {

    private Long id;
    private Boolean blocked;
    private Date createDate;
    private String eanCode;
    private String name;
    private Double price;
    private Double priceWithDiscount;
    private Date publishDate;
    private String stockStatus;
    private String systemCode;
    private Date updateDate;
    private String url;
    private Double volume;
    private Double weight;
    private Long itemMeasureId;
    private Integer fold;
    private Boolean active;
    private Integer orderfield;
    private Boolean topMain;
    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;
    private String consistsOf;
    private String[] images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getItemMeasureId() {
        return itemMeasureId;
    }

    public void setItemMeasureId(Long itemMeasureId) {
        this.itemMeasureId = itemMeasureId;
    }

    public Integer getFold() {
        return fold;
    }

    public void setFold(Integer fold) {
        this.fold = fold;
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

    public Boolean getTopMain() {
        return topMain;
    }

    public void setTopMain(Boolean topMain) {
        this.topMain = topMain;
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

    public String getConsistsOf() {
        return consistsOf;
    }

    public void setConsistsOf(String consistsOf) {
        this.consistsOf = consistsOf;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
