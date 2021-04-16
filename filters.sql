create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type,
    expired_date timestamp,
    price float
)

insert into type(name) values ('СЫР');
insert into type(name) values ('мороженное');
insert into type(name) values ('молоко');


insert into product(name, type_id, expired_date, price) values ('best', 1, '2021-04-08 04:05:06', 100);
insert into product(name, type_id, expired_date, price) values ('not best', 1, '2021-05-08 04:05:06', 50);
insert into product(name, type_id, expired_date, price) values ('low quality', 1,'2021-05-08 04:05:06', 20);
insert into product(name, type_id, expired_date, price) values ('nice', 2, '2021-04-08 04:05:06', 10);
insert into product(name, type_id, expired_date, price) values ('more less ok', 2, '2021-04-08 04:05:06', 5);
insert into product(name, type_id, expired_date, price) values ('can not be eaten', 2, '2021-04-08 04:05:06', 2);
insert into product(name, type_id, expired_date, price) values ('tasty', 3, '2021-05-08 04:05:06', 15);
insert into product(name, type_id, expired_date, price) values ('bitter', 3, '2021-04-08 04:05:06', 8);
insert into product(name, type_id, expired_date, price) values ('spoiled', 3, '2021-05-08 04:05:06', 3);
insert into product(name, type_id, expired_date, price) values ('test product', 3, '2021-05-08 04:05:06', 3);


select p.name, p.price
from product as p 
inner join type as t on 
t.id = p.type_id
where t.id = 1

select p.name 
from product as p 
inner join type as t on 
t.id = p.type_id
where t.name like '%мороженное%'

select p.name 
from product as p 
inner join type as t on 
t.id = p.type_id
where p.expired_date between now() and (now() + '4 weeks'::interval);

select max(p.price) from product as p

select t.name, count(t.name)
from product as p
inner join type as t on t.id = p.type_id
group by t.id

select p.*
from product as p
inner join type as t on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО'

select t.name
from product as p
inner join type as t on t.id = p.type_id
group by t.name
having count(t.name) > 6

