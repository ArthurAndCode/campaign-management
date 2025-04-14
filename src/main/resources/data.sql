-- Towns
INSERT INTO town (name) VALUES
                            ('London'),
                            ('Birmingham'),
                            ('Manchester'),
                            ('Glasgow'),
                            ('Liverpool'),
                            ('Leeds'),
                            ('Sheffield'),
                            ('Edinburgh'),
                            ('Bristol'),
                            ('Cardiff'),
                            ('Leicester'),
                            ('Coventry'),
                            ('Kingston upon Hull'),
                            ('Newcastle upon Tyne'),
                            ('Stoke-on-Trent');

-- Owners
INSERT INTO owner (first_name, last_name, email, password, phone, emerald_funds) VALUES
                                                                                     ('Karol', 'Nowakowski', 'karol.nowakowski@futurum.tech', 'password123', '07123456789', 1000.00),
                                                                                     ('Bob', 'Johnson', 'bob@example.com', 'securepass', '07234567890', 5000.50),
                                                                                     ('Charlie', 'Brown', 'charlie@example.com', 'charlie2024', '07345678901', 3000.00);

-- Products (10 for Alice [owner_id = 1])
INSERT INTO product (name, owner_id) VALUES
                                         ('Wireless Earbuds', 1),
                                         ('Smartwatch Pro 5', 1),
                                         ('Bluetooth Speaker', 1),
                                         ('Noise Cancelling Headphones', 1),
                                         ('Portable Charger 20000mAh', 1),
                                         ('4K Action Camera', 1),
                                         ('Smart Home Hub', 1),
                                         ('Fitness Tracker Band', 1),
                                         ('LED Desk Lamp', 1),
                                         ('Compact Drone Mini', 1),

                                         -- Other owners
                                         ('Gaming Laptop X15', 2),
                                         ('Mechanical Keyboard RGB', 2),
                                         ('Eco-Friendly Water Bottle', 3),
                                         ('Organic Cotton T-Shirt', 3);

-- Campaigns (4 for Alice)
INSERT INTO campaign (product_id, campaign_name, bid_amount, campaign_fund, status, town, radius, owner_id) VALUES
                                                                                                                (1, 'Spring Audio Deals', 1.50, 100.00, true, 'London', 10, 1),
                                                                                                                (2, 'Smartwatch Launch', 1.80, 120.00, true, 'Bristol', 12, 1),
                                                                                                                (3, 'Portable Speaker Vibes', 1.40, 90.00, false, 'Cardiff', 8, 1),
                                                                                                                (4, 'Silence the World', 2.00, 150.00, true, 'Glasgow', 10, 1),

                                                                                                                -- Other owners
                                                                                                                (11, 'Gaming Power Sale', 2.00, 200.00, true, 'Manchester', 15, 2),
                                                                                                                (13, 'Go Green Promo', 1.75, 150.00, false, 'Birmingham', 20, 3);

-- Campaign Keywords
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES
                                                          (1, 'audio'),
                                                          (1, 'earbuds'),
                                                          (1, 'wireless'),
                                                          (2, 'smartwatch'),
                                                          (2, 'fitness'),
                                                          (2, 'wearable'),
                                                          (3, 'portable'),
                                                          (3, 'music'),
                                                          (3, 'bluetooth'),
                                                          (4, 'headphones'),
                                                          (4, 'noise cancelling'),
                                                          (4, 'hi-fi'),
                                                          (5, 'gaming'),
                                                          (5, 'laptop'),
                                                          (5, 'performance'),
                                                          (6, 'eco'),
                                                          (6, 'sustainable'),
                                                          (6, 'green');

-- Keywords
INSERT INTO keyword (name) VALUES
                               ('wireless'),
                               ('earbuds'),
                               ('smartwatch'),
                               ('fitness'),
                               ('gaming'),
                               ('laptop'),
                               ('keyboard'),
                               ('mechanical'),
                               ('rgb'),
                               ('eco'),
                               ('sustainable'),
                               ('green'),
                               ('organic'),
                               ('cotton'),
                               ('reusable'),
                               ('fashion'),
                               ('clothing'),
                               ('men'),
                               ('women'),
                               ('technology'),
                               ('gadgets'),
                               ('portable'),
                               ('performance'),
                               ('bestseller'),
                               ('new'),
                               ('bluetooth'),
                               ('hi-fi'),
                               ('noise cancelling'),
                               ('wearable'),
                               ('music');
