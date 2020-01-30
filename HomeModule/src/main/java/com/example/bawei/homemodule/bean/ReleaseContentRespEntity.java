package com.example.bawei.homemodule.bean;


import java.io.Serializable;

/***
 * 朋友圈文本内容分
 */
public class ReleaseContentRespEntity implements Serializable {


    /**
     * id : 1
     * newscode : ad1b13a5f16045bcbd01fd8cf992b41e
     * userid : 1
     * content : 123123
     * imgs : sample string 1|sample string 2
     * ctime : 2020/1/19 13:27:53
     */

    private int id;
    private String newscode;
    private int userid;
    private String content;
    private String imgs;
    private String ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewscode() {
        return newscode;
    }

    public void setNewscode(String newscode) {
        this.newscode = newscode;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
