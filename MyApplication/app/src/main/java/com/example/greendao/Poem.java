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
    @Generated(hash = 1070710514)
    public Poem(Long id, String poemName, String authorName) {
        this.id = id;
        this.poemName = poemName;
        this.authorName = authorName;
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

}
