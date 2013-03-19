create table company (
id serial primary key,
name varchar(255) not null,
address text,
subscriptionDate date not null,
createdOn timestamp not null
);

create table product (
id serial primary key,
name varchar(255) not null,
keywords text not null,
createdOn timestamp not null,
company_id int not null,
foreign key(company_id) references company(id)
);

create table twicks_user (
id serial primary key,
firstName varchar(255) not null,
middleName varchar(255),
lastName varchar(255) not null,
mobileNo varchar(25) not null,
address text,
role varchar(255) not null,
email varchar(255) not null unique,
createdOn timestamp not null,
lastLoggedIn timestamp not null,
password varchar(255) not null,
company_id int,
foreign key(company_id) references company(id)
);

create table result (
id serial primary key,
hour timestamp not null,
source int not null,
rating numeric not null,
feedCount int not null,
createdOn timestamp not null,
product_id int not null,
company_id int not null,
foreign key(product_id) references product(id),
foreign key(company_id) references company(id)
);
