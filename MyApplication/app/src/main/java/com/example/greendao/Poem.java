package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;


@Entity
public class Poem {
    @Id
    private Long id;
    @Property(nameInDb = "difficulty")
    private String difficulty;
    @Property(nameInDb = "poemName")
    private String poemName;
    @Property(nameInDb = "poemNameEnglish")
    private String webLink;
    @Property(nameInDb = "webLink")
    private String poemNameEnglish;
    @Property(nameInDb = "authorName")
    private String authorName;
    @Property(nameInDb = "authorNameEnglish")
    private String authorNameEnglish;
    @Property(nameInDb = "kindOfPoem")
    private String kindOfPoem;
    @Property(nameInDb = "chineseVersion")
    private String chineseVersion;
    @Property(nameInDb = "EnglishVersion")
    private String EnglishVersion;
    @Property(nameInDb = "poemBackground")
    private String poemBackground;
    private String poemNameHtml;
    private String authorNameHtml;

    @Generated(hash = 1729531821)
    public Poem(Long id, String difficulty, String poemName, String webLink,
            String poemNameEnglish, String authorName, String authorNameEnglish,
            String kindOfPoem, String chineseVersion, String EnglishVersion,
            String poemBackground, String poemNameHtml, String authorNameHtml) {
        this.id = id;
        this.difficulty = difficulty;
        this.poemName = poemName;
        this.webLink = webLink;
        this.poemNameEnglish = poemNameEnglish;
        this.authorName = authorName;
        this.authorNameEnglish = authorNameEnglish;
        this.kindOfPoem = kindOfPoem;
        this.chineseVersion = chineseVersion;
        this.EnglishVersion = EnglishVersion;
        this.poemBackground = poemBackground;
        this.poemNameHtml = poemNameHtml;
        this.authorNameHtml = authorNameHtml;
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
    public String getKindOfPoem() {
        return this.kindOfPoem;
    }
    public void setKindOfPoem(String kindOfPoem) {
        this.kindOfPoem = kindOfPoem;
    }
    public String getPoemNameEnglish() {
        return this.poemNameEnglish;
    }
    public void setPoemNameEnglish(String poemNameEnglish) {
        this.poemNameEnglish = poemNameEnglish;
    }
    public String getAuthorNameEnglish() {
        return this.authorNameEnglish;
    }
    public void setAuthorNameEnglish(String authorNameEnglish) {
        this.authorNameEnglish = authorNameEnglish;
    }
    public String getPoemNameHtml() {
        return this.poemNameHtml;
    }
    public void setPoemNameHtml(String poemNameHtml) {
        this.poemNameHtml = poemNameHtml;
    }
    public String getAuthorNameHtml() {
        return this.authorNameHtml;
    }
    public void setAuthorNameHtml(String authorNameHtml) {
        this.authorNameHtml = authorNameHtml;
    }
    public String getDifficulty() {
        return this.difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public String getWebLink() {
        return this.webLink;
    }
    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }
    public String getPoemBackground() {
        return this.poemBackground;
    }
    public void setPoemBackground(String poemBackground) {
        this.poemBackground = poemBackground;
    }

}
