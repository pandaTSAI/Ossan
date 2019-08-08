package com.example.ossan.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

public class OssanBean implements Serializable {

    private Integer ossanNo;
    private String email;
    private String password;
    private String imageForAndroid;

    private String name;
//    private String uniqueId;
//    private Date birthday;
    private String phone;
    private Timestamp registerTime;
//    private String city;
//    private String district;
    private String address;


    private String nickname;
//    private Blob ossanImage;
//    private String fileName;

//    private String quote;
//    private Clob intro;
//    private String sIntro;

//    private boolean twNorth;
//    private boolean twMiddle;
//    private boolean twSouth;
//    private boolean twOther;

    private boolean isFBLogin;
    private boolean isGoogleLogin;

//    private String privilege;


//    private Set<ArticleBean> articleBean;





//    public Set<ArticleBean> getArticleBean() {
//        return articleBean;
//    }

//    public void setArticleBean(Set<ArticleBean> articleBean) {
//        this.articleBean = articleBean;
//    }


    public OssanBean(){

    }

    public OssanBean(Integer ossanNo, String email, String password, String name, String uniqueId, Date birthday,
                     String phone, String city, String district, String address, String nickname, Blob ossanImage,
                     String fileName,String quote,String imageForAndroid, Clob intro,boolean isFBLogin,boolean isGoogleLogin) {

        this.ossanNo = ossanNo;
        this.email = email;
        this.password = password;
        this.name = name;
//        this.uniqueId = uniqueId;
//        this.birthday = birthday;
        this.phone = phone;
//        this.city = city;
//        this.district = district;
        this.address = address;
        this.nickname = nickname;
//        this.ossanImage = ossanImage;
//        this.fileName = fileName;
//        this.quote = quote;
//        this.intro = intro;
        this.imageForAndroid = imageForAndroid;
        this.isFBLogin = isFBLogin;
        this.isGoogleLogin = isGoogleLogin;
    }

    public Integer getOssanNo() {
        return ossanNo;
    }

    public void setOssanNo(Integer ossanNo) {
        this.ossanNo = ossanNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getUniqueId() {
//        return uniqueId;
//    }

//    public void setUniqueId(String uniqueId) {
//        this.uniqueId = uniqueId;
//    }

//    public Date getBirthday() {
//        return birthday;
//    }

//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

//    public String getCity() {
//        return city;
//    }

//    public void setCity(String city) {
//        this.city = city;
//    }

//    public String getDistrict() {
//        return district;
//    }

//    public void setDistrict(String district) {
//        this.district = district;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public Blob getOssanImage() {
//        return ossanImage;
//    }
//
//    public void setOssanImage(Blob ossanImage) {
//        this.ossanImage = ossanImage;
//    }

//    public String getFileName() {
//        return fileName;
//    }

//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

//    public String getQuote() {
//        return quote;
//    }

//    public void setQuote(String quote) {
//        this.quote = quote;
//    }

//    public Clob getIntro() {
//        return intro;
//    }

//    public void setIntro(Clob intro) {
//        this.intro = intro;
//    }

//    public String getsIntro() {
//        return sIntro;
//    }

//    public void setsIntro(String sIntro) {
//        this.sIntro = sIntro;
//    }

//    public boolean isTwNorth() {
////        return twNorth;
////    }

//    public void setTwNorth(boolean twNorth) {
//        this.twNorth = twNorth;
//    }

//    public boolean isTwMiddle() {
//        return twMiddle;
//    }

//    public void setTwMiddle(boolean twMiddle) {
//        this.twMiddle = twMiddle;
//    }

//    public boolean isTwSouth() {
//        return twSouth;
//    }

//    public void setTwSouth(boolean twSouth) {
//        this.twSouth = twSouth;
//    }

//    public boolean isTwOther() {
//        return twOther;
//    }

//    public void setTwOther(boolean twOther) {
//        this.twOther = twOther;
//    }

//    public String getPrivilege() {
//        return privilege;
//    }

//    public void setPrivilege(String privilege) {
//        this.privilege = privilege;
//    }

    public String getImageForAndroid() {
        return imageForAndroid;
    }

    public void setImageForAndroid(String imageForAndroid) {
        this.imageForAndroid = imageForAndroid;
    }

    public boolean isFBLogin() {
        return isFBLogin;
    }

    public void setFBLogin(boolean FBLogin) {
        isFBLogin = FBLogin;
    }

    public boolean isGoogleLogin() {
        return isGoogleLogin;
    }

    public void setGoogleLogin(boolean googleLogin) {
        isGoogleLogin = googleLogin;
    }
}
