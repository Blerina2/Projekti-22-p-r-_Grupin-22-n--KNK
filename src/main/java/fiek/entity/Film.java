package fiek.entity;

import javafx.scene.image.Image;

public class Film {

    private int id;
    private String title;
    private String pershkrimi;
    private String trailer;
    private String startdate;
    private String endate;
    private String time1;
    private String time2;
    private Image IMAGE;

    private int kinoID;

    public int getKinoID() {
        return kinoID;
    }

    public void setKinoID(int kinoID) {
        this.kinoID = kinoID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEndate() {
        return endate;
    }

    public void setEndate(String endate) {
        this.endate = endate;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public Image getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(Image IMAGE) {
        this.IMAGE = IMAGE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}