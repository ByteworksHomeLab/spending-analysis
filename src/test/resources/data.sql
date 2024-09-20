INSERT INTO transaction_category ( id, category, description )
VALUES ('e64abd8a-af20-47c0-a468-5b5a5631fdc6','Alcohol','Alcohol purchases'),
    ('af303b4a-dfd8-443a-b876-6fe2f7be1eaa','Automotive','Automotive purchases'),
    ('9586aaa7-bc91-4ba1-9f2d-42e6af5e468f','Clothing','Clothing purchases'),
    ('6ba01699-d86c-4d07-b399-758139ad8668','Credit','Return credit'),
    ('2c2ee139-06c4-48ae-83e9-3832794e094b','Debit','Daily cash adjustment'),
    ('b97b7eb3-0c72-4816-bd21-f0396e3d8a1d','Electronics','Electronics purchases'),
    ('828e73eb-4527-4d42-9694-7fa9994958c3','Entertainment','Entertainment purchases'),
    ('2c47394f-c811-49b6-a374-f8e5e36a15e5','Fees','Bank Fees'),
    ('d7a172c7-cfc0-451b-b34c-713b83529183','Gifts','Gift purchases'),
    ('f49a32d9-d7e3-47d6-a853-4bf3bd37237d','Gas','Gas purchases'),
    ('b0b0dc7d-5477-4cc9-8e2a-9fe71ce71e04','Groceries','Grocery purchases'),
    ('61641a96-4d3e-430a-ad14-098ec851517c','Medical','Healthcare purchases'),
    ('2104c600-60f4-48b5-b9ea-28d0d2e7bdcd','Interest','Interest Charges'),
    ('1a2ad32e-7812-47ab-9cb1-850bdd0ff5da','Household','Household purchases'),
    ('2875dea5-af5e-41b4-9334-35742852e10c','Payment','Payment'),
    ('5bc0ed74-1d13-41f6-8132-8d4d26efd911','PersonalCare','Personal care purchases'),
    ('105f18f0-3949-451b-b9a5-06222c142a1a','Political','Political purchases'),
    ('eee14807-1e06-4b51-9cce-fac466bbe401','Pets','Personal care purchases'),
    ('f64df404-b331-41b6-ae6c-6ce330d61815','Restaurants','Fast food and dining purchases'),
    ('46a5db25-0c49-4f93-acf9-7d44d1c8797c','Shopping','Shopping purchases'),
    ('8ff2a4cd-3090-4f01-a80e-b046ee547c54','Subscriptions','Subscription purchases'),
    ('94d6e555-98f5-42be-b872-39e6ca091f84','Travel','Travel purchases'),
    ('a638ac55-8021-4f8b-9ea5-217aa15a5ec5','Utilities','Utility purchases'),
    ('2ee601e8-2a95-4f82-b282-dca8be3c7cfa','Other','Other purchases');

INSERT INTO credit_card_transaction
(    id,
    transaction_date,
    statement_year,
    statement_month,
    charge_card,
    description,
    transaction_amount)
VALUES
('64efc470-bd77-4a68-bb0e-4d0b2f3252a6','2024-09-19T12:00:00.000Z',2024,9,'VISA','AMAZON.COM',100.00
 );

