/*
 * Paul T. Nguyen
 *  Chapter 6, Questions 1 - 6
 */
 
-- Chapter 6, Question 1*
SELECT
    c.category_name
  FROM
    categories AS c
  WHERE
    c.category_id IN
    (SELECT 
      DISTINCT p.category_id
      FROM products AS p)
  ORDER BY
    c.category_name;
    
-- Chapter 6, Question 2*
SELECT 
    p.product_name, p.list_price
  FROM 
    products AS p
  WHERE
    p.list_price >
      (SELECT
        AVG(p2.list_price)
	  FROM
        products AS p2
	  WHERE 
        p2.list_price > 0)
  ORDER BY
    p.list_price DESC;

-- Chapter 6, Question 3*
SELECT
    c.category_name
  FROM
    categories AS c
  WHERE NOT EXISTS
    (SELECT 
        p.category_id
      FROM 
        products AS p
      WHERE 
        c.category_id = p.category_id)
  ORDER BY 
    c.category_name; 
  
-- Chapter 6, Question 4*
SELECT 
    t.email_address, MAX(t.order_total) AS max_order_total
  FROM
  (
    SELECT 
      c.email_address, o.order_id
      , SUM((oI.item_price - oI.discount_amount) * oI.quantity) as order_total
    FROM 
      customers AS c 
      INNER JOIN orders AS o ON c.customer_id = o.customer_id
      INNER JOIN order_items AS oI ON o.order_id = oI.order_id
    GROUP BY 
      c.email_address, o.order_id
  ) AS t
  GROUP BY 
    t.email_address;                                                          

-- Chapter 6, Question 5*
SELECT
    p.product_name, p.discount_percent
  FROM
    products AS p
  WHERE
	p.discount_percent NOT IN
      (SELECT p2.discount_percent
        FROM
          products AS p2
		WHERE 
          p.product_name != p2.product_name
        )
  ORDER BY p.product_name;

-- Chapter 6, Question 6*
SELECT
    c.email_address, o.order_id, o.order_date
  FROM 
    orders AS o
      INNER JOIN
        (
        SELECT
            o1.customer_id, MIN(o1.order_date) AS oldest_order_date
		  FROM
            orders as o1
		  GROUP BY
            o1.customer_id
		) AS oS
	  ON o.customer_id = oS.customer_id AND
      o.order_date = oS.oldest_order_date
      INNER JOIN
        customers AS c ON o.customer_id = c.customer_id
  ORDER BY c.email_address;