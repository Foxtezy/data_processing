/* https://drive.google.com/file/d/12xw2n_P_iaZWtuUctIowT7ZcaOn2UlEc/view?usp=sharing */

CREATE TABLE PERSONS (
                       ID TEXT UNIQUE PRIMARY KEY NOT NULL,
                       NAME VARCHAR(50),
                       SURNAME VARCHAR(50),
                       GENDER TEXT,
                       MOTHER_ID TEXT REFERENCES PERSONS(ID),
                       FATHER_ID TEXT REFERENCES PERSONS(ID),
                       WIFE_ID TEXT REFERENCES PERSONS(ID),
                       HUSBAND_ID TEXT REFERENCES PERSONS(ID)
);

CREATE TABLE SONS (
                            SON_ID TEXT REFERENCES PERSONS(ID),
                            PARENT_ID TEXT REFERENCES PERSONS(ID)
);

CREATE TABLE DAUGHTERS (
                                 DAUGHTER_ID TEXT REFERENCES PERSONS(ID),
                                 PARENT_ID TEXT REFERENCES PERSONS(ID)
);

CREATE TABLE BROTHERS (
                                BROTHER_ID TEXT REFERENCES PERSONS(ID),
                                PERSON_ID TEXT REFERENCES PERSONS(ID)
);

CREATE TABLE SISTERS (
                              SISTER_ID TEXT REFERENCES PERSONS(ID),
                              PERSON_ID TEXT REFERENCES PERSONS(ID)
);