package commentTools;

public class Comment {

    private int id;
    private String name; //评论者
    private String content; //评论内容
    private String like_num; //点赞数
    private String date; //日期
    private String parentId; //是否是回复别人 是则代表楼层数 不是则为0
    private String imageURL;


    public Comment(){

    }

    public Comment(int id, String name, String content, String like_num, String date, String parentId, String imageURL){
        this.id = id;
        this.name = name;
        this.content = content;
        this.like_num = like_num;
        this.date = date;
        this.parentId = parentId;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}