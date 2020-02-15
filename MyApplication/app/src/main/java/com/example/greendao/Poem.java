package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;


@Entity
public class Poem {
    @Id
    private Long id;
    @Property(nameInDb = "poemName")
    private String poemName;
    @Property(nameInDb = "authorName")
    private String authorName;
    @Property(nameInDb = "chineseVersion")
    private String chineseVersion;
    @Property(nameInDb = "EnglishVersion")
    private String EnglishVersion;
    @Generated(hash = 168613105)
    public Poem(Long id, String poemName, String authorName, String chineseVersion,
            String EnglishVersion) {
        this.id = id;
        this.poemName = poemName;
        this.authorName = authorName;
        this.chineseVersion = chineseVersion;
        this.EnglishVersion = EnglishVersion;
    }
    @Generated(hash = 1852989059)
    public Poem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPoemName() {
        return this.poemName;
    }
    public void setPoemName(String poemName) {
        this.poemName = poemName;
    }
    public String getAuthorName() {
        return this.authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getChineseVersion() {
        return this.chineseVersion;
    }
    public void setChineseVersion(String chineseVersion) {
        this.chineseVersion = chineseVersion;
    }
    public String getEnglishVersion() {
        return this.EnglishVersion;
    }
    public void setEnglishVersion(String EnglishVersion) {
        this.EnglishVersion = EnglishVersion;
    }

}
