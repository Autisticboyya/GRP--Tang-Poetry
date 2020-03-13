package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Author {
    @Property(nameInDb = "authorNameEnglish")
    private String authorNameEnglish;

    @Property(nameInDb = "authorBriefIntroduction")
    private String authorBriefIntroduction;

    @Generated(hash = 370921094)
    public Author(String authorNameEnglish, String authorBriefIntroduction) {
        this.authorNameEnglish = authorNameEnglish;
        this.authorBriefIntroduction = authorBriefIntroduction;
    }

    @Generated(hash = 64241762)
    public Author() {
    }

    public String getAuthorNameEnglish() {
        return this.authorNameEnglish;
    }

    public void setAuthorNameEnglish(String authorNameEnglish) {
        this.authorNameEnglish = authorNameEnglish;
    }

    public String getAuthorBriefIntroduction() {
        return this.authorBriefIntroduction;
    }

    public void setAuthorBriefIntroduction(String authorBriefIntroduction) {
        this.authorBriefIntroduction = authorBriefIntroduction;
    }
}
