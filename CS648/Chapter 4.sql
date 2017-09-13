/*
Chapter 4, Homework Questions 1-7
Paul Truong Nguyen
*/

-- 1

SELECT *
FROM vendors AS v 
	 INNER JOIN invoices AS i
		ON v.vendor_id = i.vendor_id;

-- 2

SELECT v.vendor_name AS vendor_name, i.invoice_number AS invoice_number,
		i.invoice_date AS invoice_date, 
		i.invoice_total - i.payment_total - i.credit_total
        AS balance_due
FROM vendors AS v INNER JOIN invoices AS i
		ON v.vendor_id = i.vendor_id AND
		i.invoice_total - i.payment_total - i.credit_total != 0
ORDER BY vendor_name;

-- 3

SELECT v.vendor_name, v.default_account_number, gLA.account_description
FROM vendors AS v INNER JOIN General_Ledger_Accounts AS gLA
	 ON v.default_account_number = gLA.account_number
ORDER BY account_description, vendor_name;

-- 4

SELECT v.vendor_name AS vendor_name, i.invoice_date AS invoice_date,
	   i.invoice_number AS invoice_number, iLI.invoice_sequence AS li_sequence,
       iLI.line_item_amount AS li_amount
FROM vendors AS v 
	   INNER JOIN invoices AS i
	      ON v.vendor_id = i.vendor_id 
       INNER JOIN invoice_line_items AS iLI
		  ON i.invoice_id= iLI.invoice_id
ORDER BY vendor_name, invoice_date, invoice_number, invoice_sequence;

-- 5

SELECT v1.vendor_id AS vendor_id, v2.vendor_name AS vendor_name,
	   CONCAT (v1.vendor_contact_first_name, ' ', 
		v2.vendor_contact_last_name) AS contact_name
FROM vendors AS v1 INNER JOIN vendors AS v2
	 ON v1.vendor_id != v2.vendor_id AND
		v1.vendor_contact_last_name = v2.vendor_contact_last_name
ORDER BY contact_name;

-- 6

SELECT gLA.account_number AS account_number, gLA.account_description
	   AS account_description
FROM general_ledger_accounts AS gLA LEFT OUTER JOIN invoice_line_items AS iLI 
	   ON gLA.account_number = iLI.account_number
WHERE iLI.invoice_id IS NULL
ORDER BY account_number;

-- 7

	SELECT v1.vendor_name AS vendor_name, v1.vendor_state as vendor_state
	FROM vendors AS v1
    WHERE vendor_state = 'CA'
UNION
	SELECT v2.vendor_state AS vendor_state, 'OUTSIDE CA'
    FROM vendors as v2
    WHERE vendor_state != 'CA'
ORDER BY vendor_name;

          