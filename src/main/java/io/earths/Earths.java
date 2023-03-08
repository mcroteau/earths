package io.earths;

import net.plsar.*;
import net.plsar.drivers.Drivers;
import net.plsar.environments.Environments;
import net.plsar.schemes.RenderingScheme;
import net.plsar.security.renderer.AuthenticatedRenderer;
import net.plsar.security.renderer.GuestRenderer;

public class Earths {
    public static void main(String[] args){
        PLSAR plsar = new PLSAR(1701);
        plsar.setNumberOfPartitions(300);
        plsar.setNumberOfRequestExecutors(700);

        PersistenceConfig persistenceConfig = new PersistenceConfig();
        persistenceConfig.setDriver(Drivers.H2);
        persistenceConfig.setUrl("jdbc:h2:~/Earths");
        persistenceConfig.setUser("sa");
        persistenceConfig.setPassword("");
        persistenceConfig.setDebug(true);

        SchemaConfig schemaConfig = new SchemaConfig();
        schemaConfig.setSchema("schema.sql");
        schemaConfig.setEnvironment(Environments.DEVELOPMENT);

        persistenceConfig.setSchemaConfig(schemaConfig);

        PropertiesConfig propertiesConfig = new PropertiesConfig();
        propertiesConfig.setPropertiesFile("earths.properties");

        plsar.addViewRenderer(AuthenticatedRenderer.class);
        plsar.addViewRenderer(GuestRenderer.class);

        ViewConfig viewConfig = new ViewConfig();
        viewConfig.setResourcesPath("assets");
        viewConfig.setRenderingScheme(RenderingScheme.RELOAD_EACH_REQUEST);

        plsar.setSecurityAccess(AuthSecurityAccess.class);

        plsar.setViewConfig(viewConfig);
        plsar.setPropertiesConfig(propertiesConfig);
        plsar.setPersistenceConfig(persistenceConfig);

        plsar.start();
    }

}