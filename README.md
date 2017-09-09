Redis cache
Redis 是完全开源免费的，遵守BSD协议，是一个高性能的key-value数据库。

1.	支持数据的持久化，可以将内存的数据存储在磁盘中，下次启动时再次加载使用
2.	除了支持key-value类型以外，还支持String，list，set，zset，hash等数据结构
3.	支持数据的备份，即master-slave结构的数据备份，主从备份的时候要确保master激活了持久化，或不会再当掉后自动重启，因为master，slave的数据完全一致，如果master通过一个空数据集重启，那么slave的数据也会被冲掉

Redis优势：
1.	性能高 --- 读：11万次/s 写：8万次/s
2.	丰富的数据类型
3.	原子性 --- 所有操作都是原子性的，且支持多个操作合并后的原子性
4.	丰富的特性 --- 支持key过期，通知，publish/subscribe

Redis命令：
运行redis：redis-cli.exe -h 127.0.0.1 -p 6379
开启客户端： redis-cli -h 127.0.0.1 -p 6379 -a ""
Redis 数据类型
Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。
________________________________________1. String（字符串）
string是redis最基本的类型，你可以理解成与Memcached一模一样的类型，一个key对应一个value。
string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。
string类型是Redis最基本的数据类型，一个键最大能存储512MB。
实例
redis 127.0.0.1:6379> SET name "runoob"
OK
redis 127.0.0.1:6379> GET name
"runoob"
在以上实例中我们使用了 Redis 的 SET 和 GET 命令。键为 name，对应的值为 runoob。
注意：一个键最大能存储512MB。
2.	Hash（哈希）
Redis hash 是一个键名对集合。
Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。
实例
127.0.0.1:6379> HMSET user:1 username runoob password runoob points 200
OK
127.0.0.1:6379> HGETALL user:1
1) "username"
2) "runoob"
3) "password"
4) "runoob"
5) "]points"
6) "200"
以上实例中 hash 数据类型存储了包含用户脚本信息的用户对象。 实例中我们使用了 Redis HMSET, HGETALL 命令，user:1为键值。
每个 hash 可以存储 232 -1 键值对（40多亿）。
3.	List（列表）
Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。
实例
redis 127.0.0.1:6379> lpush runoob redis
(integer) 1
redis 127.0.0.1:6379> lpush runoob mongodb
(integer) 2
redis 127.0.0.1:6379> lpush runoob rabitmq
(integer) 3
redis 127.0.0.1:6379> lrange runoob 0 10
1) "rabitmq"
2) "mongodb"
3) "redis"
redis 127.0.0.1:6379>
列表最多可存储 232 - 1 元素 (4294967295, 每个列表可存储40多亿)。
4.	Set（集合）
Redis的Set是string类型的无序集合。
集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
sadd 命令
添加一个string元素到,key对应的set集合中，成功返回1,如果元素已经在集合中返回0,key对应的set不存在返回错误。
sadd key member
实例
redis 127.0.0.1:6379> sadd runoob redis
(integer) 1
redis 127.0.0.1:6379> sadd runoob mongodb
(integer) 1
redis 127.0.0.1:6379> sadd runoob rabitmq
(integer) 1
redis 127.0.0.1:6379> sadd runoob rabitmq
(integer) 0
redis 127.0.0.1:6379> smembers runoob

1) "rabitmq"
2) "mongodb"
3) "redis"
注意：以上实例中 rabitmq 添加了两次，但根据集合内元素的唯一性，第二次插入的元素将被忽略。
集合中最大的成员数为 232 - 1(4294967295, 每个集合可存储40多亿个成员)。
5.	zset(sorted set：有序集合)
Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。
不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
zset的成员是唯一的,但分数(score)却可以重复。
zadd 命令
添加元素到集合，元素在集合中存在则更新对应score
zadd key score member
实例
redis 127.0.0.1:6379> zadd runoob 0 redis
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 mongodb
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 rabitmq
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 rabitmq
(integer) 0
redis 127.0.0.1:6379> ZRANGEBYSCORE runoob 0 1000

1) "redis"
2) "mongodb"
3) "rabitmq"

Redis HyperLogLog
用于基数统计算法。在输入元素的体积或者数据非常大的情况下，计算基数需要的空间是固定很小非常小的。
基数：不重复的元素
{1，2，4，5，4, 5， 6} =》{1， 2, 4， 5， 6}

Redis 发布订阅
发布（pub）订阅（sub）是一种消息通信方式，发布者发布消息，订阅者接收消息。


多起几个客户端
在订阅者的客户端执行 subscribe channelName，然后该客户端进入阻塞式接听状态
在发布者的客户端发布 publish channelName message，所有的客户端都会收到消息


先声明订阅者然后再发布者再发消息

Redis demo
1.	配置文件
redis.properties -> redis的port，host，password，pool连接数，连接时间等
spring-redis.xml -> 将redis.properties配置进来
web.xml -> 将spring-redis.xml配置进来
pom.xml -> 添加redis依赖
2.	Jedis操作步骤：
1）	从JedisPool中获取jedis实例
2）	调用set，get，lpush等一系列方法操作jedis
3）	用完jedis以后要返还给pool，如果使用过程中抛异常，要用returnBrokenResource的形式返还给pool
获取jedis实例时，可能会有两种类型的错误，一种是pool里去redis时未取到可用的jedis实例，另一种是jedis在set，get等操作时抛出的异常。 对于未取到jedis实例的，只要判断jedis是否为空，为空就不用返回给pool了
抛出异常一定要用returnBrokenResource返回给pool，不然下次getResource时缓存区还存在数据，出现问题。

JedisPool通过JedisPoolConfig来配置
