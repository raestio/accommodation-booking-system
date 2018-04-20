DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE ADDRESS (
  id      SERIAL  PRIMARY KEY   NOT NULL,
  city    TEXT                  NOT NULL,
  country TEXT                  NOT NULL,
  street  TEXT                  NOT NULL,
  UNIQUE (city, country, street)
);

CREATE TABLE ACCOMMODATION_TYPE (
  id    SERIAL  PRIMARY KEY   NOT NULL,
  name  TEXT    UNIQUE        NOT NULL
);

CREATE TABLE ACCOMMODATION (
  id                          INT    PRIMARY KEY                          NOT NULL,
  name                        TEXT                                        NOT NULL,
  pricePerNight               NUMERIC(12, 2)                              NOT NULL,
  guests                      INT                                         NOT NULL,
  beds                        INT                                         NOT NULL,
  bathrooms                   INT                                         NOT NULL,
  fk_accommodation_type_id    INT    REFERENCES ACCOMMODATION_TYPE(id)    NOT NULL,
  fk_address_id               INT    REFERENCES ADDRESS(id)               NOT NULL
);

CREATE TABLE PHOTO (
  id                  SERIAL  PRIMARY KEY                   NOT NULL,
  filePath            TEXT    UNIQUE                        NOT NULL,
  fk_accommodation_id INT     REFERENCES ACCOMMODATION(id)  NOT NULL
);

CREATE TABLE CUSTOMER (
  id      SERIAL PRIMARY KEY    NOT NULL,
  name    TEXT                  NOT NULL,
  surname TEXT                  NOT NULL,
  email   TEXT   UNIQUE         NOT NULL
);

CREATE TABLE RESERVATION (
  dateFrom            DATE                                NOT NULL,
  dateTo              DATE                                NOT NULL,
  fk_accommodation_id INT   REFERENCES ACCOMMODATION(id)  NOT NULL,
  fk_customer_id      INT   REFERENCES CUSTOMER(id)       NOT NULL,
  PRIMARY KEY (dateFrom, dateTo, fk_accommodation_id)
);


