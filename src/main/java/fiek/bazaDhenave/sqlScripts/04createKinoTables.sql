CREATE TABLE Kino
(
    ID                   int PRIMARY KEY,
    KINO_NAME            CHAR(255),
    SASIA_KARRIAGEVE     int          NOT NULL DEFAULT 45,
    KARRIGE_LIST         varchar(400) NOT NULL DEFAULT '',
    KARRIAGEVE_RESERVUAR int          NOT NULL DEFAULT 0
);


CREATE TABLE KARRIGE
(
    NUMRI_KARRIGES  CHAR(25),
    USER_ID         int,
    DATA_REZERVIMIT CHAR(50),
    KOHA_FILMIT     CHAR(50),
    ORA_FILMIT      CHAR(50),
    KINO_ID         int,
    PRIMARY KEY (NUMRI_KARRIGES, KINO_ID),
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
    FOREIGN KEY (KINO_Id) REFERENCES KINO (ID)
);


CREATE TABLE Movies
(
    ID         int auto_increment,
    TITLE      TEXT     NOT NULL,
    PERSHKRIMI TEXT     NOT NULL,
    TRAILER    TEXT     NOT NULL,
    STARTDATE  CHAR(25) NOT NULL,
    ENDATE     CHAR(25) NOT NULL,
    TIME1      CHAR(25) NOT NULL,
    TIME2      CHAR(25) NOT NULL,
    IMAGE      LONGBLOB NOT NULL,
    KINO_ID    int,
    primary key (ID),
    FOREIGN KEY (KINO_ID) REFERENCES KINO (ID)
);



select ID, KARRIGE_LIST
from Kino;


