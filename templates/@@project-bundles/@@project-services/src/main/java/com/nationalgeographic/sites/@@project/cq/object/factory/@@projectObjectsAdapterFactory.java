package com.nationalgeographic.sites.travel.@@project.cq.object.factory;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.nationalgeographic.sites.travel.@@project.cq.util.@@projectPageTypeResourceTypes;

@Component
@Service(value = org.apache.sling.api.adapter.AdapterFactory.class)
@Properties({
    @Property(name="adaptables", 
        value={"org.apache.sling.api.resource.Resource"}),
        @Property(name="adapters", 
        value= {"com.nationalgeographic.cq.pagetypes.util.NgsObject"})
})
public class @@projectObjectsAdapterFactory implements AdapterFactory {
    
    private static final Logger log = LoggerFactory
            .getLogger(@@projectObjectsAdapterFactory.class);
    
    @Override
    @SuppressWarnings("unchecked")
    public <AdapterType> AdapterType getAdapter(Object adaptable, 
        Class<AdapterType> type) {
        
        Resource resource = null;
        
        try {

            if(adaptable instanceof Resource) {
                resource = (Resource) adaptable;
            } else {
                return null;
            }
            
            log.info(resource.getPath());
            
            if(resource.getPath().endsWith("/jcr:content")) {
                resource = resource.getParent();
            }
            
            Page page = resource.adaptTo(Page.class);
            
            if(page!=null && page.hasContent()) {
                String resourceType = page.getProperties()
                        .get("sling:resourceType",String.class);
                
                if(resourceType.equals("")) {
                    log.info("page has no resource type");
                    return null;
                }
                
                Class myClass = @@projectPageTypeResourceTypes
                        .pageTypeFromResourceType(resourceType);
                
                if(myClass==null) {
                    return null;
                }
                
                return (AdapterType) myClass.asSubclass(myClass)
                        .getConstructor(Resource.class, Page.class)
                        .newInstance(resource, page);
            }
        } catch (Exception e) {
            log.error(String.format("Error adapting resource of {} to type {}", 
                    resource.getResourceType(), type.getName()),e);
        }
        
        log.debug("Unable to adapt resource of {} to type {}", 
                resource.getResourceType(), type.getName());
        
        return null;
    }

}