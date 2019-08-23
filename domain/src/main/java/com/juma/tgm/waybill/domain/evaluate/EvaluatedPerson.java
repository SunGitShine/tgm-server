package com.juma.tgm.waybill.domain.evaluate;

import java.io.Serializable;

/**
 * 
 * @ClassName:   EvaluatedPerson   
 * @Description: 被评价人 
 * @author:      Administrator
 * @date:        2017年5月19日 下午6:47:06  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class EvaluatedPerson implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6816777308199002283L;

    private String nickname;
    
    private String headPortrait;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
    
}
