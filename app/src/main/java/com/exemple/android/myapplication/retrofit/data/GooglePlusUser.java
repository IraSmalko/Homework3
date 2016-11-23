package com.exemple.android.myapplication.retrofit.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GooglePlusUser {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("objectType")
    @Expose
    private String objectType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("isPlusUser")
    @Expose
    private Boolean isPlusUser;
    @SerializedName("circledByCount")
    @Expose
    private Integer circledByCount;
    @SerializedName("verified")
    @Expose
    private Boolean verified;

    /**
     *
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     *     The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     *
     * @param etag
     *     The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     *
     * @return
     *     The objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     *
     * @param objectType
     *     The objectType
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     *     The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     *     The name
     */
    public Name getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     *     The image
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     *
     * @return
     *     The isPlusUser
     */
    public Boolean getIsPlusUser() {
        return isPlusUser;
    }

    /**
     *
     * @param isPlusUser
     *     The isPlusUser
     */
    public void setIsPlusUser(Boolean isPlusUser) {
        this.isPlusUser = isPlusUser;
    }

    /**
     *
     * @return
     *     The circledByCount
     */
    public Integer getCircledByCount() {
        return circledByCount;
    }

    /**
     *
     * @param circledByCount
     *     The circledByCount
     */
    public void setCircledByCount(Integer circledByCount) {
        this.circledByCount = circledByCount;
    }

    /**
     *
     * @return
     *     The verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     *
     * @param verified
     *     The verified
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}
