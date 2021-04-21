package com.howei.pojo;

/**
 * @author jayun
 *
 * 系统操作记录,用于动态区域
 *
 */
public class OperationRecord {

    private Integer id;
    private Integer sendId;
    private Integer receiveId;
    private String verb;
    private String content;
    private String type;
    private String remark;
    private String createTime;
    private Integer isRead;
    private String longTime;
    private String sendName;
    private String confirmTime;

    @Override
    public String toString() {
        return "OperationRecord{" +
                "id=" + id +
                ", sendId=" + sendId +
                ", receiveId=" + receiveId +
                ", verb='" + verb + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isRead=" + isRead +
                ", longTime='" + longTime + '\'' +
                ", sendName='" + sendName + '\'' +
                ", confirmTime='" + confirmTime + '\'' +
                '}';
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getLongTime() {
        return longTime;
    }

    public void setLongTime(String longTime) {
        this.longTime = longTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }


    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
