USE my_guitar_shop;

DROP PROCEDURE IF EXISTS test;

DELIMITER //

CREATE PROCEDURE test()
  BEGIN
    DECLARE
      avg_list_price DECIMAL(9, 2);
	DECLARE
      product_count INT;
        SELECT 
          AVG(list_price), COUNT(product_id) 
          INTO avg_list_price, product_count
	    FROM
          products;
	IF product_count >= 7 THEN
      SELECT
        product_count, avg_list_price;
	ELSE
      SELECT 
        'The number of products is less than 7' AS message;
	END IF;
  END//
  
  CALL test();
  

      
    