package fiek.bazaDhenave.entity;

public class Karriage {

    private String numriKarriges;
    private int userId;
    private String dataRezervimit;
    private String kohaFilmit;
    private String oraFilmit;
    private int kinoId;

    public String getNumriKarriges() {
        return numriKarriges;
    }

    public void setNumriKarriges(String numriKarriges) {
        this.numriKarriges = numriKarriges;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDataRezervimit() {
        return dataRezervimit;
    }

    public void setDataRezervimit(String dataRezervimit) {
        this.dataRezervimit = dataRezervimit;
    }

    public String getKohaFilmit() {
        return kohaFilmit;
    }

    public void setKohaFilmit(String kohaFilmit) {
        this.kohaFilmit = kohaFilmit;
    }

    public String getOraFilmit() {
        return oraFilmit;
    }

    public void setOraFilmit(String oraFilmit) {
        this.oraFilmit = oraFilmit;
    }

    public int getKinoId() {
        return kinoId;
    }

    public void setKinoId(int kinoId) {
        this.kinoId = kinoId;
    }
}