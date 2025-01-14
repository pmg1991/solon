package org.noear.solon.extend.sqltoy;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.noear.solon.Utils;
import org.noear.solon.core.AopContext;
import org.noear.solon.core.Props;

import java.util.Arrays;

/**
 * MongoClient 初始化器（用于拆分 XPluginImp 类）
 *
 * @author 夜の孤城
 * @author noear
 * @since 1.8
 */
class SqlToyMongoInit {
    public static void tryBuildMongoDbClient(AopContext context) {
        Class<?> mongoClz = Utils.loadClass(context.getClassLoader(), "com.mongodb.client.MongoDatabase");

        if (mongoClz == null) {
            return;
        }

        MongoDatabase mongodb = context.getBean(MongoDatabase.class);
        if (mongodb == null) {
            Props props = context.getProps().getProp("data.mongodb");
            if (props == null) {
                return;
            }
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            //每个地址的最大连接数
            builder.connectionsPerHost(props.getInt("connectionsPerHost", 10));
            //连接超时时间
            builder.connectTimeout(props.getInt("connectTimeout", 5000));
            //设置读写操作超时时间
            builder.socketTimeout(props.getInt("socketTimeout", 5000));

            //封装MongoDB的地址与端口
            ServerAddress address = new ServerAddress(props.get("host", "127.0.0.1"), props.getInt("port", 27017));

            String databaseName = props.get("database", "test");
            MongoClient client = null;
            //认证
            if (props.contains("username")) {
                MongoCredential credential = MongoCredential.createCredential(props.get("username"), databaseName, props.get("password", "").toCharArray());
                client = new MongoClient(address, Arrays.asList(credential), builder.build());
            } else {
                client = new MongoClient(address, builder.build());
            }
            //client
            MongoDatabase database = client.getDatabase(databaseName);
            context.wrapAndPut(MongoDatabase.class, database);
        }

    }
}
