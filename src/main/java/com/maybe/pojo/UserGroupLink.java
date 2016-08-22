package com.maybe.pojo;

import java.util.Date;

/**
 * Created by Maybe on 2016/7/21
 * Maybe has infinite possibilities
 * 描述User和Group之间的映射关系
 */
public class UserGroupLink {

    private User user;

    private Group group;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}