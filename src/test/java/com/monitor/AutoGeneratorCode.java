package com.monitor;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


public class AutoGeneratorCode {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir").concat("/src/main/java"));
        gc.setAuthor("SGao");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setDateType(DateType.TIME_PACK);
        mpg.setGlobalConfig(gc);

        // 2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        MySqlQuery mySqlQuery = new MySqlQuery();
        dsc.setUrl("jdbc:mysql://gz-cynosdbmysql-grp-af58oat5.sql.tencentcdb.com:20364/erlangtest");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("erlangtest*8");
        dsc.setDbQuery(mySqlQuery);
        dsc.setDbType(DbType.MYSQL);
        dsc.setSchemaName("erlangtest");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wit.pano");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setXml("mapper");
        pc.setEntity("po");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("p_project","p_scene","p_pano_icon","p_hotspot");  // 设置要映射的表名
        strategy.setTablePrefix("p_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setLogicDeleteFieldName("deleted"); // 逻辑删除字段
        // 自动填充配置
//        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
//
//        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
//
//        ArrayList<TableFill> tableFillList = new ArrayList<>();
//
//        tableFillList.add(createTime);
//        tableFillList.add(updateTime);
//
//        strategy.setTableFillList(tableFillList);
        strategy.setControllerMappingHyphenStyle(true);  // http://localhost:8080/hello_id_2


        mpg.setStrategy(strategy);
        mpg.execute();

    }
}