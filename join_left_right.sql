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
insert into departments(name) values ('administrators');

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
from employees as e 
left join departments as d 
on e.department_id = d.id 

select * 
from departments as d 
right join employees as e 
on e.department_id = d.id 

select * 
from employees as e 
full join departments as d 
on e.department_id = d.id 

select * 
from employees as e 
cross join departments as d 

select * 
from departments as d 
left join employees as e 
on e.department_id = d.id
where department_id is null


select * 
from employees as e 
left join departments as d 
on e.department_id = d.id 

select * 
from departments as d 
right join employees as e 
on e.department_id = d.id 

create table teens (
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('alex', 'male');
insert into teens(name, gender) values ('sam', 'male');
insert into teens(name, gender) values ('pam', 'female');
insert into teens(name, gender) values ('koly', 'male');
insert into teens(name, gender) values ('dim', 'male');
insert into teens(name, gender) values ('anton', 'male');
insert into teens(name, gender) values ('zina', 'female');
insert into teens(name, gender) values ('susana', 'female');
insert into teens(name, gender) values ('adam', 'male');
insert into teens(name, gender) values ('mat', 'male');

select * from teens t1 cross join teens t2
where t1.gender = 'male' and t2.gender = 'female'