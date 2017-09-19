# External Database

Here we use the H2 in-memory database through a JDBC data source and `QueryRunner`.

Points of interest:

- In [build.gradle](https://github.com/cuba-labs/external-db/blob/master/build.gradle) in the `core` module there is a dependency on the H2 driver. It can be added in Studio, see *Project Properties > Advanced > Dependencies*.

- A data source is added to [context.xml](https://github.com/cuba-labs/external-db/blob/master/modules/core/web/META-INF/context.xml) of the `core` module.

- The data source is registered as a Spring bean in [spring.xml](https://github.com/cuba-labs/external-db/blob/master/modules/core/src/com/company/externaldb/spring.xml) of the `core` module.

- In [MyServiceBean](https://github.com/cuba-labs/external-db/blob/master/modules/core/src/com/company/externaldb/service/MyServiceBean.java) service, the data source is injected and is used in the `loadData` method.
