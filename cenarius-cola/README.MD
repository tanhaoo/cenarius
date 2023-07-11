## 1 根据创建的模板生成maven骨架

``
mvn archetype:create-from-project
``

## 2 执行 mvn install

进入到 target/generated-sources/archetype/ 目录下执行mvn install
成功后就会在本地 repo仓库中有个配置文件archetype-catalog.xml

``
mvn install
``

## 3 deploy 到私服

在target/generated-sources/archetype/pom.xml 文件里加入私服地址

``` xml
<distributionManagement>
<repository>
<id>nexus-releases</id>
<url>http://1.1.1.1:19012/nexus/content/repositories/releases/</url>
</repository>
<snapshotRepository>
<id>nexus-snapshots</id>
<url>http://1.1.1.1:19012/nexus/content/repositories/snapshots/</url>
</snapshotRepository>
</distributionManagement>
```

然后再target/generated-sources/archetype/ 下 执行

``
mvn deploy
``

## 4 本地使用

```` shell
  mvn archetype:generate 
    -DarchetypeGroupId=com.test 
    -DarchetypeArtifactId=test-project-archetype 
    -DarchetypeVersion=1.0-SNAPSHOT 
    -DarchetypeCatalog=local 
    -DgroupId=com.test 
    -DartifactId=test 
    -Dversion=1.0-SNAPSHOT
````