/*
 * Chapter 5 Homework
 * Paul T. Nguyen
 */
 
-- Chapter 4, Question 4
SELECT
       c.last_name AS last_name, c.first_name AS first_name
       , o.order_date AS order_date, p.product_name AS product_name
       , p.list_price AS item_price, oI.discount_amount AS discount_amount
       , oI.quantity AS quantity
   FROM 
	   customers AS c 
       INNER JOIN orders AS o ON c.customer_id = o.customer_id
       INNER JOIN order_items AS oI ON o.order_id = oI.order_id
       INNER JOIN products AS p ON oI.product_id = p.product_id
   ORDER BY
       last_name, order_date, product_name;
       
-- Chapter 4, Question 6
SELECT
       c.category_name AS category_name, p.product_id AS product_id
   FROM
       categories AS c 
       LEFT OUTER JOIN products AS p ON c.category_id = p.category_id
   HAVING
       p.product_id IS NULL;

-- Chapter 5, Question 5
SELECT
       c.email_address AS email_address, COUNT(DISTINCT o.order_id) AS orders_count
       , SUM(oI.item_price - oI.discount_amount) * SUM(oI.quantity) AS total_amount
   FROM 
       customers AS c 
	   INNER JOIN orders AS o ON c.customer_id = o.customer_id
       INNER JOIN order_items AS oI ON o.order_id = oI.order_id
   WHERE
       oI.item_price > 400
   GROUP BY
       email_address
   HAVING
       orders_count > 1
   ORDER BY 
       SUM(oI.item_price) DESC;
   
-- Chapter 5, Question 7
SELECT 
       c.email_address AS email_address, COUNT(DISTINCT oI.product_id) AS distinct_products
   FROM 
       customers AS c 
       INNER JOIN orders AS o ON c.customer_id = o.customer_id
       INNER JOIN order_items AS oI ON o.order_id = oI.order_id
   GROUP BY
       email_address
   HAVING
       distinct_products > 1;