drop table company cascade;

create table company (
id serial primary key,
name varchar(255) not null,
address text,
subscriptionDate date not null,
createdOn timestamp not null
);

drop table product cascade;

create table product (
id serial primary key,
name varchar(255) not null,
keywords text not null,
createdOn timestamp not null,
company_id int not null,
foreign key(company_id) references company(id)
);

drop table twicks_user cascade;

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
lastLoggedIn timestamp null,
username varchar(255) not null unique,
password varchar(255) not null,
company_id int,
foreign key(company_id) references company(id)
);

drop table result cascade;

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

insert into twicks_user values (1,'Sahir','Zulfikar','Maredia','8898985498',null,'admin','sahirzm@gmail.com',now(),null,'sahir',md5('sahir123'),null);
