Cloudera Quickstart Image On Docker
--

### Pre-Requisite

- Docker Installation
```
- Docker on Mac => https://docs.docker.com/docker-for-mac/install/
- Docker on windows => https://docs.docker.com/docker-for-windows/install/
```

- Docker Validation
```
$ docker version
Client: Docker Engine - Community
 Version:           18.09.2
 API version:       1.39
 Go version:        go1.10.8
 Git commit:        6247962
 Built:             Sun Feb 10 04:12:39 2019
 OS/Arch:           darwin/amd64
 Experimental:      false

Server: Docker Engine - Community
 Engine:
  Version:          18.09.2
  API version:      1.39 (minimum version 1.12)
  Go version:       go1.10.6
  Git commit:       6247962
  Built:            Sun Feb 10 04:13:06 2019
  OS/Arch:          linux/amd64
  Experimental:     false
```

### Importing the Cloudera QuickStart Image

- Import the Cloudera QuickStart image from Docker Hub:
```
$ docker pull cloudera/quickstart:latest
```

- Validate the downloaded image
```
$ docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
cloudera/quickstart   latest              4239cd2958c6        3 years ago         6.34GB
```

### Running a Cloudera QuickStart Container

- Start the docker container for cloudera quickstart image
```
$ docker run --hostname=quickstart.cloudera --privileged=true -t -i -p 8888:8888 -p 10000:10000 -p 10020:10020 -p 11000:11000 -p 18080:18080 -p 18081:18081 -p 18088:18088 -p 19888:19888 -p 21000:21000 -p 21050:21050 -p 2181:2181 -p 25000:25000 -p 25010:25010 -p 25020:25020 -p 50010:50010 -p 50030:50030 -p 50060:50060 -p 50070:50070 -p 50075:50075 -p 50090:50090 -p 60000:60000 -p 60010:60010 -p 60020:60020 -p 60030:60030 -p 7180:7180 -p 7183:7183 -p 7187:7187 -p 80:80 -p 8020:8020 -p 8032:8032 -p 802:8042 -p 8088:8088 -p 8983:8983 -p 9083:9083 4239cd2958c6 /usr/bin/docker-quickstart

=====
Note:
=====
-> If you do not pass the -d flag to docker run, your terminal will automatically attach to that of the container.
-> A container will die when you exit the shell, but you can disconnect and leave the container running by hitting Ctrl+P -> Ctrl+Q.
```

- Validate the running container
```
$ docker ps -a

CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     NAMES
a90e23375345        4239cd2958c6        "/usr/bin/docker-qui…"   21 hours ago        Up 5 hours          0.0.0.0:80->80/tcp, 0.0.0.0:2181->2181/tcp, 0.0.0.0:7180->7180/tcp, 0.0.0.0:7183->7183/tcp, 0.0.0.0:7187->7187/tcp, 0.0.0.0:8020->8020/tcp, 0.0.0.0:8032->8032/tcp, 0.0.0.0:8088->8088/tcp, 0.0.0.0:8888->8888/tcp, 0.0.0.0:8983->8983/tcp, 0.0.0.0:9083->9083/tcp, 0.0.0.0:10000->10000/tcp, 0.0.0.0:10020->10020/tcp, 0.0.0.0:11000->11000/tcp, 0.0.0.0:18080-18081->18080-18081/tcp, 0.0.0.0:18088->18088/tcp, 0.0.0.0:19888->19888/tcp, 0.0.0.0:21000->21000/tcp, 0.0.0.0:21050->21050/tcp, 0.0.0.0:25000->25000/tcp, 0.0.0.0:25010->25010/tcp, 0.0.0.0:25020->25020/tcp, 0.0.0.0:50010->50010/tcp, 0.0.0.0:50030->50030/tcp, 0.0.0.0:50060->50060/tcp, 0.0.0.0:50070->50070/tcp, 0.0.0.0:50075->50075/tcp, 0.0.0.0:50090->50090/tcp, 0.0.0.0:60000->60000/tcp, 0.0.0.0:60010->60010/tcp, 0.0.0.0:60020->60020/tcp, 0.0.0.0:60030->60030/tcp, 0.0.0.0:802->8042/tcp   romantic_bell
```

