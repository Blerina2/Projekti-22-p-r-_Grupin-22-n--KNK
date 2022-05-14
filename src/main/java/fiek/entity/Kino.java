package fiek.entity;

public class Kino {
    private int kinoID;
    private String kinoName;
    private String karrigeList;

    private int karriageveReservuar;


    private int sasiaKarriageve;

    public int getSasiaKarriageve() {
        return sasiaKarriageve;
    }

    public void setSasiaKarriageve(int sasiaKarriageve) {
        this.sasiaKarriageve = sasiaKarriageve;
    }

    public int getKarriageveReservuar() {
        return karriageveReservuar;
    }

    public void setKarriageveReservuar(int karriageveReservuar) {
        this.karriageveReservuar = karriageveReservuar;
    }

    public int getKinoID() {
        return kinoID;
    }

    public void setKinoID(int kinoID) {
        this.kinoID = kinoID;
    }

    public String getKinoName() {
        return kinoName;
    }

    public void setKinoName(String kinoName) {
        this.kinoName = kinoName;
    }

    public String getKarrigeList() {
        return karrigeList;
    }

    public void setKarrigeList(String karrigeList) {
        this.karrigeList = karrigeList;
    }
}