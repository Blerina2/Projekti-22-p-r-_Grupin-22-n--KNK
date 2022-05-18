package kinema.fiek.entity;

public enum UserRole {
    SYSTEMMANGER(1), ADMINSTRATORR(2), KLIENTI(3);

    private int userRole;

    UserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getUserRole() {
        return userRole;
    }

    public static UserRole getRole(int i) {
        if (SYSTEMMANGER.userRole == i) {
            return SYSTEMMANGER;
        } else if (ADMINSTRATORR.userRole == i) {
            return ADMINSTRATORR;
        } else if (KLIENTI.userRole == i) {
            return KLIENTI;
        }
        return null;
    }

    public static boolean isSystemmanger(UserRole userRole) {
        return SYSTEMMANGER.equals(userRole) ? true : false;
    }

    public static boolean isAdminstrator(UserRole userRole) {
        return ADMINSTRATORR.equals(userRole) ? true : false;
    }

    public static boolean isKlienti(UserRole userRole) {
        return KLIENTI.equals(userRole) ? true : false;
    }
}