/*
Chapter 3 Homework Assignment: Questions 6, 8, 10, 12
Paul Truong Nguyen
*/

-- 6

SELECT vendor_name, vendor_contact_last_name, 
	   vendor_contact_first_name
FROM vendors
ORDER BY  vendor_contact_last_name, vendor_contact_first_name;

-- 8

SELECT invoice_due_date AS dueDate, invoice_total AS invoiceTotal,
		invoice_total * 0.10 AS tenPercent,
        invoice_total + invoice_total * 0.10 AS plusTenPercent
FROM invoices
WHERE invoice_total >= 500 AND invoice_total <= 1000
ORDER BY dueDate DESC;

-- 10

SELECT invoice_number, invoice_date,
		invoice_total - payment_total - credit_total AS balance_due,
        payment_date
FROM invoices
WHERE payment_date IS NULL;

-- 12

SELECT 50000 AS starting_principal, 0.065 AS interest, 
	   50000 * (1 + 0.065) AS principal_plus_interest;
