CREATE DATABASE IF NOT EXISTS Coupons_v1 ;

-- TABLES
-- COMPANIES
CREATE TABLE IF NOT EXISTS Coupons_v1.Companies(
id INT primary key auto_increment,
name VARCHAR(30),
email VARCHAR(30),
password VARCHAR(30));

-- CUSTOMERS
CREATE TABLE IF NOT EXISTS Coupons_v1.Customers(
id INT primary key auto_increment,
first_name VARCHAR(30),
last_name VARCHAR(30),
email VARCHAR(30),
password VARCHAR(30)
);

-- CATEGORIES
CREATE TABLE IF NOT EXISTS Coupons_v1.Categories(
id INT primary key auto_increment,
category_id int
);

-- COUPONS
CREATE TABLE IF NOT EXISTS Coupons_v1.Coupons(
id INT primary key auto_increment,
company_id int,
category_id int,
title VARCHAR(30),
description VARCHAR(50),
start_date date,
end_date date,
amount int,
price double,
image VARCHAR(50),

foreign key (company_id) references Coupons_v1.Companies(id),
foreign key (category_id) references Coupons_v1.Categories(id)
);

-- CUSTOMERS_VS_COUPONS
CREATE TABLE IF NOT EXISTS Coupons_v1.Customers_VS_Coupons(
customer_id int primary key,
coupon_id int,
foreign key(customer_id) references Coupons_V1.Customers(id),
foreign key(coupon_id) references Coupons_V1.Coupons(id)
);