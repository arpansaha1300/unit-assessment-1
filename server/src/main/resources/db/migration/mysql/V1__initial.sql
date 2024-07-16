create table movie (
    id int auto_increment primary key,
    title varchar(255) not null,
    plot longtext not null,
    trailer varchar(255) not null,
    rating int not null
);

create table vendor (
    id int auto_increment primary key,
    name varchar(255) not null
);

create table movie_vendor (
    id int auto_increment primary key,
    movie_id int,
    vendor_id int,
    price double not null,
    foreign key (movie_id) references movie(id),
    foreign key (vendor_id) references vendor(id)
);

create table poster (
    id int auto_increment primary key,
    url varchar(255) not null,
    movie_id int,
    foreign key (movie_id) references movie(id)
);

