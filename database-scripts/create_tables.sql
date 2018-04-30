DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE ADDRESS (
  id      BIGSERIAL  PRIMARY KEY   NOT NULL,
  city    TEXT                     NOT NULL,
  country TEXT                     NOT NULL,
  street  TEXT                     NOT NULL,
  UNIQUE (city, country, street)
);

CREATE TABLE ACCOMMODATION_TYPE (
  id    SERIAL  PRIMARY KEY   NOT NULL,
  name  TEXT    UNIQUE        NOT NULL
);

CREATE TABLE ACCOMMODATION (
  id                          BIGSERIAL    PRIMARY KEY                    NOT NULL,
  name                        TEXT                                        NOT NULL,
  price_per_night             NUMERIC(12, 2)                              NOT NULL,
  guests                      INT                                         NOT NULL,
  beds                        INT                                         NOT NULL,
  bathrooms                   INT                                         NOT NULL,
  fk_accommodation_type_id    INT    REFERENCES ACCOMMODATION_TYPE(id)    NOT NULL,
  fk_address_id               BIGINT REFERENCES ADDRESS(id)               NOT NULL
);

CREATE TABLE PHOTO (
  id                  BIGSERIAL  PRIMARY KEY                NOT NULL,
  url                 TEXT    UNIQUE                        NOT NULL,
  fk_accommodation_id BIGINT  REFERENCES ACCOMMODATION(id)  NOT NULL
);

CREATE TABLE USER_INFO (
  id       BIGSERIAL PRIMARY KEY NOT NULL,
  name     TEXT                  NOT NULL,
  surname  TEXT                  NOT NULL,
  email    TEXT      UNIQUE      NOT NULL,
  password TEXT                  NOT NULL,
  role     TEXT                  NOT NULL
);

CREATE TABLE BOOKING (
  id                   BIGSERIAL PRIMARY KEY                  NOT NULL,
  check_in             DATE                                   NOT NULL,
  check_out            DATE                                   NOT NULL,
  price                NUMERIC(12, 2)                         NOT NULL,
  fk_accommodation_id  BIGINT   REFERENCES ACCOMMODATION(id)  NOT NULL,
  fk_user_id           BIGINT   REFERENCES USER_INFO(id)      NOT NULL,
  UNIQUE (check_in, check_out, fk_accommodation_id)
);


