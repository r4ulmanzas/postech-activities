create table student (
    id serial primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    phone char(11) not null
);