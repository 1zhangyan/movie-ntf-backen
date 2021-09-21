# Dev Note
> 开发常用配置文记录或者说明备忘
## Server Info
- centos $ ssh -i qtum_proj.pem root@82.157.177.72  
  centos 内网 ip 172.21.0.12
- ubuntu $ ssh -i qtum_proj.pem ubuntu@152.136.130.81  
  ubuntu 内网 ip 172.21.0.11  

## Ubuntu Qtum Info
### 可执行文件路径   path:/usr/local/bin  
  ```shell

  - 后台运行 $ ./qtumd -daemon -logevents
  - 测试远程使用RPC $ curl --user test:test1234 --data-binary '{"jsonrpc": "1.0", "id": "curltest", "method": "getblockchaininfo", "params": []}' -H 'content-type: text/plain;' http://152.136.130.81:8081/  
  - 获取所有RPC命令 $ ./qtum-cli help
  - 关闭qtum client $ ./qtum-cli stop
  - $ ./qtum-cli searchlogs 0 100 '{"addresses": ["b6c48b3a7c888713dd96eed92a4ee0397dd64e71"]}' '{"topics": []}'
  - $ ./qtum-cli searchlogs 99995 100100 '{"addresses": ["f2703e93f87b846a7aacec1247beaec1c583daa4"]}'
  - $ curl --user test:test1234 --data-binary "{\"jsonrpc\": \"1.0\", \"id\":\"curltest\", \"method\": \"searchlogs\", \"params\": [99995 100100 {\"addresses\": [\"f2703e93f87b846a7aacec1247beaec1c583daa4\"]} ]}" -H 'content-type: text/plain;' http://152.136.130.81:8081/
  - $   
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

## Ubuntu ipfs Info
### Node路径 path
```shell
docker run --name ipfs-node-0 -v /home/ubuntu/ipfs/nodes/node0/export:/export -v /home/ubuntu/ipfs/nodes/node0/data:/data/ipfs -p 10000:4001 -p 10001:5001 -p 10002:8080 -d ipfs/go-ipfs:latest
	
docker run -d --name ipfs_node_1 -e IPFS_SWARM_KEY_FILE=/home/ubuntu/ipfs/ipfs/swarm.key -v /home/ubuntu/ipfs/node1/staging:/export -v /home/ubuntu/ipfs/node1/data:/data/ipfs -p 4001:4001 -p 4001:4001/udp -p 127.0.0.1:8080:8080 -p 127.0.0.1:5001:5001 ipfs/go-ipfs:latest
docker run -d --name ipfs_node_2 -e IPFS_SWARM_KEY_FILE=/home/ubuntu/ipfs/ipfs/swarm.key -v /home/ubuntu/ipfs/node2/staging:/export -v /home/ubuntu/ipfs/node2/data:/data/ipfs -p 4002:4001 -p 4002:4001/udp -p 127.0.0.1:8082:8080 -p 127.0.0.1:5002:5001 ipfs/go-ipfs:latest


docker exec ipfs_node_1 ipfs bootstrap add  /ip4/172.17.0.2/tcp/4001/p2p/12D3KooWQVPih9JMVFuUX3yFM7n3zWrnWsxAmWxgMv64JiQX7XwH
docker exec ipfs_node_2 ipfs bootstrap add  /ip4/172.17.0.3/tcp/4001/p2p/12D3KooWGakBbN6i4sUiwJX1zfpmoCEdTVhULhNhtWuy9ZiAx2Yh

```

## CentOS Jekins 运行脚本
```shell
#将打包好的jar项目，移到/opt/data/build目录
#/var/jenkins_home/workspace/SpringBoot_AutoTest/target/jenkins-0.0.1-SNAPSHOT.jar构建好的jar路径
mv /var/lib/jenkins/workspace/movie-ntf-backen/target/movie-ntf-backen-0.0.1-SNAPSHOT.jar /root/app
#切换目录到/opt/data/build
cd /root/app
#执行构建Dockerfile命令
docker build ./ -t my/movie-ntf-backen
#停止之前的容器运行
docker stop movie-ntf-backen
#删除之前的容器
docker rm movie-ntf-backen 
#运行刚刚创建的容器
docker run -d --name movie-ntf-backen -p 8081:8081 my/movie-ntf-backen
echo "构建完成"
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

