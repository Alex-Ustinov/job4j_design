  create table roles (
  	id serial primary key,
  	name varchar (150)
  )
  
  create table users (
  	id serial primary key,
  	last_name varchar (150),
  	first_name varchar (150),
   	role_id int references roles(id)
  )

  create table rules (
 	id serial primary key,
 	name varchar (150)
 )

 create table rules_roles (
 	id serial primary key,
 	rule_id int references rules(id),
 	role_id int references roles(id)
 )
 
  create table categories (
 	id serial primary key,
	name varchar(150)
 )
 
 create table states (
 	id serial primary key,
	name varchar(150)
 )

 create table items (
 	id serial primary key,
	name varchar (150),
	user_id int references users(id),
	cattegory_id int references categories(id),
	state_id int references states(id)
 )
 create table comments (
 	id serial primary key,
	text varchar (250),
	item_id int references items(id)
 ) 
 
 create table attaches (
 	id serial primary key,
	file bytea,
	item_id int references items(id)
 )



