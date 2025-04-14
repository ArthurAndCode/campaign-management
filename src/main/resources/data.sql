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
                                                                                     ('Alice', 'Smith', 'alice@example.com', 'password123', '07123456789', 1000.00),
                                                                                     ('Bob', 'Johnson', 'bob@example.com', 'securepass', '07234567890', 500.50),
                                                                                     ('Charlie', 'Brown', 'charlie@example.com', 'charlie2024', '07345678901', 300.00);

-- Products
INSERT INTO product (name, owner_id) VALUES
                                         ('Wireless Earbuds', 1),
                                         ('Smartwatch Pro 5', 1),
                                         ('Gaming Laptop X15', 2),
                                         ('Mechanical Keyboard RGB', 2),
                                         ('Eco-Friendly Water Bottle', 3),
                                         ('Organic Cotton T-Shirt', 3);

-- Campaigns
INSERT INTO campaign (product_id, campaign_name, bid_amount, campaign_fund, status, town, radius, owner_id) VALUES
                                                                                                                (1, 'Spring Audio Deals', 1.50, 100.00, true, 'London', 10, 1),
                                                                                                                (3, 'Gaming Power Sale', 2.00, 200.00, true, 'Manchester', 15, 2),
                                                                                                                (5, 'Go Green Promo', 1.75, 150.00, false, 'Birmingham', 20, 3);

-- Campaign Keywords
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES
                                                          (1, 'audio'),
                                                          (1, 'earbuds'),
                                                          (1, 'wireless'),
                                                          (2, 'gaming'),
                                                          (2, 'laptop'),
                                                          (2, 'performance'),
                                                          (3, 'eco'),
                                                          (3, 'sustainable'),
                                                          (3, 'green');

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
                               ('new');

