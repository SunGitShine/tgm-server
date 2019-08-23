mbg生成mapper的命令
覆盖现有的文件:
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
直接生成:
mvn mybatis-generator:generate