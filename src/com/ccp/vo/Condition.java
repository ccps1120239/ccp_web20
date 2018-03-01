package com.ccp.vo;

/**
 * vo的意思就是value object
 * 主要用于封装页面参数的实体对象。
 * 因为页面参数对象不一定与数据库的完全对应，
 * 因此创建一个vo包下的实体对象专门用于封装页面传递过来的复杂参数整合成一个实体对象进行封装到后台传递，
 * 再进行取出处理查询和更新操作。
 */
public class Condition {

    private String pname;
    private String isHot;
    private String cid;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
