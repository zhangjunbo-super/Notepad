package com.zjb.notepad;

public class Inf_list {
    private String inf_id;
    private String inf_title;
    private String inf_inf;
    private String inf_date;
    private String group_id;
    public Inf_list(String i_id,String title,String inf,String date,String g_id){
        this.inf_id=i_id;
        this.inf_title=title;
        this.inf_inf=inf;
        this.inf_date=date;
        this.group_id=g_id;
    }
    public String getGroup_id() {
        return group_id;
    }
    public String getInf_date() {
        return inf_date;
    }
    public String getInf_id() {
        return inf_id;
    }
    public String getInf_inf() {
        return inf_inf;
    }
    public String getInf_title() {
        return inf_title;
    }
}
