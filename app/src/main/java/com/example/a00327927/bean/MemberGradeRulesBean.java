package com.example.a00327927.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chemin on 2018/4/13 18:40.
 * description：
 */

public class MemberGradeRulesBean implements Serializable{
    private String performanceTotal;
    private String invitationNumTotal;

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    private int currentValue;
    private List<MemberBean> memberBeenList;

    public String getPerformanceTotal() {
        return performanceTotal;
    }

    public void setPerformanceTotal(String performanceTotal) {
        this.performanceTotal = performanceTotal;
    }

    public String getInvitationNumTotal() {
        return invitationNumTotal;
    }

    public void setInvitationNumTotal(String invitationNumTotal) {
        this.invitationNumTotal = invitationNumTotal;
    }

    public List<MemberBean> getMemberBeenList() {
        return memberBeenList;
    }

    public void setMemberBeenList(List<MemberBean> memberBeenList) {
        this.memberBeenList = memberBeenList;
    }
}
