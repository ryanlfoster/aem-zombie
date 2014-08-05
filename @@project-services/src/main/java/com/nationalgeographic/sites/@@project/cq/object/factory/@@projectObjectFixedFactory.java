package com.nationalgeographic.sites.travel.lodges.cq.object.factory;

import org.apache.sling.api.resource.Resource;

import com.nationalgeographic.cq.pagetypes.util.NgsObject;
import com.nationalgeographic.cq.pagetypes.util.NgsPage;
import com.nationalgeographic.sites.travel.lodges.cq.object.LodgesObject;
import com.nationalgeographic.sites.travel.lodges.cq.pagetypes.impl.LodgesDetailPageImpl;
import com.nationalgeographic.sites.travel.lodges.cq.util.LodgesPageTypeResourceTypes;

public class LodgesObjectFixedFactory {

    private LodgesObjectFixedFactory() {
    }

    @SuppressWarnings("rawtypes")
    public static LodgesObject buildLodgesObject(NgsObject ngsObject) {
        Resource contentResource = ngsObject.getContentResource().getParent();

        if (ngsObject instanceof NgsPage) {
            String template = ((NgsPage) ngsObject).getProperties().get(
                    "sling:resourceType", String.class);

            Class lodgesType = LodgesPageTypeResourceTypes
                    .pageTypeFromResourceType(LodgesPageTypeResourceTypes.LODGES
                            .getResourceType());

            if (LodgesDetailPageImpl.class.equals(lodgesType)) {
                return new LodgesDetailPageImpl(contentResource,
                        ((NgsPage) ngsObject));
            }
        }
        return null;
    }
}
