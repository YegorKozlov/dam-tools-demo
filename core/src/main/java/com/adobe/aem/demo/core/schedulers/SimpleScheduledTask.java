package com.adobe.aem.demo.core.schedulers;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;

/**
 * A simple demo for cron-job like tasks that get executed regularly.
 */
@Designate(ocd=SimpleScheduledTask.Config.class)
@Component(service=Runnable.class)
public class SimpleScheduledTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ObjectClassDefinition(name="A scheduled task",
                           description = "Simple demo for cron-job like task with properties")
    public static @interface Config {

        @AttributeDefinition(name = "Cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

         @AttributeDefinition(name = "Destination folder",
                             description = "Can be configured in /system/console/configMgr")
        String destinationFolder() default "/content/dam/projects";
    }

    private String destinationFolder;

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public void run() {


        for(AssetInfo assetInfo : getAssets()) {
            try (ResourceResolver resolver = resolverFactory.getAdministrativeResourceResolver(null)){
                AssetManager assetManager = resolver.adaptTo(AssetManager.class);
                String path = destinationFolder + "/" + assetInfo.fileName;
                Asset asset = assetManager.createAsset(path, assetInfo.stream, assetInfo.mimeType, true);

                // add properties, renditions, etc...

                logger.info("asset created: {}", asset.getPath());

            } catch (LoginException e){
                logger.error("failed to create resolver", e);
            }
        }
    }

    @Activate
    protected void activate(final Config config) {
        destinationFolder = config.destinationFolder();
    }


    List<AssetInfo> getAssets(){
        // TODO
        return Collections.emptyList();
    }

    /**
     * information about an asset to upload.
     * It can be a file in the file system or a remote asset fetched by http, etc.
     */
    static class AssetInfo {
        String fileName;
        String mimeType;
        InputStream stream;
    }
}
