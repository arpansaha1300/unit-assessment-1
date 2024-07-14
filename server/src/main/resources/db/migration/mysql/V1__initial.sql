create table releases (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    type varchar(50) not null,
    imdb_id varchar(20) not null unique,
    season_number int,
    poster_url varchar(255),
    source_release_date date not null,
    source_id int not null,
    source_name varchar(255) not null,
    is_original int not null
);

create table title_sources (
    id bigint auto_increment primary key,
    source_id int not null,
    name varchar(255) not null,
    type varchar(50) not null,
    region varchar(50) not null,
    web_url varchar(255) not null,
    format varchar(50) not null,
    price double,
    seasons int not null,
    episodes int not null
);

create table title_details (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    original_title varchar(255) not null,
    plot_overview longtext not null,
    type varchar(50) not null,
    runtime_minutes int,
    year int not null,
    end_year int,
    release_date date not null,
    imdb_id varchar(20) not null unique,
    user_rating double not null,
    critic_score double,
    poster varchar(255),
    backdrop varchar(255),
    original_language varchar(50) not null,
    relevance_percentile double not null,
    popularity_percentile double not null,
    trailer varchar(255),
    trailer_thumbnail varchar(255),
    genres json not null,
    genre_names json not null
);
