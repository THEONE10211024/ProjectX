package theone.medusa.pers.projectx.bean;

/**
 * Created by xiayong on 2016/1/1.
 */
public class JourneyBean {
    private String time;
    private int carTypeSrcId;
    private String state;
    private String startPlace;
    private String endPlace;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCarTypeSrcId() {
        return carTypeSrcId;
    }

    public void setCarTypeSrcId(int carTypeSrcId) {
        this.carTypeSrcId = carTypeSrcId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }
}
