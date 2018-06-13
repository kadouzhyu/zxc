package zhibi.cms.domain;

import java.util.Date;

public class SysBbs {
    private Integer bid;

    private String bname;

    private String btelephone;

    private String bemail;

    private String sited;

    private Date addtime;

    private String bcontent;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getBtelephone() {
        return btelephone;
    }

    public void setBtelephone(String btelephone) {
        this.btelephone = btelephone == null ? null : btelephone.trim();
    }

    public String getBemail() {
        return bemail;
    }

    public void setBemail(String bemail) {
        this.bemail = bemail == null ? null : bemail.trim();
    }

    public String getSited() {
        return sited;
    }

    public void setSited(String sited) {
        this.sited = sited == null ? null : sited.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent == null ? null : bcontent.trim();
    }
}