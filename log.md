

## movie_info Table
### create table sql
```sql
create table movie_info
(
    movie_id int,
    record_number varchar(64) not null,
    chinese_name varchar(64) not null,
    english_name varchar(64) not null,
    director varchar(64) null,
    region varchar(64) null,
    producer varchar(64) null,
    publish_company varchar(64) null,
    preview varchar(128) null,
    post varchar(128) null,
    publish_time date null,
    upload_time datetime not null,
    plot text null,
    intro text null,
    constraint movie_info_pk
        primary key (movie_id)
);

alter table movie_info modify movie_id int auto_increment;
create unique index movie_info_record_number_uindex
    on movie_info (record_number);
```

