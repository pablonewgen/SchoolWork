/*
 * Paul T. Nguyen
 * Chapter 7, Questions 2, 4, (6), 8
 * Chapter 8, Questions 1, 2
 *
 */

-- Chapter 7, Question 2
insert into categories
values (default, 'Woodwinds');

UPDATE
    categories
  SET
    category_name = 'Woodwinds'
  WHERE 
    category_id = 5;
    
-- Chapter 7, Question 4
INSERT INTO
    products
      (product_id, category_id, product_code, product_name
      , description, list_price, discount_percent, date_added)
  VALUES
    (DEFAULT, 4, 'dgx_640', 'Yamaha DGX 640 88-Key Digital Piano'
    , 'Long description to come', 799.99, 0, NOW());
    
-- Chapter 7, Question 6
DELETE FROM
    products
  WHERE
    category_id = 5;
    
DELETE FROM
    categories
  WHERE
    category_id = 5;
    
-- Chapter 7, Question 8
UPDATE
    customers
  SET
    password = 'secret'
  WHERE 
    email_address = 'rick@raven.com';
    
-- Chapter 8, Question 1
SELECT
    list_price, FORMAT(list_price, 1) AS list_FORMAT
    , CONVERT(list_price, SIGNED) AS list_CONVERT
    , CAST(list_price AS SIGNED) AS list_CAST
  FROM
    products;
    
-- Chapter 8, Question 2
SELECT
    date_added
    , CAST(date_added AS DATE) AS da_DateCast
    , CAST(date_added AS CHAR(7)) AS da_CharCast
    , CAST(date_added AS TIME) AS da_TimeCast
  FROM
    products;
    