### Connecting to running container shell

- Start shell of running container
```
$ docker exec -it a90e23375345 /bin/bash

[root@quickstart /]# 
```

- Validate all java processed running under the container
```
[root@quickstart /]# jps
10224 RunJar
5677 SecondaryNameNode
6916 ThriftServer
10128 Main
5439 JournalNode
7737 HistoryServer
7047 RunJar
5327 DataNode
5535 NameNode
7250 RunJar
7699 Bootstrap
7873 HRegionServer
8119 Bootstrap
6662 RESTServer
5271 QuorumPeerMain
8558
17350 Jps
6492 HMaster
6184 ResourceManager
5986 NodeManager
8520 Bootstrap
8614
5818 Bootstrap
5884 JobHistoryServer
```

- Start `hive` shell in running container 
```
[root@quickstart /]# hive
2019-07-17 05:35:56,646 WARN  [main] mapreduce.TableMapReduceUtil: The hbase-prefix-tree module jar containing PrefixTreeCodec is not present.  Continuing without it.

Logging initialized using configuration in file:/etc/hive/conf.dist/hive-log4j.properties
WARNING: Hive CLI is deprecated and migration to Beeline is recommended.
hive> show databases;
OK
default
```

- Start `hbase shell` in running container 
```
[root@quickstart /]# hbase shell
2019-07-17 05:35:54,075 INFO  [main] Configuration.deprecation: hadoop.native.lib is deprecated. Instead, use io.native.lib.available
HBase Shell; enter 'help<RETURN>' for list of supported commands.
Type "exit<RETURN>" to leave the HBase Shell
Version 1.2.0-cdh5.7.0, rUnknown, Wed Mar 23 11:39:14 PDT 2016

hbase(main):001:0> list
TABLE
0 row(s) in 0.1570 seconds
```

- Validate `hadoop` specific commands
```
[root@quickstart /]# hadoop fs -ls /
Found 5 items
drwxrwxrwx   - hdfs  supergroup          0 2016-04-06 02:26 /benchmarks
drwxr-xr-x   - hbase supergroup          0 2019-07-17 05:03 /hbase
drwxrwxrwt   - hdfs  supergroup          0 2019-07-17 05:47 /tmp
drwxr-xr-x   - hdfs  supergroup          0 2019-07-16 12:06 /user
drwxr-xr-x   - hdfs  supergroup          0 2016-04-06 02:27 /var

[root@quickstart /]# hadoop fs -ls /user
Found 9 items
drwxr-xr-x   - cloudera cloudera            0 2016-04-06 02:25 /user/cloudera
drwxr-xr-x   - hdfs     supergroup          0 2019-07-16 12:06 /user/hdfs
drwxr-xr-x   - mapred   hadoop              0 2016-04-06 02:26 /user/history
drwxrwxrwx   - hive     supergroup          0 2016-04-06 02:27 /user/hive
drwxrwxrwx   - hue      supergroup          0 2016-04-06 02:26 /user/hue
drwxrwxrwx   - jenkins  supergroup          0 2016-04-06 02:26 /user/jenkins
drwxrwxrwx   - oozie    supergroup          0 2016-04-06 02:27 /user/oozie
drwxrwxrwx   - root     supergroup          0 2016-04-06 02:26 /user/root
drwxr-xr-x   - hdfs     supergroup          0 2016-04-06 02:27 /user/spark

[root@quickstart /]# hadoop fs -ls /user/hive
Found 1 items
drwxrwxrwx   - hive supergroup          0 2019-07-16 12:07 /user/hive/warehouse
```

### Access important services running under docker container using browser

