create table cinema(
id serial primary key,
name varchar(255),
rating int,
vieved bool
);
insert into cinema(name, rating, vieved) values('Мстители', '8', 'TRUE');
update cinema set rating = '10';
delete from cinema;