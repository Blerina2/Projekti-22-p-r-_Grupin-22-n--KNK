package kinema.fiek.entity;

import fiek.bazaDhenave.entity.Karriage;
import fiek.bazaDhenave.entity.Kino;

public class Rezervim {

    int userId;
    Karriage karriage;
    Kino kino;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Karriage getKarriage() {
        return karriage;
    }

    public void setKarriage(Karriage karriage) {
        this.karriage = karriage;
    }

    public Kino getKino() {
        return kino;
    }

    public void setKino(Kino kino) {
        this.kino = kino;
    }
}
