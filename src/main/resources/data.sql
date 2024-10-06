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
        (gen_random_uuid(), 'szkło'),
        (gen_random_uuid(), 'paper'),
        (gen_random_uuid(), 'steal'),
        (gen_random_uuid(), 'plastic');

--MethodOfCollection
INSERT INTO "method_of_collection" (id, name)
VALUES
    (gen_random_uuid(), 'odbiór osobisty'),
    (gen_random_uuid(), 'wysyłka kurierem'),
    (gen_random_uuid(), 'wysyłka paczkomatem'),
    (gen_random_uuid(), 'dostawa przez sprzedawcę');

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
         (SELECT id FROM "role" WHERE name = 'user'));

--Item
INSERT INTO "item" (id, name, description, weight, quantity, measurements, modifiable, owner_id, type_id)
VALUES
    (
     gen_random_uuid(),
     'szklane rurki',
     'szklane rurki',
     12,
     40,
     '12x10',
     true,
     (SELECT id FROM "account" WHERE name = 'adampol'),
     (SELECT id FROM "type" WHERE name = 'szkło')
    );

--Photo
INSERT INTO "photo" (id, type, name, data, item_id)
VALUES
    (
     gen_random_uuid(),
     'jpg',
     'szkło',
     decode('48656c6c6f20776f726c64', 'hex'),
     (SELECT id FROM "item" WHERE name = 'szklane rurki')
     );

--Offer
INSERT INTO "offer" (id, price, is_accepted, pickup_location, description, client_id, method_of_collection_id, region_id)
VALUES
    (
    gen_random_uuid(),
    12.56,
    false,
    'Poznańska 38b',
    'Odbiór możliwy tylko we wtorki',
    (SELECT id FROM "account" WHERE name = 'adampol'),
    (SELECT id FROM "method_of_collection" WHERE name = 'odbiór osobisty'),
    (SELECT id FROM "region" WHERE name = 'Podlasie')
    );

--Item-Offer
INSERT INTO "item_offer" (quantity, item_id, offer_id)
VALUES
    (
        12,
        (SELECT id FROM "item" WHERE name = 'szklane rurki'),
        (SELECT id FROM "offer" WHERE price = 12.56)
    );

--Auction
INSERT INTO "auction" (id, bid, start_date, end_date, has_ended, pickup_location, description, winning_account_id, method_of_collection_id, region_id)
VALUES
    (
        gen_random_uuid(),
        145.56,
        '2024-10-02',
        '2024-10-12',
        false,
        'Mieszka 12a',
        'Na teren zakładu mogą wjechać jedynie pojazdy poniżej 8t',
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "method_of_collection" WHERE name = 'dostawa przez sprzedawcę'),
        (SELECT id FROM "region" WHERE name = 'Mazowsze')
    );

--Item-Auction
INSERT INTO "item_auction" (quantity, item_id, auction_id)
VALUES
    (
        24,
        (SELECT id FROM "item" WHERE name = 'szklane rurki'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    );

--Account-Auction
INSERT INTO "account_auction" (account_id, auction_id)
VALUES
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "auction" WHERE bid = 145.56)
    );

--Account-Contractor
INSERT INTO "account_contractor" (account_id, contractor_id)
VALUES
    (
        (SELECT id FROM "account" WHERE name = 'adampol'),
        (SELECT id FROM "account" WHERE name = 'wiesbud')
    );