package swasthyalehar.swasthya;

/**
 * Created by itstym on 15/8/16.
 */
public class LeaderboardProfile {

    private String name;
    private int creditPoint;
    private int thumbnail;

    public LeaderboardProfile(){

    }

    public LeaderboardProfile(String name, int creditPoint, int thumbnail) {
        this.name = name;
        this.creditPoint = creditPoint;
        this.thumbnail=thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(int creditPoint) {
        this.creditPoint = creditPoint;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }


}
