# bookstore-backend-using-springboot
content spring boot project 
content endpont to user It contains user registration form as well as user login using Json web token




SELECT * FROM mymedicine_store.order_items;
-- SELECT * FROM mymedicine_store.users where users.id in()-- 

select * from mymedicine_store.medicine;
select * from mymedicine_store.order_items;
select * from mymedicine_store.orders;
select * from mymedicine_store.users;

update mymedicine_store.order_items set order_id =3 where id = 5;
UPDATE  mymedicine_store.order_items
SET order_id = 1
WHERE id = 5;

select * from  mymedicine_store.orders ;

SELECT *
FROM mymedicine_store.orders
-- INNER JOIN mymedicine_store.users ON mymedicine_store.orders.user_id=mymedicine_store.users.id
INNER JOIN  mymedicine_store.medicine On mymedicine_store.medicine.id = mymedicine_store.order_items.book_id;

SELECT *
FROM mymedicine_store.cart_items
INNER JOIN mymedicine_store.users ON mymedicine_store.orders.user_id=mymedicine_store.users.id
INNER JOIN  mymedicine_store.medicine On mymedicine_store.medicine.id = mymedicine_store.order_items.id;

select * from mymedicine_store.cart_items where mymedicine_store.cart_items.medicine_id  in(select id from  mymedicine_store.medicine);

INSERT INTO mymedicine_store.medicine (id, category, image, name, price) VALUES
(1, 'Electronics', 'https://picsum.photos/id/0/5000/3333', 'Gaming Laptop', 1299.99),
(2, 'Electronics', 'https://picsum.photos/id/1/5000/3333', 'Bluetooth Headphones', 89.99),
(3, 'Appliances', 'https://picsum.photos/id/2/5000/3333', 'Air Purifier', 149.99),
(4, 'Appliances', 'https://picsum.photos/id/3/5000/3333', 'Electric Kettle', 39.99),
(5, 'Electronics', 'https://picsum.photos/id/4/5000/3333', '4K TV', 799.99),
(6, 'Furniture', 'https://picsum.photos/id/5/5000/3334', 'Ergonomic Chair', 299.99),
(7, 'Furniture', 'https://picsum.photos/id/6/5000/3333', 'Office Desk', 199.99),
(8, 'Appliances', 'https://picsum.photos/id/7/4728/3168', 'Stand Mixer', 249.99),
(9, 'Electronics', 'https://picsum.photos/id/8/5000/3333', 'Tablet', 349.99),
(10, 'Electronics', 'https://picsum.photos/id/9/5000/3269', 'Smartwatch', 229.99),
(11, 'Electronics', 'https://picsum.photos/id/10/2500/1667', 'Portable Speaker', 59.99),
(12, 'Furniture', 'https://picsum.photos/id/11/2500/1667', 'Adjustable Desk', 399.99),
(13, 'Appliances', 'https://picsum.photos/id/12/2500/1667', 'Blender', 89.99),
(14, 'Electronics', 'https://picsum.photos/id/13/2500/1667', 'Camera', 499.99),
(15, 'Appliances', 'https://picsum.photos/id/14/2500/1667', 'Vacuum Cleaner', 149.99),
(16, 'Appliances', 'https://picsum.photos/id/15/2500/1667', 'Outdoor Grill', 399.99),
(17, 'Furniture', 'https://picsum.photos/id/16/2500/1667', 'Bookshelf', 119.99),
(18, 'Appliances', 'https://picsum.photos/id/17/2500/1667', 'Microwave Oven', 79.99),
(19, 'Electronics', 'https://picsum.photos/id/18/2500/1667', 'Wireless Mouse', 29.99),
(20, 'Electronics', 'https://picsum.photos/id/19/2500/1667', 'Digital Thermometer', 19.99);


sample test data for testing
