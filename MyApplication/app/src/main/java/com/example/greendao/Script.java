package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Game story script entity
 */
@Entity
public class Script {
    @Property(nameInDb = "ID")
    private int ID;
    @Property(nameInDb = "Role")
    private String role;
    @Property(nameInDb = "Content")
    private String content;
    @Property(nameInDb = "Action1")
    private String action1;
    @Property(nameInDb = "Result1")
    private String result1;
    @Property(nameInDb = "Action2")
    private String action2;
    @Property(nameInDb = "Result2")
    private String result2;
    @Property(nameInDb = "Light")
    private String light;
    @Generated(hash = 216319855)
    public Script(int ID, String role, String content, String action1,
            String result1, String action2, String result2, String light) {
        this.ID = ID;
        this.role = role;
        this.content = content;
        this.action1 = action1;
        this.result1 = result1;
        this.action2 = action2;
        this.result2 = result2;
        this.light = light;
    }
    @Generated(hash = 231174866)
    public Script() {
    }
    public int getID() {
        return this.ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getRole() {
        return this.role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAction1() {
        return this.action1;
    }
    public void setAction1(String action1) {
        this.action1 = action1;
    }
    public String getResult1() {
        return this.result1;
    }
    public void setResult1(String result1) {
        this.result1 = result1;
    }
    public String getAction2() {
        return this.action2;
    }
    public void setAction2(String action2) {
        this.action2 = action2;
    }
    public String getResult2() {
        return this.result2;
    }
    public void setResult2(String result2) {
        this.result2 = result2;
    }
    public String getLight() {
        return this.light;
    }
    public void setLight(String light) {
        this.light = light;
    }

}
