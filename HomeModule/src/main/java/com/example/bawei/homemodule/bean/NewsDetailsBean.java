package com.example.bawei.homemodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/19 20:48
 * @Email 1151403054@qq.com
 */
public class NewsDetailsBean {

    /**
     * id : 10244
     * title : 《全面战争：三国》袁术双传奇难度演义模式，一波细节带你飞
     * content :
     * url : http://api.city2sky.cn:8088/info/2fd09e02a44311e9a0e2fc7774fd634d.html
     * newscode : 2fd09e02a44311e9a0e2fc7774fd634d
     * auth : NGA玩家社区
     * publishtime : 2019-07-09 16:29:05
     * isstaticpage : 1
     */

    private int id;
    private String title;
    private String content;
    private String url;
    private String newscode;
    private String auth;
    private String publishtime;
    private String isstaticpage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNewscode() {
        return newscode;
    }

    public void setNewscode(String newscode) {
        this.newscode = newscode;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getIsstaticpage() {
        return isstaticpage;
    }

    public void setIsstaticpage(String isstaticpage) {
        this.isstaticpage = isstaticpage;
    }
}
