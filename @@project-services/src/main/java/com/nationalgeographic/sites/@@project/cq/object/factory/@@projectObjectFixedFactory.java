package com.nationalgeographic.sites.travel.@@project.cq.object.factory;

import org.apache.sling.api.resource.Resource;

import com.nationalgeographic.cq.pagetypes.util.NgsObject;
import com.nationalgeographic.cq.pagetypes.util.NgsPage;
import com.nationalgeographic.sites.travel.@@project.cq.object.@@projectObject;
import com.nationalgeographic.sites.travel.@@project.cq.pagetypes.impl.@@projectDetailPageImpl;
import com.nationalgeographic.sites.travel.@@project.cq.util.@@projectPageTypeResourceTypes;

public class @@projectObjectFixedFactory {

    private @@projectObjectFixedFactory() {
    }

    @SuppressWarnings("rawtypes")
    public static @@projectObject build@@projectObject(NgsObject ngsObject) {
        Resource contentResource = ngsObject.getContentResource().getParent();

        if (ngsObject instanceof NgsPage) {
            String template = ((NgsPage) ngsObject).getProperties().get(
                    "sling:resourceType", String.class);

            Class @@projectType = @@projectPageTypeResourceTypes
                    .pageTypeFromResourceType(@@projectPageTypeResourceTypes.@@project
                            .getResourceType());

            if (@@projectDetailPageImpl.class.equals(@@projectType)) {
                return new @@projectDetailPageImpl(contentResource,
                        ((NgsPage) ngsObject));
            }
        }
        return null;
    }
}