- Validate Hue GUI using `http://localhost:8888/`
- Validate HDFS GUI using `http://localhost:50070/`
- Validate YARN GUI using `http://localhost:8088/`
- Validate HBase GUI using `http://localhost:60010/`

### Stop/Start a running container

- Stop the running container
```
$ docker ps

CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     NAMES
a90e23375345        4239cd2958c6        "/usr/bin/docker-qui…"   21 hours ago        Up 5 hours          0.0.0.0:80->80/tcp, 0.0.0.0:2181->2181/tcp, 0.0.0.0:7180->7180/tcp, 0.0.0.0:7183->7183/tcp, 0.0.0.0:7187->7187/tcp, 0.0.0.0:8020->8020/tcp, 0.0.0.0:8032->8032/tcp, 0.0.0.0:8088->8088/tcp, 0.0.0.0:8888->8888/tcp, 0.0.0.0:8983->8983/tcp, 0.0.0.0:9083->9083/tcp, 0.0.0.0:10000->10000/tcp, 0.0.0.0:10020->10020/tcp, 0.0.0.0:11000->11000/tcp, 0.0.0.0:18080-18081->18080-18081/tcp, 0.0.0.0:18088->18088/tcp, 0.0.0.0:19888->19888/tcp, 0.0.0.0:21000->21000/tcp, 0.0.0.0:21050->21050/tcp, 0.0.0.0:25000->25000/tcp, 0.0.0.0:25010->25010/tcp, 0.0.0.0:25020->25020/tcp, 0.0.0.0:50010->50010/tcp, 0.0.0.0:50030->50030/tcp, 0.0.0.0:50060->50060/tcp, 0.0.0.0:50070->50070/tcp, 0.0.0.0:50075->50075/tcp, 0.0.0.0:50090->50090/tcp, 0.0.0.0:60000->60000/tcp, 0.0.0.0:60010->60010/tcp, 0.0.0.0:60020->60020/tcp, 0.0.0.0:60030->60030/tcp, 0.0.0.0:802->8042/tcp   romantic_bell

$ docker stop a90e23375345
```

- Start the stopped container
```
$ docker ps -a

CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     NAMES
a90e23375345        4239cd2958c6        "/usr/bin/docker-qui…"   22 hours ago        Up 5 hours          0.0.0.0:80->80/tcp, 0.0.0.0:2181->2181/tcp, 0.0.0.0:7180->7180/tcp, 0.0.0.0:7183->7183/tcp, 0.0.0.0:7187->7187/tcp, 0.0.0.0:8020->8020/tcp, 0.0.0.0:8032->8032/tcp, 0.0.0.0:8088->8088/tcp, 0.0.0.0:8888->8888/tcp, 0.0.0.0:8983->8983/tcp, 0.0.0.0:9083->9083/tcp, 0.0.0.0:10000->10000/tcp, 0.0.0.0:10020->10020/tcp, 0.0.0.0:11000->11000/tcp, 0.0.0.0:18080-18081->18080-18081/tcp, 0.0.0.0:18088->18088/tcp, 0.0.0.0:19888->19888/tcp, 0.0.0.0:21000->21000/tcp, 0.0.0.0:21050->21050/tcp, 0.0.0.0:25000->25000/tcp, 0.0.0.0:25010->25010/tcp, 0.0.0.0:25020->25020/tcp, 0.0.0.0:50010->50010/tcp, 0.0.0.0:50030->50030/tcp, 0.0.0.0:50060->50060/tcp, 0.0.0.0:50070->50070/tcp, 0.0.0.0:50075->50075/tcp, 0.0.0.0:50090->50090/tcp, 0.0.0.0:60000->60000/tcp, 0.0.0.0:60010->60010/tcp, 0.0.0.0:60020->60020/tcp, 0.0.0.0:60030->60030/tcp, 0.0.0.0:802->8042/tcp   romantic_bell

$ docker start a90e23375345
```
