package com.example.bawei.homemodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 17:08
 * @Email 1151403054@qq.com
 */
public class NewsBean {

    /**
     * id : 12589
     * newscode : 3a671b92a44311e98161fc7774fd634d
     * newstypeid : 1
     * sourcesiteid : 1
     * sourcesitename : 今日头条
     * title : 提升游戏买量效果新“利器”：UART重磅发布
     * description : 长久以来，对于以广告变现为主的中轻度游戏如何计算准确ROI一直是困扰市场推广人员的难题——与以内购为主要营收方式的游戏不同。
     * auth : GameRes游资网
     * sourceurl : /group/6712577688856429063/
     * mainimgurl : http://p1.pstatp.com/list/pgc-image/2a9eb9ee687245c8bffbc8176b1f1dba
     * istop : 0
     */

    private int id;
    private String newscode;
    private int newstypeid;
    private int sourcesiteid;
    private String sourcesitename;
    private String title;
    private String description;
    private String auth;
    private String sourceurl;
    private String mainimgurl;
    private String istop;

    public NewsBean(int id, String newscode, int newstypeid, int sourcesiteid, String sourcesitename, String title, String description, String auth, String sourceurl, String mainimgurl, String istop) {
        this.id = id;
        this.newscode = newscode;
        this.newstypeid = newstypeid;
        this.sourcesiteid = sourcesiteid;
        this.sourcesitename = sourcesitename;
        this.title = title;
        this.description = description;
        this.auth = auth;
        this.sourceurl = sourceurl;
        this.mainimgurl = mainimgurl;
        this.istop = istop;
    }

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

    public int getNewstypeid() {
        return newstypeid;
    }

    public void setNewstypeid(int newstypeid) {
        this.newstypeid = newstypeid;
    }

    public int getSourcesiteid() {
        return sourcesiteid;
    }

    public void setSourcesiteid(int sourcesiteid) {
        this.sourcesiteid = sourcesiteid;
    }

    public String getSourcesitename() {
        return sourcesitename;
    }

    public void setSourcesitename(String sourcesitename) {
        this.sourcesitename = sourcesitename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getMainimgurl() {
        return mainimgurl;
    }

    public void setMainimgurl(String mainimgurl) {
        this.mainimgurl = mainimgurl;
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }
}
