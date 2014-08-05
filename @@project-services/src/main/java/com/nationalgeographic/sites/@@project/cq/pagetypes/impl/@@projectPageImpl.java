package com.nationalgeographic.sites.travel.@@project.cq.pagetypes.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;
import java.util.ArrayList;

import com.day.cq.wcm.api.Page;
import javax.jcr.Node;

import com.nationalgeographic.sites.travel.@@project.cq.pagetypes.BestOfPage;
import com.nationalgeographic.sites.travel.@@project.cq.pagetypes.impl.@@projectDetailPageImpl;
import com.nationalgeographic.sites.travel.@@project.cq.object.@@projectObject;
import com.nationalgeographic.cq.pagetypes.util.NgsObject;

public class BestOfPageImpl extends @@projectDetailPageImpl implements BestOfPage {

    public BestOfPageImpl(Resource resource, Page page) {
        super(resource, page);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<@@projectObject> getObjects() {

        List<@@projectObject> objects = new ArrayList<@@projectObject>();
        if(this.page.getProperties().containsKey("@@projectAndItineraries")) {
            String[] @@projectPath = this.getProperties().get("@@projectAndItineraries",String[].class);
            ResourceResolver resourceResolver = this.resource.getResourceResolver(); 

            for (String path: @@projectPath)
            {
                Resource lodgeResource = resourceResolver.getResource(path);
                Page lodgePage = lodgeResource.adaptTo(Page.class);
                @@projectObject obj = (@@projectObject)lodgePage.adaptTo(NgsObject.class);
                objects.add(obj);
            }
            return objects;
        } else {
            return null;
        }
    }
}
