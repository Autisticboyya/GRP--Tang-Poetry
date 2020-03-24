package poemListTools;


public class SortModel {

    private String poemName;
    private String writerName;
    private String sortLetter;

    public SortModel(String poemName, String writerName, String sortLetter){
        this.poemName = poemName;
        this.writerName = writerName;
        this.sortLetter = sortLetter;
    }

    public SortModel(){

    }

    public String getSortLetter() {
        return sortLetter;
    }

    public void setSortLetter(String sortLetter) {
        this.sortLetter = sortLetter;
    }

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String name) {
        this.poemName = name;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String name) {
        this.writerName = name;
    }
}