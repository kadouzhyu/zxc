package zhibi.cms.domain;

import java.util.Date;

public class SysWebsite {
    private String siteid;

    private String sitename;

    private String sitedomain;

    private String sitebeian;

    private Integer sitestatus;

    private String ftlloc;

    private String outputloc;

    private String nickname;

    private String logo;

    private String slogan;

    private String tel;

    private String info;

    private Date addtime;

    private String adr;

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid == null ? null : siteid.trim();
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename == null ? null : sitename.trim();
    }

    public String getSitedomain() {
        return sitedomain;
    }

    public void setSitedomain(String sitedomain) {
        this.sitedomain = sitedomain == null ? null : sitedomain.trim();
    }

    public String getSitebeian() {
        return sitebeian;
    }

    public void setSitebeian(String sitebeian) {
        this.sitebeian = sitebeian == null ? null : sitebeian.trim();
    }

    public Integer getSitestatus() {
        return sitestatus;
    }

    public void setSitestatus(Integer sitestatus) {
        this.sitestatus = sitestatus;
    }

    public String getFtlloc() {
        return ftlloc;
    }

    public void setFtlloc(String ftlloc) {
        this.ftlloc = ftlloc == null ? null : ftlloc.trim();
    }

    public String getOutputloc() {
        return outputloc;
    }

    public void setOutputloc(String outputloc) {
        this.outputloc = outputloc == null ? null : outputloc.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr == null ? null : adr.trim();
    }
}