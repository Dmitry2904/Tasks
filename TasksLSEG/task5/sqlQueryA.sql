-- a) Will join Table 1 with Table 2 on "ID" and "CustomerID"
select * from customers
inner join orders on customers.ID = orders.CustomerID