package kinema.fiek.entity;

public class Table {


    private String userName;
    private String numriKinos;
    private String kinoName;
    private String numriKarriges;
    private String kohaFilmit;
    private String oraFilmit;
    private String dataRezervimit;


    public Table(String userName,
                 String numriKinos, String kinoName, String numriKarriges,
                 String kohaFilmit, String oraFilmit, String dataRezervimit) {
        this.userName = userName;
        this.numriKinos = numriKinos;
        this.kinoName = kinoName;
        this.numriKarriges = numriKarriges;
        this.kohaFilmit = kohaFilmit;
        this.oraFilmit = oraFilmit;
        this.dataRezervimit = dataRezervimit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumriKinos() {
        return numriKinos;
    }

    public void setNumriKinos(String numriKinos) {
        this.numriKinos = numriKinos;
    }

    public String getKinoName() {
        return kinoName;
    }

    public void setKinoName(String kinoName) {
        this.kinoName = kinoName;
    }

    public String getNumriKarriges() {
        return numriKarriges;
    }

    public void setNumriKarriges(String numriKarriges) {
        this.numriKarriges = numriKarriges;
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

    public String getDataRezervimit() {
        return dataRezervimit;
    }

    public void setDataRezervimit(String dataRezervimit) {
        this.dataRezervimit = dataRezervimit;
    }
}
