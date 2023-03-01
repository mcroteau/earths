package io.earths;

import net.plsar.*;
import net.plsar.drivers.Drivers;
import net.plsar.environments.Environments;
import net.plsar.schemes.RenderingScheme;
import net.plsar.security.renderer.AuthenticatedRenderer;
import net.plsar.security.renderer.GuestRenderer;

public class Earths {
    public static void main(String[] args){
        STARGZR stargzr = new STARGZR(9876);
        stargzr.setNumberOfPartitions(300);
        stargzr.setNumberOfRequestExecutors(700);
        stargzr.setStartupExecutors(1);

        PersistenceConfig persistenceConfig = new PersistenceConfig();
        persistenceConfig.setDriver(Drivers.H2);
        persistenceConfig.setUrl("jdbc:h2:~/EarthsDb");
        persistenceConfig.setUser("sa");
        persistenceConfig.setPassword("");
        persistenceConfig.setDebug(true);

        SchemaConfig schemaConfig = new SchemaConfig();
        schemaConfig.setSchema("schema.sql");
        schemaConfig.setEnvironment(Environments.DEVELOPMENT);

        PropertiesConfig propertiesConfig = new PropertiesConfig();
        propertiesConfig.setPropertiesFile("earths.properties");

        stargzr.setPageRenderingScheme(RenderingScheme.RELOAD_EACH_REQUEST);
        stargzr.setSecurityAccess(AuthSecurityAccess.class);

        stargzr.addViewRenderer(AuthenticatedRenderer.class);
        stargzr.addViewRenderer(GuestRenderer.class);

        ViewConfig viewConfig = new ViewConfig();
        viewConfig.setResourcesPath("assets");

        stargzr.setViewConfig(viewConfig);
        stargzr.setSchemaConfig(schemaConfig);
        stargzr.setPropertiesConfig(propertiesConfig);
        stargzr.setPersistenceConfig(persistenceConfig);

        stargzr.start();
    }

}