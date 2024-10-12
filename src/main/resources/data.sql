--Role
INSERT INTO "role" (id, name) VALUES (gen_random_uuid(), 'user'), (gen_random_uuid(), 'admin');

--Region
INSERT INTO "region" (id, name)
VALUES
    (gen_random_uuid(), 'Dolnośląskie'),
    (gen_random_uuid(), 'Kujawsko-Pomorskie'),
    (gen_random_uuid(), 'Lubelskie'),
    (gen_random_uuid(), 'Lubuskie'),
    (gen_random_uuid(), 'Łódzkie'),
    (gen_random_uuid(), 'Małopolskie'),
    (gen_random_uuid(), 'Mazowsze'),
    (gen_random_uuid(), 'Opolskie'),
    (gen_random_uuid(), 'Podkarpackie'),
    (gen_random_uuid(), 'Podlasie'),
    (gen_random_uuid(), 'Pomorskie'),
    (gen_random_uuid(), 'Śląskie'),
    (gen_random_uuid(), 'Świętokrzyskie'),
    (gen_random_uuid(), 'Warmińsko-Mazurskie'),
    (gen_random_uuid(), 'Wielkopolskie'),
    (gen_random_uuid(), 'Zachodniopomorskie');

--Type
INSERT INTO "type" (id, name)
VALUES
    (gen_random_uuid(), 'Szkło'),
    (gen_random_uuid(), 'Papier'),
    (gen_random_uuid(), 'Metal'),
    (gen_random_uuid(), 'Plastik'),
    (gen_random_uuid(), 'Drewno'),
    (gen_random_uuid(), 'Tekstylia'),
    (gen_random_uuid(), 'Guma'),
    (gen_random_uuid(), 'Bioodpady'),
    (gen_random_uuid(), 'Ceramika'),
    (gen_random_uuid(), 'Elektronika');

--MethodOfCollection
INSERT INTO "method_of_collection" (id, name)
VALUES
    (gen_random_uuid(), 'Odbiór osobisty'),
    (gen_random_uuid(), 'Wysyłka kurierem'),
    (gen_random_uuid(), 'Wysyłka paczkomatem'),
    (gen_random_uuid(), 'Dostawa przez sprzedawcę');

--Account
INSERT INTO "account" (id, name, nip, phone_number, email, postal_code, city, street, region_id, role_id)
    VALUES
        (gen_random_uuid(), 'admin', null, null, null, null, null, null,
         (SELECT id FROM "region" WHERE name = 'Mazowsze'),
         (SELECT id FROM "role" WHERE name = 'admin')),

        (gen_random_uuid(), 'adampol', '1150469650', '+48914216054','adampol@gmail.com', '62-095', 'Warszawa', 'Dąbrowa 22',
         (SELECT id FROM "region" WHERE name = 'Mazowsze'),
         (SELECT id FROM "role" WHERE name = 'user')),

        (gen_random_uuid(), 'wiesbud', '8837890289', '+48479054900','wiesbud@gmail.com', '32-014', 'Białystok', 'Legionowa 2',
         (SELECT id FROM "region" WHERE name = 'Podlasie'),
         (SELECT id FROM "role" WHERE name = 'user')),

        (gen_random_uuid(), 'markpol', '3437890255', '+48329054915','markpol@gmail.com', '15-045', 'Kraków', 'Mieszka 31',
         (SELECT id FROM "region" WHERE name = 'Małopolskie'),
         (SELECT id FROM "role" WHERE name = 'user')),

        (gen_random_uuid(), 'stalprom', '4337890282', '+48779054993','stalprom@gmail.com', '43-011', 'Opole', 'Kwiatowa 12',
         (SELECT id FROM "region" WHERE name = 'Opolskie'),
         (SELECT id FROM "role" WHERE name = 'user'));

--Item
INSERT INTO "item" (id, name, description, weight, quantity, measurements, modifiable, owner_id, type_id)
VALUES
    (
        gen_random_uuid(),
        'butelki szklane',
        'Butelki po napojach szklanych',
        0.5,
        100,
        '500ml',
        true,
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "type" WHERE name = 'Szkło')
    ),
    (
        gen_random_uuid(),
        'papier makulaturowy',
        'Stare gazety i czasopisma',
        0.3,
        200,
        'A4',
        true,
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "type" WHERE name = 'Papier')
    ),
    (
        gen_random_uuid(),
        'złom stalowy',
        'Złom stalowy z konstrukcji budowlanych',
        1500,
        10,
        '1x1x2m',
        false,
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "type" WHERE name = 'Metal')
    ),
    (
        gen_random_uuid(),
        'butelki PET',
        'Butelki plastikowe po napojach',
        0.02,
        500,
        '1L',
        true,
        (SELECT id FROM "account" WHERE name = 'markpol'),
        (SELECT id FROM "type" WHERE name = 'Plastik')
    ),
    (
        gen_random_uuid(),
        'zużyte opony',
        'Opony samochodowe używane',
        7.5,
        50,
        '205/55 R16',
        false,
        (SELECT id FROM "account" WHERE name = 'wiesbud'),
        (SELECT id FROM "type" WHERE name = 'Guma')
    ),
    (
        gen_random_uuid(),
        'tekstylia bawełniane',
        'Stare ubrania bawełniane',
        1,
        100,
        'Różne rozmiary',
        true,
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "type" WHERE name = 'Tekstylia')
    );

