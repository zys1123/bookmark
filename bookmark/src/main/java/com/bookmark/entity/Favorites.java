package com.bookmark.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Favorites {
    private Long id;

    private Long count;

    private Long createTime;

    private Long lastModifyTime;

    @NotEmpty
    private String name;

    private Long publicCount;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPublicCount() {
        return publicCount;
    }

    public void setPublicCount(Long publicCount) {
        this.publicCount = publicCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}