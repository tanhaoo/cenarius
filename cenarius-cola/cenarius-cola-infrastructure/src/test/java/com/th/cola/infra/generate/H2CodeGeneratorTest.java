package com.th.cola.infra.generate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.query.SQLQuery;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Aaron
 * @Date: 2023/7/7
 */
public class H2CodeGeneratorTest {

    /**
     * 执行初始化数据库脚本
     */
    @BeforeAll
    public static void before() throws SQLException {
        initDataSource(DATA_SOURCE_CONFIG);
    }

    /**
     * 执行数据库脚本
     */
    protected static void initDataSource(DataSourceConfig dataSourceConfig) throws SQLException {
        Connection conn = dataSourceConfig.getConn();
        InputStream schema = H2CodeGeneratorTest.class.getResourceAsStream("/sql/schema.sql");
        InputStream data = H2CodeGeneratorTest.class.getResourceAsStream("/sql/data.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(schema));
        scriptRunner.runScript(new InputStreamReader(data));
        conn.close();
    }


    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL",
            "root",
            "root")
            .databaseQueryClass(SQLQuery.class) // 设置SQL查询方式，默认的是元数据查询方式
            .build();

    private static final String projectPath = getProjectPath();
    private static final String projectName = "cenarius-cola";
    private static final String parentPackagePath = "com.th.cola";
    private static final String packagePath = "/com/th/cola";
    private static final String codeRoot = "/src/main/java";
    private static final String resourceRoot = "/src/main/resources";
    private static final String mapperRoot = "/mybatis/mapper";
    private static final String CONTROLLER_PATH = projectPath + "/" + projectName + "-adapter" + codeRoot + packagePath + "/adapter/web";
    private static final String SERVICE_PATH = projectPath + "/" + projectName + "-client" + codeRoot + packagePath + "/client/api";
    private static final String SERVICE_IMPL_PATH = projectPath + "/" + projectName + "-app" + codeRoot + packagePath + "/app/service";
    private static final String CMD_EXECUTE_PATH = projectPath + "/" + projectName + "-app" + codeRoot + packagePath + "/app/executor/command";
    private static final String QRY_EXECUTE_PATH = projectPath + "/" + projectName + "-app" + codeRoot + packagePath + "/app/executor/query";
    private static final String APP_CONVERTOR_PATH = projectPath + "/" + projectName + "-app" + codeRoot + packagePath + "/app/convertor";
    private static final String GATEWAY_PATH = projectPath + "/" + projectName + "-domain" + codeRoot + packagePath + "/domain/gateway";
    private static final String DOMAIN_PATH = projectPath + "/" + projectName + "-domain" + codeRoot + packagePath + "/domain/model";
    private static final String CLIENT_DTO_DATA_PATH = projectPath + "/" + projectName + "-client" + codeRoot + packagePath + "/client/dto/data";
    private static final String CLIENT_DTO_CMD_PATH = projectPath + "/" + projectName + "-client" + codeRoot + packagePath + "/client/dto/command";
    private static final String CLIENT_DTO_QUERY_PATH = projectPath + "/" + projectName + "-client" + codeRoot + packagePath + "/client/dto/query";
    private static final String GATEWAY_IMPL_PATH = projectPath + "/" + projectName + "-infrastructure" + codeRoot + packagePath + "/infra/gateway";
    private static final String ENTITY_PATH = projectPath + "/" + projectName + "-infrastructure" + codeRoot + packagePath + "/infra/gateway/database/dataobject";
    private static final String MAPPER_PATH = projectPath + "/" + projectName + "-infrastructure" + codeRoot + packagePath + "/infra/gateway/database/mapper";
    private static final String CONVERTOR_PATH = projectPath + "/" + projectName + "-infrastructure" + codeRoot + packagePath + "/infra/convertor";
    private static final String XML_PATH = projectPath + "/" + projectName + "-infrastructure" + resourceRoot + mapperRoot;

    private static String getProjectPath() {
        String path = System.getProperty("user.dir");
        return path.substring(0, path.lastIndexOf("/"));
    }

