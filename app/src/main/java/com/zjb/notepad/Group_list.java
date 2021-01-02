package com.zjb.notepad;

public class Group_list {
    private String group_name;
    private String group_id;
    private String group_count;
    public  Group_list(String group_name,String group_id,String group_count){
        this.group_name=group_name;
        this.group_count=group_count;
        this.group_id=group_id;
    }

    public String getGroup_name(){
        return group_name;
    }
    public String getGroup_id(){
        return group_id;
    }
    public String getGroup_count(){
        return group_count;
    }
}
