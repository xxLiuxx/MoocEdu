package com.xxLiuxx.statservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class CodeGenerator {

  @Test
  public void run() {

    // 1. create code generator
    AutoGenerator mpg = new AutoGenerator();

    // 2. global config
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir("/Users/liuyuchen/IdeaProjects/OnlineEdu/management/backend/mooc_parent/service/service_statistics/src/main/java");
    gc.setAuthor("Yuchen Liu");
    gc.setOpen(true); //open the directories after code generation
    gc.setFileOverride(true); //overwrite the files when updating
    gc.setServiceName("%sService");
    gc.setIdType(IdType.ASSIGN_ID); //primary key strategy
    gc.setDateType(DateType.ONLY_DATE);
    gc.setSwagger2(true);//open Swagger2

    mpg.setGlobalConfig(gc);

    // 3. datasource config
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl("jdbc:mysql://localhost:3306/online_edu?userSSL=false&&serverTimezone=GMT%2B8");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("supreme150211");
    dsc.setDbType(DbType.MYSQL);
    mpg.setDataSource(dsc);

    // 4. package config
    PackageConfig pc = new PackageConfig();
    pc.setModuleName("statservice");
    pc.setParent("com.xxLiuxx");
    pc.setController("controller");
    pc.setEntity("entity");
    pc.setService("service");
    pc.setMapper("mapper");
    mpg.setPackageInfo(pc);

    // 5. strategy config
    StrategyConfig strategy = new StrategyConfig();
    strategy.setInclude("statistics_daily");
    strategy.setNaming(NamingStrategy.underline_to_camel);//name strategy when mapping the database's name to entity
    strategy.setTablePrefix(pc.getModuleName() + "_");

    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(false);

    strategy.setRestControllerStyle(true);
    strategy.setControllerMappingHyphenStyle(true);

    mpg.setStrategy(strategy);


    mpg.execute();
  }
}
