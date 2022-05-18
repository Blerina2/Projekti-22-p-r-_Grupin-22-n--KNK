CREATE TABLE USERS
(
    ID         int auto_increment,
    FIRST_NAME CHAR(25) NOT NULL,
    LAST_NAME  CHAR(25) NOT NULL,
    EMAIL      CHAR(45) NOT NULL,
    USER_NAME  CHAR(25) NOT NULL,
    PASSWORD   CHAR(64) NOT NULL,
    USER_ROLE  int      NOT NULL,
    primary key (ID)
)