package com.bookmark.entity;

import com.bookmark.valid.LoginCheck;
import com.bookmark.valid.RegisterCheck;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
    private Long id;

    private String backgroundPicture;

    private Long createTime;

    @Email(groups = {RegisterCheck.class})
    private String email;

    private Long lastModifyTime;

    private String outDate;

    @NotEmpty(groups = {RegisterCheck.class, LoginCheck.class})
    @Length(min = 5,max = 20,groups = {RegisterCheck.class, LoginCheck.class})
    private String passWord;

    private String profilePicture;

    @NotEmpty(groups = {RegisterCheck.class, LoginCheck.class})
    @Length(min = 5,max = 20,groups = {RegisterCheck.class, LoginCheck.class})
    private String userName;

    private String validataCode;

    private String introduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture == null ? null : backgroundPicture.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate == null ? null : outDate.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture == null ? null : profilePicture.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode == null ? null : validataCode.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}