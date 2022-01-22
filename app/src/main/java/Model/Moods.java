package Model;

public class Moods {

    private String MoodsTitle;
    private String Description;
    private int Thumbnail;

    public Moods(String moodsTitle, String description, int thumbnail) {
        MoodsTitle = moodsTitle;
        Description = description;
        Thumbnail = thumbnail;
    }

    public String getMoodsTitle() {
        return MoodsTitle;
    }

    public void setMoodsTitle(String moodsTitle) {
        MoodsTitle = moodsTitle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
