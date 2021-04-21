create table car_body (
	id serial primary key,
	name varchar (255)
);

create table engine (
	id serial primary key,
	name varchar (255)
);

create table transmission (
	id serial primary key,
	name varchar (255)
);

create table car (
	id serial primary key,
	name varchar (255),
	car_body_id int references car_body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into car_body(name) values ('sedan');
insert into car_body(name) values ('hatchback');
insert into car_body(name) values ('cabriolet');
insert into car_body(name) values ('bagi');

insert into engine(name) values ('race');
insert into engine(name) values ('diesele');
insert into engine(name) values ('oil');
insert into engine(name) values ('electric');

insert into transmission(name) values ('sport');
insert into transmission(name) values ('offroad');
insert into transmission(name) values ('plain');
insert into transmission(name) values ('hybrid');


insert into car(name, car_body_id, engine_id, transmission_id) values ('bmw', 1 , 3, 2);
insert into car(name, car_body_id, engine_id, transmission_id) values ('honda', 2 , 1, 2);
insert into car(name, car_body_id, engine_id, transmission_id) values ('opel', 3 , 3, 3);

select * from car as c 
left join car_body as b
on b.id = c.car_body_id
left join engine as e
on e.id = c.engine_id
left join transmission as t
on t.id = c.transmission_id

select * from car as c 
right join car_body as b
on b.id = c.car_body_id
where c.car_body_id is null

select * from engine as e 
left join car as c
on e.id = c.engine_id
where c.engine_id is null

select * from transmission as t 
left join car as c
on t.id = c.transmission_id
where c.transmission_id is null