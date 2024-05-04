create table book (
                      id serial primary key,
                      title varchar(255) not null,
                      author_name varchar(255) not null,
                      publisher_name varchar(255) not null
);