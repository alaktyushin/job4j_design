 create table maker(
     id serial primary key,
     name varchar(100)
 );

 create table model(
     id serial primary key,
	 maker_id int references maker(id),
     name varchar(100)
 );
