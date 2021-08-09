package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotSceneUserRelation;

/**
 * @类:IotSceneUserRelation
 * @作者:chenrj
 */

public class IotSceneUserRelationBO extends IotSceneUserRelation {

    public IotSceneUserRelationBO(Integer id) {

        super();
        this.setId(id);
    }

    public IotSceneUserRelationBO() {
    }

    private String name;

    private String ids;

    private String userKey;

    private String user_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

