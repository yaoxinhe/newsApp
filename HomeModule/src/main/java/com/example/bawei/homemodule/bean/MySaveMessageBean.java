package com.example.bawei.homemodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/21 17:18
 * @Email 1151403054@qq.com
 */
public class MySaveMessageBean {

    /**
     * id : 1
     * content : sample string 2
     * newscode : sample string 3
     * commenttime : sample string 4
     * parentid : 5
     * userid : 6
     */

    private int id;
    private String content;
    private String newscode;
    private String commenttime;
    private int parentid;
    private int userid;

    public MySaveMessageBean(int id, String content, String newscode, String commenttime, int parentid, int userid) {
        this.id = id;
        this.content = content;
        this.newscode = newscode;
        this.commenttime = commenttime;
        this.parentid = parentid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewscode() {
        return newscode;
    }

    public void setNewscode(String newscode) {
        this.newscode = newscode;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
