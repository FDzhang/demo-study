package com.example.demomybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用说明：
 * 1、修改 projectPath author packageParent 参数
 * 2、修改 moduleName 参数 (可以不填)
 * 3、修改 数据库配置
 * 4、修改 tableNames （需要进行代码生成的 表名）
 *
 * @author zhangxinqiang
 * @create 2021/5/13 15:37
 */
public class CodeGenerator {


    private static final String projectPath = System.getProperty("user.dir") + "/demo-springboot/demo-mybatisplus";
    private static final String outputDir = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java";
    private static String outputDirMapper = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper";

    private final static String author = "zxq";
    private final static String packageParent = "com.example.demomybatisplus.gen";
    private final static String moduleName = "target";

    private final static String[] tableNames = {"target_manage", "time_record"};

    static {
        if (!"".equals(moduleName)){
            outputDirMapper += (File.separator + moduleName);
        }
    }

    public static void main(String[] args) {
        System.out.println("分隔符: " + File.separator);
        System.out.println("projectPath: " + projectPath);
        System.out.println("outputDir: " + outputDir);
        System.out.println("outputDirMapper: " + outputDirMapper);

        if (tableNames.length < 1) {
            System.err.println("没有设置数据表名称： tableNames 为空");
            return;
        }

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        setGlobalConfig(mpg);
        // 数据源配置
        setDataSourceConfig(mpg);
        // 包配置
        setPackageConfig(mpg);
        // 策略配置
        setStrategyConfig(mpg);
        // 自定义配置
        setInjectionConfig(mpg);

        // 模板配置
        setTemplateConfig(mpg);
        // 模板引擎配置
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    /**
     * 数据源配置
     */
    private static void setDataSourceConfig(AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://ip/target_manage_time_record?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("xxxx");

        mpg.setDataSource(dsc);
    }

    /**
     * 全局配置
     */
    private static void setGlobalConfig(AutoGenerator mpg) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);

        // 是否打开输出目录
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setDateType(DateType.ONLY_DATE);

        gc.setActiveRecord(true);
        // xml相关
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        mpg.setGlobalConfig(gc);
    }


    private static void setTemplateConfig(AutoGenerator mpg) {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        // 不生成 mapper.xml文件
        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);
    }

    private static void setInjectionConfig(AutoGenerator mpg) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 如果模板引擎是 freemarker, 则后缀是 ftl
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名，如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outputDirMapper + File.separator + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);
    }

    private static void setStrategyConfig(AutoGenerator mpg) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass();  // 你自己的父类实体,没有就不用设置!
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setInclude(tableNames);

        mpg.setStrategy(strategy);
    }

    /**
     * 包配置
     */
    private static void setPackageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(packageParent);

        mpg.setPackageInfo(pc);
    }


}
