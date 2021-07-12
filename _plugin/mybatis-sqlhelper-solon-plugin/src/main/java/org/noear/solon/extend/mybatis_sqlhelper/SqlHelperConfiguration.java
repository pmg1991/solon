package org.noear.solon.extend.mybatis_sqlhelper;

import com.jn.sqlhelper.dialect.instrument.SQLInstrumentorConfig;
import com.jn.sqlhelper.mybatis.MybatisUtils;
import com.jn.sqlhelper.mybatis.SqlHelperMybatisProperties;
import com.jn.sqlhelper.mybatis.plugins.CustomScriptLanguageDriver;
import com.jn.sqlhelper.mybatis.plugins.SqlHelperMybatisPlugin;
import com.jn.sqlhelper.mybatis.plugins.pagination.PaginationConfig;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.Configuration;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.event.EventListener;
import org.noear.solon.core.util.PrintUtil;

/**
 * SqlHelper 分布插件配置器（添加拦截器）
 *
 * @author noear
 * @since 1.1
 * */
@org.noear.solon.annotation.Configuration
public class SqlHelperConfiguration implements EventListener<Configuration> {

    private SqlHelperMybatisProperties sqlHelperMybatisProperties;

    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        return MybatisUtils.vendorDatabaseIdProvider();
    }

    @Bean
    public void sqlHelperMybatisProperties(
            @Inject("${sqlhelper.mybatis.instrumentor}") SQLInstrumentorConfig sqlInstrumentConfig,
            @Inject("${sqlhelper.mybatis.pagination}") PaginationConfig paginationPluginConfig) {
        sqlHelperMybatisProperties = new SqlHelperMybatisProperties();
        sqlHelperMybatisProperties.setInstrumentor(sqlInstrumentConfig);
        sqlHelperMybatisProperties.setPagination(paginationPluginConfig);
    }


    @Override
    public void onEvent(Configuration configuration) {
        if(sqlHelperMybatisProperties == null){
            return;
        }

        PrintUtil.info("Start to customize mybatis configuration with mybatis-sqlhelper-solon-plugin");
        configuration.setDefaultScriptingLanguage(CustomScriptLanguageDriver.class);

        SqlHelperMybatisPlugin plugin = new SqlHelperMybatisPlugin();
        plugin.setPaginationConfig(sqlHelperMybatisProperties.getPagination());
        plugin.setInstrumentorConfig(sqlHelperMybatisProperties.getInstrumentor());
        plugin.init();

        PrintUtil.info(String.format("Add interceptor {} to mybatis configuration", plugin));
        PrintUtil.info(String.format("The properties of the mybatis plugin [{}] is: {}", SqlHelperMybatisPlugin.class.getName(), sqlHelperMybatisProperties));
        configuration.addInterceptor(plugin);
    }
}
