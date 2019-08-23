package com.juma.tgm.mq.domain;

import com.juma.tgm.mq.enumeration.MsgEventEnum;
import com.juma.tgm.mq.enumeration.MsgTypeEnum;
import java.util.Map;

/**
 * 消息推送
 */

public class MsgEvent {

    private MsgTypeEnum msgTypeEnum;
    private MsgEventEnum msgEventEnum;
    private String sceneKey;
    private Map<String, Object> extras;
    private String[] tos;
    private boolean isMustRead;
    private String extMsgId;
    private Integer tenantId;

    public MsgEvent() {
    }

    public MsgEvent(MsgTypeEnum msgTypeEnum, MsgEventEnum msgEventEnum, String sceneKey,
        Map<String, Object> extras, String[] tos) {
        this.msgTypeEnum = msgTypeEnum;
        this.msgEventEnum = msgEventEnum;
        this.sceneKey = sceneKey;
        this.extras = extras;
        this.tos = tos;
    }

    public MsgTypeEnum getMsgTypeEnum() {
        return msgTypeEnum;
    }

    public void setMsgTypeEnum(MsgTypeEnum msgTypeEnum) {
        this.msgTypeEnum = msgTypeEnum;
    }

    public MsgEventEnum getMsgEventEnum() {
        return msgEventEnum;
    }

    public void setMsgEventEnum(MsgEventEnum msgEventEnum) {
        this.msgEventEnum = msgEventEnum;
    }

    public String getSceneKey() {
        return sceneKey;
    }

    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    public String[] getTos() {
        return tos;
    }

    public void setTos(String[] tos) {
        this.tos = tos;
    }

    public boolean isMustRead() {
        return isMustRead;
    }

    public void setMustRead(boolean mustRead) {
        isMustRead = mustRead;
    }

    public String getExtMsgId() {
        return extMsgId;
    }

    public void setExtMsgId(String extMsgId) {
        this.extMsgId = extMsgId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}