    @Test
    public void generateCustomFile() {
        // 数据库连接地址
        String url = "jdbc:h2:mem:testDB;AUTO_SERVER=TRUE";
        // 数据库登录账号
        String username = "root";
        // 数据库登录密码
        String password = "root";
        // 设置 mapper Xml 文件输出路径(可以是项目外的位置，也可以直接生成到当前项目中)
//        String xmlOutputDir = property + "/resources/mybatis/mapper";
        // 设置父包名

        // 设置作者姓名，用于展示在类上 @author
        String author = "Aaron";
        // 设置需要生成代码的表名，允许正则表达式。为空时，将默认为所有表生成代码
        String[] includeTable = {"tb_sales_order"
//                , "tb_sales_order_item"
        };
        // 设置需要排除的表名，允许正则表达式
        String[] excludeTable = {};
        // 设置过滤表前缀
        String[] tablePrefix = {"tb_", "temp_", "backup_", "bak_"};
        // 设置过滤表后缀
        String[] tableSuffix = {"_temp", "_backup", "_bak"};

        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(author)
                            .commentDate("yyyy-MM-dd")
//                            .enableSpringdoc()
                            // 是否开启swagger模式，开启之后，生成的代码中会自动带上一些Swagger注解
//                            .enableSwagger()
                            // 是否禁止打开输出目录
                            .disableOpenDir()
//                            .outputDir(javaOutputDir)
                    ;
                })
                // 包配置, 这里的配置是用于给每个生成的文件定义 package 变量的
                .packageConfig(builder -> {
                    builder
                            .parent(parentPackagePath)
//                            .moduleName(parentModuleName)
                            .controller("adapter.web")
                            .service("client.api")
                            .serviceImpl("app.service")
                            .entity("infra.gateway.database.dataobject")
                            .mapper("infra.gateway.database.mapper")

                            .pathInfo(getPathInfo());
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            .addInclude(includeTable)
                            .addExclude(excludeTable)
                            .addTablePrefix(tablePrefix)
                            .addTableSuffix(tableSuffix)

                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")

                            .serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            .entityBuilder()
                            .formatFileName("%sDO")
                            .addTableFills(new Property("createTime", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            .versionColumnName("version")
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableRemoveIsPrefix()
//                            .enableActiveRecord()
                            .idType(IdType.ASSIGN_ID)
                            .enableLombok();
                })
                .injectionConfig(builder ->
                        builder.beforeOutputFile((tableInfo, map) -> {
                            setCustomMap(tableInfo, map);
                            builder.customFile(getCustomFiles(map));
                        }).build())
                // 模板配置
                // 模板引擎配置，可选模板引擎有：Velocity(默认)、Beetl、Freemarker、Enjoy。无论是谁，都必须导入对应的依赖，否则报错找不到类。
                .templateEngine(new EnhanceVelocityTemplateEngine())
                .execute();
        // 默认一个表会生成6个文件：1个Controller、1个Mapper接口、1个Xml文件、1个Service接口、1个Service接口实现、1个实体对象
        // 如果不想生成其中某个，则可以禁用模板生成相应的文件
        // .templateConfig(builder -> builder.disable(TemplateType.XML, TemplateType.CONTROLLER))
        // 执行
    }

    private static void setCustomMap(TableInfo tableInfo, Map<String, Object> map) {
        String domainName = tableInfo.getEntityName().substring(0, tableInfo.getEntityName().length() - 2);
        String lowerDomainName = Character.toLowerCase(domainName.charAt(0)) + domainName.substring(1);

        String serviceName = domainName + "Service";
        String lowerServiceName = Character.toLowerCase(domainName.charAt(0)) + domainName.substring(1) + "Service";

        String gatewayName = domainName + "Gateway";
        String lowerGatewayName = Character.toLowerCase(domainName.charAt(0)) + domainName.substring(1) + "Gateway";

        String convertorName = domainName + "Convertor";
        String lowerConvertorName = Character.toLowerCase(convertorName.charAt(0)) + convertorName.substring(1);

        String appConvertorName = domainName + "AppConvertor";

        map.put("domainName", domainName);
        map.put("lowerDomainName", lowerDomainName);
        map.put("serviceName", serviceName);
        map.put("lowerServiceName", lowerServiceName);
        map.put("gatewayName", gatewayName);
        map.put("lowerGatewayName", lowerGatewayName);
        map.put("convertorName", convertorName);
        map.put("lowerConvertorName", lowerConvertorName);
        map.put("appConvertorName", appConvertorName);

        map.put("appConvertorPackage", parentPackagePath + ".app.convertor");
        map.put("cmdExePackage", parentPackagePath + ".app.executor.command");
        map.put("qryExePackage", parentPackagePath + ".app.executor.query");
        map.put("dtoPackage", parentPackagePath + ".client.dto");
        map.put("domainPackage", parentPackagePath + ".domain.model");
        map.put("gatewayPackage", parentPackagePath + ".domain.gateway");
        map.put("gatewayImplPackage", parentPackagePath + ".infra.gateway");
        map.put("convertorPackage", parentPackagePath + ".infra.convertor");

    }

    private static Map<OutputFile, String> getPathInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH);
        pathInfo.put(OutputFile.mapper, MAPPER_PATH);
        pathInfo.put(OutputFile.service, SERVICE_PATH);
        pathInfo.put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
        pathInfo.put(OutputFile.controller, CONTROLLER_PATH);
        pathInfo.put(OutputFile.xml, XML_PATH);
        return pathInfo;
    }

    public List<CustomFile> getCustomFiles(Map<String, Object> map) {
        ArrayList<CustomFile> files = new ArrayList<>();
        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "Convertor" + StringPool.DOT_JAVA)
                .filePath(CONVERTOR_PATH)
                .templatePath("templates/convertor.java.vm")
                .build());

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + StringPool.DOT_JAVA)
                .filePath(DOMAIN_PATH)
                .templatePath("templates/domain.java.vm")
                .build());

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "DTO" + StringPool.DOT_JAVA)
                .filePath(CLIENT_DTO_DATA_PATH)
                .templatePath("templates/data.java.vm")
                .build());

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "CreateCmd" + StringPool.DOT_JAVA)
                .filePath(CLIENT_DTO_CMD_PATH)
                .templatePath("templates/createCmd.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "ReviseCmd" + StringPool.DOT_JAVA)
                .filePath(CLIENT_DTO_CMD_PATH)
                .templatePath("templates/reviseCmd.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "PageQuery" + StringPool.DOT_JAVA)
                .filePath(CLIENT_DTO_QUERY_PATH)
                .templatePath("templates/pageQuery.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName("I" + map.get("gatewayName") + StringPool.DOT_JAVA)
                .filePath(GATEWAY_PATH)
                .templatePath("templates/gateway.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("gatewayName") + StringPool.DOT_JAVA)
                .filePath(GATEWAY_IMPL_PATH)
                .templatePath("templates/gatewayImpl.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "AppConvertor" + StringPool.DOT_JAVA)
                .filePath(APP_CONVERTOR_PATH)
                .templatePath("templates/appConvertor.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "CreateCmdExe" + StringPool.DOT_JAVA)
                .filePath(CMD_EXECUTE_PATH)
                .templatePath("templates/createCmdExe.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "ReviseCmdExe" + StringPool.DOT_JAVA)
                .filePath(CMD_EXECUTE_PATH)
                .templatePath("templates/reviseCmdExe.java.vm")
                .build()
        );


        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "RemoveCmdExe" + StringPool.DOT_JAVA)
                .filePath(CMD_EXECUTE_PATH)
                .templatePath("templates/removeCmdExe.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "ByIdQryExe" + StringPool.DOT_JAVA)
                .filePath(QRY_EXECUTE_PATH)
                .templatePath("templates/byIdQryExe.java.vm")
                .build()
        );

        files.add(new CustomFile.Builder()
                .fileName(map.get("domainName") + "ByPageQryExe" + StringPool.DOT_JAVA)
                .filePath(QRY_EXECUTE_PATH)
                .templatePath("templates/byPageQryExe.java.vm")
                .build()
        );

        return files;
    }

}
