package kinema.fiek.ndihmes;

import kinema.fiek.entity.Karriage;
import kinema.fiek.entity.Kino;
import kinema.fiek.entity.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserNdihmes {
    /**
     * @param objects qe perban reshtat e tables users
     * @return list me objekti te users.
     */
    public static List<kinema.fiek.entity.User> convertoNeObjekteTeUser(List<Object[]> objects) {
        List<kinema.fiek.entity.User> users = new ArrayList<>();

        for (Object[] object : objects) {
            kinema.fiek.entity.User user = new User();
            user.setUserId((int) object[0]);
            user.setFirstName(((String) object[1]));
            user.setLastName((String) object[2]);
            user.setEmail((String) object[3]);
            user.setUserName((String) object[4]);
            //user.setPassword((String) object[5]);
            user.setUserRole(kinema.fiek.entity.UserRole.getRole((Integer) object[6]));
            // shto User ne list
            users.add(user);
        }
        // ne fund sortoj permese emrit (a-z)
        return users.stream().sorted(Comparator.comparing(User::getFirstName)).collect(Collectors.toList());
    }

    public static Rezervim converToRezervimetObjekt(List<Object[]> objects) {
        Rezervim rezervim = new Rezervim();
        rezervim.setUserId(Session.getUser().getUserId());
        for (Object[] object : objects) {
            Karriage karriage = new Karriage();
            karriage.setUserId(kinema.fiek.ndihmes.Session.getUser().getUserId());
            karriage.setNumriKarriges((String) object[0]);
            karriage.setDataRezervimit((String) object[1]);
            karriage.setKohaFilmit((String) object[2]);
            karriage.setOraFilmit((String) object[3]);
            rezervim.setKarriage(karriage);

            Kino kino = new Kino();
            kino.setKinoName((String) object[4]);
            kino.setKinoID((int) object[5]);
            rezervim.setKino(kino);
        }
        return rezervim;
    }

}
