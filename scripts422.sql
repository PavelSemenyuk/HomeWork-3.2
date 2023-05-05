CREATE TABLE People
(
    ID            SERIAL      NOT NULL PRIMARY KEY,
    Name          VARCHAR(50) NOT NULL,
    Age           INTEGER     NOT NULL,
    DriverLicense BOOLEAN     NOT NULL
);
CREATE TABLE Cars
(
    ID    SERIAL      NOT NULL PRIMARY KEY,
    Make  VARCHAR(50) NOT NULL,
    Model VARCHAR(50) NOT NULL,
    Price REAL        NOT NULL
);
CREATE TABLE Owner
(
    ID       SERIAL  NOT NULL PRIMARY KEY,
    PersonID INTEGER NOT NULL,
    CarID    INTEGER NOT NULL,
    FOREIGN KEY (PersonID) REFERENCES People (ID),
    FOREIGN KEY (CarID) REFERENCES Cars (ID)
);

