# Dev Note
> 开发常用配置文记录或者说明备忘
## Server Info
- centos $ ssh -i qtum_proj.pem root@82.157.174.79  
  centos 内网 ip 172.21.0.12
- ubuntu $ ssh -i qtum_proj.pem ubuntu@49.232.209.145  
  ubuntu 内网 ip 172.21.0.11  

## Ubuntu Qtum Info
### 可执行文件路径   path:/usr/local/bin  
  ```shell

  - 后台运行 $ ./qtumd -daemon  
  - 测试远程使用RPC $ curl --user test:test1234 --data-binary '{"jsonrpc": "1.0", "id": "curltest", "method": "getblockchaininfo", "params": []}' -H 'content-type: text/plain;' http://49.232.209.145:8081/  
  - 获取所有RPC命令 $ ./qtum-cli help
  ```
### Qtum Config  
- 配置文件路径    path:~/.qtum/qtum.conf
- Config File Content
```conf
server=1
rpcuser=test
rpcpassword=test1234
rpcallowip=0.0.0.0/0
rpcbind=0.0.0.0
rpcport=8081
```

## Database Info
- Table movie_info
```sql
create table movie_nft.movie_info
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
- Table artwork_info
```sql
create table movie_nft.artwork_info
(
    artwork_id      int auto_increment
        primary key,
    quantity        int                                 not null,
    price           varchar(256)                        not null,
    publish_time    varchar(64)                         not null,
    artwork_name    varchar(64)                         not null,
    cover           varchar(255)                        not null,
    artwork_status  int                                 not null,
    upload_time     timestamp default CURRENT_TIMESTAMP null,
    intro           text                                null,
    file_link       varchar(255)                        not null,
    remain_quantity int                                 null
)
```
- Table copyright_info
```sql
create table movie_nft.copyright_info
(
    copyright_id    int auto_increment
        primary key,
    movie_id        int                                 not null,
    record_number   varchar(64)                         null,
    copyright_type  int                                 null,
    price           varchar(255)                        not null,
    quantity        int                                 null,
    remain_quantity int                                 not null,
    share           varchar(64)                         null,
    publish_time    timestamp default CURRENT_TIMESTAMP null,
    constraint copyright_info_pk
        unique (record_number, copyright_type)
)
```

