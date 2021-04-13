insert into devices (name, price) values ('smartphone', 100);
insert into devices (name, price) values ('laptop', 200);
insert into devices (name, price) values ('tv', 150);
insert into devices (name, price) values ('cooker', 100);
insert into devices (name, price) values ('watch', 50);
insert into devices (name, price) values ('car', 5500);

insert into people (name) values('alex');
insert into people (name) values('sam');
insert into people (name) values('tom');
insert into people (name) values('susana');
insert into people (name) values('nicky');

insert into devices_people (device_id, people_id) values(1, 1);
insert into devices_people (device_id, people_id) values(2, 3);
insert into devices_people (device_id, people_id) values(4, 3);
insert into devices_people (device_id, people_id) values(2, 5);
insert into devices_people (device_id, people_id) values(3, 2);
insert into devices_people (device_id, people_id) values(5, 1);
insert into devices_people (device_id, people_id) values(2, 1);
insert into devices_people (device_id, people_id) values(4, 4);
insert into devices_people (device_id, people_id) values(1, 6);

--select avg(d.price) from devices as d
--select p.name, avg(d.price) from devices_people as dp inner join people as p on p.id=dp.people_id inner join devices d on d.id=dp.device_id group by dp.people_id, p.name
select p.name, avg(d.price) from devices_people as dp inner join people as p on p.id=dp.people_id inner join devices d on d.id=dp.device_id group by dp.people_id, p.name having avg(d.price) > 5000 