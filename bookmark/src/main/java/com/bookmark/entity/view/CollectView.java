package com.bookmark.entity.view;

import com.bookmark.entity.DatabaseCollect;
import com.bookmark.utils.DateFormatUtils;

/**
 * Created by 12425 on 2018/8/14.
 */
public class CollectView {
    //文章id
    private Long id;
    //用户id
    private Long userId;
    //用户名
    private String userName;
    //用户头像
    private String profilePicture;
    //文章标题
    private String title;
    //文章url
    private String url;
    //文章描述
    private String description;
    //文章所属类型
    private String type;
    //收藏夹Id
    private Long favoritesId;
    //收藏夹名称
    private String favoritesName;
    //点赞数量
    private int praiseCount;
    //评论数量
    private int commentCount;
    //该用户是否对本文章点过赞
    private boolean isPraise;
    //创建日期
    private String collectTime;
    //备注信息
    private String remark;

    public CollectView(Long id, Long userId, String userName, String profilePicture, String title, String url,
                       String description,String type, Long createTime, String remark, Long favoritesId, String favoritesName,
                       int praiseCount, int commentCount, boolean isPraise) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.profilePicture = profilePicture;
        this.title = title;
        this.url = url;
        this.description = description;
        this.type = type;
        this.favoritesId = favoritesId;
        this.favoritesName = favoritesName;
        this.praiseCount = praiseCount;
        this.commentCount = commentCount;
        this.isPraise = isPraise;
        this.collectTime = DateFormatUtils.getStringByLongDate(createTime);
        this.remark =remark;
    }

    public CollectView(DatabaseCollect databaseCollect, int praiseCount, int commentCount, boolean isPraise){
        this.id = databaseCollect.getId();
        this.userId = databaseCollect.getUser().getId();
        this.userName = databaseCollect.getUser().getUserName();
        this.profilePicture = databaseCollect.getUser().getProfilePicture();
        this.title = databaseCollect.getTitle();
        this.url = databaseCollect.getUrl();
        this.description = databaseCollect.getDescription();
        this.type = databaseCollect.getType();
        this.favoritesId = databaseCollect.getFavorites().getId();
        this.favoritesName = databaseCollect.getFavorites().getName();
        this.praiseCount = praiseCount;
        this.commentCount = commentCount;
        this.isPraise = isPraise;
        this.collectTime = DateFormatUtils.getStringByLongDate(databaseCollect.getCreateTime());
        this.remark =databaseCollect.getRemark();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Long favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getFavoritesName() {
        return favoritesName;
    }

    public void setFavoritesName(String favoritesName) {
        this.favoritesName = favoritesName;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
