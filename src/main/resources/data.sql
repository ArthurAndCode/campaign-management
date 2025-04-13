
-- Towns
INSERT INTO town (id, name) VALUES (1, 'London');
INSERT INTO town (id, name) VALUES (2, 'Birmingham');
INSERT INTO town (id, name) VALUES (3, 'Manchester');
INSERT INTO town (id, name) VALUES (4, 'Glasgow');
INSERT INTO town (id, name) VALUES (5, 'Liverpool');
INSERT INTO town (id, name) VALUES (6, 'Leeds');
INSERT INTO town (id, name) VALUES (7, 'Sheffield');
INSERT INTO town (id, name) VALUES (8, 'Edinburgh');
INSERT INTO town (id, name) VALUES (9, 'Bristol');
INSERT INTO town (id, name) VALUES (10, 'Cardiff');
INSERT INTO town (id, name) VALUES (11, 'Leicester');
INSERT INTO town (id, name) VALUES (12, 'Coventry');
INSERT INTO town (id, name) VALUES (13, 'Kingston upon Hull');
INSERT INTO town (id, name) VALUES (14, 'Newcastle upon Tyne');
INSERT INTO town (id, name) VALUES (15, 'Stoke-on-Trent');

-- Owners
INSERT INTO owner (id, first_name, last_name, email, password, phone, emerald_funds) VALUES
                                                                                         (1, 'Alice', 'Smith', 'alice@example.com', 'password123', '07123456789', 1000.00),
                                                                                         (2, 'Bob', 'Johnson', 'bob@example.com', 'securepass', '07234567890', 500.50),
                                                                                         (3, 'Charlie', 'Brown', 'charlie@example.com', 'charlie2024', '07345678901', 300.00);

-- Products
INSERT INTO product (id, name, owner_id) VALUES
                                             (1, 'Wireless Earbuds', 1),
                                             (2, 'Smartwatch Pro 5', 1),
                                             (3, 'Gaming Laptop X15', 2),
                                             (4, 'Mechanical Keyboard RGB', 2),
                                             (5, 'Eco-Friendly Water Bottle', 3),
                                             (6, 'Organic Cotton T-Shirt', 3);

-- Campaigns
INSERT INTO campaign (id, product_id, campaign_name, bid_amount, campaign_fund, status, town, radius, owner_id) VALUES
                                                                                                                    (1, 1, 'Spring Audio Deals', 1.50, 100.00, true, 'London', 10, 1),
                                                                                                                    (2, 3, 'Gaming Power Sale', 2.00, 200.00, true, 'Manchester', 15, 2),
                                                                                                                    (3, 5, 'Go Green Promo', 1.75, 150.00, false, 'Birmingham', 20, 3);

-- Keywords
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (1, 'audio');
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (1, 'earbuds');
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (1, 'wireless');

INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (2, 'gaming');
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (2, 'laptop');
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (2, 'performance');

INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (3, 'eco');
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (3, 'sustainable');
INSERT INTO campaign_keywords (campaign_id, keywords) VALUES (3, 'green');

-- Keywords
INSERT INTO keyword (id, name) VALUES (1, 'wireless');
INSERT INTO keyword (id, name) VALUES (2, 'earbuds');
INSERT INTO keyword (id, name) VALUES (3, 'smartwatch');
INSERT INTO keyword (id, name) VALUES (4, 'fitness');
INSERT INTO keyword (id, name) VALUES (5, 'gaming');
INSERT INTO keyword (id, name) VALUES (6, 'laptop');
INSERT INTO keyword (id, name) VALUES (7, 'keyboard');
INSERT INTO keyword (id, name) VALUES (8, 'mechanical');
INSERT INTO keyword (id, name) VALUES (9, 'rgb');
INSERT INTO keyword (id, name) VALUES (10, 'eco');
INSERT INTO keyword (id, name) VALUES (11, 'sustainable');
INSERT INTO keyword (id, name) VALUES (12, 'green');
INSERT INTO keyword (id, name) VALUES (13, 'organic');
INSERT INTO keyword (id, name) VALUES (14, 'cotton');
INSERT INTO keyword (id, name) VALUES (15, 'reusable');
INSERT INTO keyword (id, name) VALUES (16, 'fashion');
INSERT INTO keyword (id, name) VALUES (17, 'clothing');
INSERT INTO keyword (id, name) VALUES (18, 'men');
INSERT INTO keyword (id, name) VALUES (19, 'women');
INSERT INTO keyword (id, name) VALUES (20, 'technology');
INSERT INTO keyword (id, name) VALUES (21, 'gadgets');
INSERT INTO keyword (id, name) VALUES (22, 'portable');
INSERT INTO keyword (id, name) VALUES (23, 'performance');
INSERT INTO keyword (id, name) VALUES (24, 'bestseller');
INSERT INTO keyword (id, name) VALUES (25, 'new');

