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
INSERT INTO "account" (id, first_name, last_name, nip, phone_number, role_id)
    VALUES
        (gen_random_uuid(), 'Adam', 'Kowalski', '1150469650','+48914216054',(SELECT id FROM "role" WHERE name = 'admin')),
        (gen_random_uuid(), 'Tomasz', 'Nowak', '8837890289','+48479054900',(SELECT id FROM "role" WHERE name = 'user')),
        (gen_random_uuid(), 'Krystian', 'Niedzielski', '5750867332','+48293827763',(SELECT id FROM "role" WHERE name = 'user')),
        (gen_random_uuid(), 'Monika', 'Zdun', '7344968623','+48158260050',(SELECT id FROM "role" WHERE name = 'user')),
        (gen_random_uuid(), 'Grzegorz', 'Mikura', '7361896694','+48149605553',(SELECT id FROM "role" WHERE name = 'user'));

--Item
INSERT INTO "item" (id, name, description, weight, measurements, pickup_location, method_of_collection_id, modifiable, region_id, account_id, type_id)
VALUES
    (
     gen_random_uuid(),
    'szklane rurki',
    'szklane rurki',
    12,
    '12x10',
    'Poznańska 38b',
    (SELECT id FROM "method_of_collection" WHERE name = 'odbiór osobisty'),
    true,
    (SELECT id FROM "region" WHERE name = 'Podlasie'),
    (SELECT id FROM "account" WHERE phone_number = '+48914216054'),
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
INSERT INTO "offer" (id, price, is_accepted, client_id, seller_id)
VALUES
    (
    gen_random_uuid(),
    12.56,
    false,
    (SELECT id FROM "account" WHERE phone_number = '+48914216054'),
    (SELECT id FROM "account" WHERE phone_number = '+48149605553')
    );

--Item-Offer
INSERT INTO "item_offer" (item_id, offer_id)
VALUES
    (
        (SELECT id FROM "item" WHERE name = 'szklane rurki'),
        (SELECT id FROM "offer" WHERE price = 12.56)
    );

--Auction
INSERT INTO "auction" (id, bid, start_date, end_date, has_ended, item_id, winning_account_id, seller_id)
VALUES
    (
        gen_random_uuid(),
        145.56,
        '2024-10-02',
        '2024-10-12',
        false,
        (SELECT id FROM "item" WHERE name = 'szklane rurki'),
        (SELECT id FROM "account" WHERE phone_number = '+48914216054'),
        (SELECT id FROM "account" WHERE phone_number = '+48149605553')
    );