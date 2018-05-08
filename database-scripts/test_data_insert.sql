INSERT INTO address (city, country, street)
    VALUES ('Split', 'Croatia', 'Testing 23');

INSERT INTO accommodation (name, price_per_night, guests, beds, bathrooms, fk_accommodation_type_id, fk_address_id)
    VALUES ('Nice apartment with a view', 1600, 4, 3, 1, 1, 1);

INSERT INTO photo (url, fk_accommodation_id)
    VALUES ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681770/test_a_1.webp', 1),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_2.webp', 1),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_3.webp', 1),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_4.webp', 1),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_5.webp', 1);

INSERT INTO address (city, country, street)
    VALUES ('Testcity', 'Cyprus', 'Testing street 1');

INSERT INTO accommodation (name, price_per_night, guests, beds, bathrooms, fk_accommodation_type_id, fk_address_id)
    VALUES ('Luxury villa', 1945, 5, 4, 2, 8, 2);

INSERT INTO photo (url, fk_accommodation_id)
    VALUES ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_1.webp', 2),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_2.webp', 2),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_3.webp', 2),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_4.webp', 2);

INSERT INTO address (city, country, street)
    VALUES ('Testcity', 'Cyprus', 'Testing street 3');

INSERT INTO accommodation (name, price_per_night, guests, beds, bathrooms, fk_accommodation_type_id, fk_address_id)
    VALUES ('Beach apartment', 1051, 4, 2, 1, 1, 3);

INSERT INTO photo (url, fk_accommodation_id)
    VALUES ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_1.webp', 3),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_2.webp', 3),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_3.webp', 3),
           ('http://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_4.webp', 3);

INSERT INTO accommodation (name, price_per_night, guests, beds, bathrooms, fk_accommodation_type_id, fk_address_id)
    VALUES ('Apartment with the best view', 4600, 10, 3, 5, 1, 1);

INSERT INTO photo (url, fk_accommodation_id)
    VALUES ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_3.webp', 4),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_2.webp', 4),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681770/test_a_1.webp', 4),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_4.webp', 4),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524681771/test_a_5.webp', 4);

INSERT INTO address (city, country, street)
    VALUES ('New York', 'USA', 'Wall Sreet 1');

INSERT INTO accommodation (name, price_per_night, guests, beds, bathrooms, fk_accommodation_type_id, fk_address_id)
    VALUES ('Luxury villa', 19145, 15, 10, 4, 8, 4);

INSERT INTO photo (url, fk_accommodation_id)
    VALUES ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_3.webp', 5),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_2.webp', 5),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_1.webp', 5),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524683179/test_b_4.webp', 5);

INSERT INTO address (city, country, street)
    VALUES ('Barcelona', 'Spain', 'Street 3');

INSERT INTO accommodation (name, price_per_night, guests, beds, bathrooms, fk_accommodation_type_id, fk_address_id)
    VALUES ('Great beach apartment', 1655, 4, 3, 2, 1, 5);

INSERT INTO photo (url, fk_accommodation_id)
    VALUES ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_4.webp', 6),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_2.webp', 6),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_3.webp', 6),
           ('https://res.cloudinary.com/accommodation-booking-system/image/upload/v1524684603/test_c_1.webp', 6);