--Photo
INSERT INTO "photo" (id, type, name, data, item_id)
VALUES
    (
     gen_random_uuid(),
     'jpg',
     'butelki',
     decode('48656c6c6f20776f726c64', 'hex'),
     (SELECT id FROM "item" WHERE name = 'butelki szklane')
     );

--Offer
INSERT INTO "offer" (id, price, is_accepted, pickup_location, description, client_id, method_of_collection_id, region_id)
VALUES
    (
        gen_random_uuid(),
        132.56,
        false,
        'Poznańska 38b',
        'Odbiór możliwy tylko we wtorki',
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "method_of_collection" WHERE name = 'Odbiór osobisty'),
        (SELECT id FROM "region" WHERE name = 'Podlasie')
    ),
    (
        gen_random_uuid(),
        211.75,
        false,
        'Poznańska 38b',
        'Odbiór możliwy tylko we wtorki',
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "method_of_collection" WHERE name = 'Odbiór osobisty'),
        (SELECT id FROM "region" WHERE name = 'Podlasie')
    ),
    (
        gen_random_uuid(),
        367.29,
        false,
        'Poznańska 38b',
        'Odbiór możliwy tylko we wtorki',
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "method_of_collection" WHERE name = 'Wysyłka kurierem'),
        (SELECT id FROM "region" WHERE name = 'Podlasie')
    );

--Item-Offer
INSERT INTO "item_offer" (quantity, item_id, offer_id)
VALUES
    (
        100,
        (SELECT id FROM "item" WHERE name = 'butelki szklane'),
        (SELECT id FROM "offer" WHERE price = 132.56)
    ),
    (
        200,
        (SELECT id FROM "item" WHERE name = 'butelki PET'),
        (SELECT id FROM "offer" WHERE price = 132.56)
    ),
    (
        10,
        (SELECT id FROM "item" WHERE name = 'zużyte opony'),
        (SELECT id FROM "offer" WHERE price = 211.75)
    ),
    (
        2,
        (SELECT id FROM "item" WHERE name = 'złom stalowy'),
        (SELECT id FROM "offer" WHERE price = 211.75)
    ),
    (
        50,
        (SELECT id FROM "item" WHERE name = 'tekstylia bawełniane'),
        (SELECT id FROM "offer" WHERE price = 367.29)
    );

--Auction
INSERT INTO "auction" (id, bid, start_date, end_date, has_ended, pickup_location, description, winning_account_id, method_of_collection_id, region_id)
VALUES
    (
        gen_random_uuid(),
        145.56,
        '2024-12-02',
        '2024-12-12',
        false,
        'Mieszka 12a',
        'Na teren zakładu mogą wjechać jedynie pojazdy poniżej 8t',
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "method_of_collection" WHERE name = 'Dostawa przez sprzedawcę'),
        (SELECT id FROM "region" WHERE name = 'Mazowsze')
    ),
    (
        gen_random_uuid(),
        206.78,
        '2024-12-24',
        '2024-12-28',
        false,
        'Mieszka 12a',
        'Na teren zakładu mogą wjechać jedynie pojazdy poniżej 8t',
        (SELECT id FROM "account" WHERE name = 'wiesbud'),
        (SELECT id FROM "method_of_collection" WHERE name = 'Dostawa przez sprzedawcę'),
        (SELECT id FROM "region" WHERE name = 'Mazowsze')
    );

--Item-Auction
INSERT INTO "item_auction" (quantity, item_id, auction_id)
VALUES
    (
        100,
        (SELECT id FROM "item" WHERE name = 'papier makulaturowy'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    ),
    (
        1,
        (SELECT id FROM "item" WHERE name = 'złom stalowy'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    ),
    (
        35,
        (SELECT id FROM "item" WHERE name = 'zużyte opony'),
        (SELECT id FROM "auction" WHERE bid = 206.78)
    );

--Account-Auction
INSERT INTO "account_auction" (account_id, auction_id)
VALUES
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    ),
    (
        (SELECT id FROM "account" WHERE name = 'stalprom'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    ),
    (
        (SELECT id FROM "account" WHERE name = 'markpol'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    ),
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "auction" WHERE bid = 206.78)
    ),
    (
        (SELECT id FROM "account" WHERE name = 'wiesbud'),
        (SELECT id FROM "auction" WHERE bid = 206.78)
    );

--Account-Contractor
INSERT INTO "account_contractor" (account_id, contractor_id)
VALUES
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "account" WHERE name = 'wiesbud')
    ),
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "account" WHERE name = 'markpol')
    ),
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "account" WHERE name = 'stalprom')
    ),
    (
        (SELECT id FROM "account" WHERE name = 'markpol'),
        (SELECT id FROM "account" WHERE name = 'adampol')
    ),
    (
        (SELECT id FROM "account" WHERE name = 'wiesbud'),
        (SELECT id FROM "account" WHERE name = 'adampol')
    ),
    (
        (SELECT id FROM "account" WHERE name = 'wiesbud'),
        (SELECT id FROM "account" WHERE name = 'stalprom')
    );