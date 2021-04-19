create table departments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);


insert into departments(name) values ('developers');
insert into departments(name) values ('supports');
insert into departments(name) values ('accounters');

insert into employees(name, department_id) values ('alex', 1);
insert into employees(name, department_id) values ('sam', 2);
insert into employees(name, department_id) values ('pam', 3);
insert into employees(name, department_id) values ('koly', 1);
insert into employees(name, department_id) values ('dim', 2);
insert into employees(name, department_id) values ('anton', 3);
insert into employees(name, department_id) values ('zina', 1);
insert into employees(name, department_id) values ('susana', 2);
insert into employees(name, department_id) values ('adam', 3);
insert into employees(name, department_id) values ('mat', 3);

select * 
from departments as d 
left join employees as e 
on e.department_id = d.id 