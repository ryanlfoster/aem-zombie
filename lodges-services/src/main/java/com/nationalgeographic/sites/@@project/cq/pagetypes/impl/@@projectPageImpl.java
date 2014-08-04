package com.nationalgeographic.sites.travel.lodges.cq.pagetypes.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;
import java.util.ArrayList;

import com.day.cq.wcm.api.Page;
import javax.jcr.Node;

import com.nationalgeographic.sites.travel.lodges.cq.pagetypes.BestOfPage;
import com.nationalgeographic.sites.travel.lodges.cq.pagetypes.impl.LodgesDetailPageImpl;
import com.nationalgeographic.sites.travel.lodges.cq.object.LodgesObject;
import com.nationalgeographic.cq.pagetypes.util.NgsObject;

public class BestOfPageImpl extends LodgesDetailPageImpl implements BestOfPage {

    public BestOfPageImpl(Resource resource, Page page) {
        super(resource, page);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<LodgesObject> getObjects() {

        List<LodgesObject> objects = new ArrayList<LodgesObject>();
        if(this.page.getProperties().containsKey("lodgesAndItineraries")) {
            String[] lodgesPath = this.getProperties().get("lodgesAndItineraries",String[].class);
            ResourceResolver resourceResolver = this.resource.getResourceResolver(); 

            for (String path: lodgesPath)
            {
                Resource lodgeResource = resourceResolver.getResource(path);
                Page lodgePage = lodgeResource.adaptTo(Page.class);
                LodgesObject obj = (LodgesObject)lodgePage.adaptTo(NgsObject.class);
                objects.add(obj);
            }
            return objects;
        } else {
            return null;
        }
    }
}
