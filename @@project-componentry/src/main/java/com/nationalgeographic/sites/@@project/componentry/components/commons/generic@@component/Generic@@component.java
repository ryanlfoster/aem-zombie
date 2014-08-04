package com.nationalgeographic.sites.travel.lodges.componentry.components.commons.genericSlider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nationalgeographic.componentry.components.util.mcf.MultiCompositeFieldUtility;
import com.nationalgeographic.componentry.model.AbstractComponentModel;
import com.nationalgeographic.cq.pagetypes.util.NgsObject;
import com.nationalgeographic.cq.query.NgsPickerQueryComponent;
import com.nationalgeographic.cq.util.RequestContext;
import com.nationalgeographic.sites.travel.lodges.componentry.components.commons.genericSlider.paramsResolver.ParamsResolver;
import com.nationalgeographic.sites.travel.lodges.componentry.components.commons.genericSlider.paramsResolver.impl.DesignerDlgParamsResolver;

public class GenericSlider extends AbstractComponentModel {

    // FIELDS IN MANUAL SELECT MULTIFIELD WIDGET
    protected static final String ASSET_PATH = "assetPath";
    protected static final String LEAD_IMAGE_URL = "leadImageUrlOverride";
    protected static final String TEXT_1 = "text1Override";
    protected static final String TEXT_2 = "text2Override";
    protected static final String TEXT_3 = "text3Override";
    protected static final String TEXT_4 = "text4Override";

    // SET OF KEYS FOR MODEL
    protected static final String IMAGE_URL_KEY = "imageUrl";
    protected static final String ALT_TEXT_KEY = "altText";
    protected static final String TEXT_1_KEY = "text1";
    protected static final String TEXT_2_KEY = "text2";
    protected static final String TEXT_3_KEY = "text3";
    protected static final String TEXT_4_KEY = "text4";
    protected static final String ASSET_URL = "assetUrl";
    protected static final String ASSETS_KEY = "assets";

    protected static final String SLIDER_CSS_SELECTOR_KEY = "slideSelector";

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(GenericSlider.class);

    @Override
    protected void prepareModel(RequestContext ctx, ValueMap model) {

        ParamsResolver paramsResolver = getAssetResolver(ctx);
        ValueMap properties = ctx.getProperties();

        Node componentNode = ctx.getResource().adaptTo(Node.class);
        try {
            // The component unique identifier
            model.put("componentId", componentNode.getIdentifier());
        } catch (RepositoryException e) {
            model.put("componentId", "none");
            e.printStackTrace();
        }

        // PROPERTIES SET IN THE COMPONENT DIALOG
        model.put("heading", properties.get("heading"));

        // The text line selected as title
        model.put("titlefield", paramsResolver.getTitleField());

        // Css selector to provide styles to this Gallery
        model.put("cssselector", paramsResolver.getCssSelector());

        String conf = properties.get("configuration", String.class);

        // IF THE GALLERY IS NOT POPULATED DYNAMICALLY
        // CHECK EACH IMAGE FOR OVERRIDDEN FIELDS AND ADD THEM TO THE MODEL
        if (conf != null) {
            List<Map<String, Object>> assets = null;
            if (conf.equals("manual")) {
                assets = getManualInputAssets(ctx, paramsResolver, assets);
            } else if (conf.equals("dynamic")) {
                assets = getDynamicInputAssets(ctx, model, paramsResolver);
            } else if (conf.equals("combo")) {
                assets = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> manualAssets = getManualInputAssets(ctx, paramsResolver, assets);
                assets.addAll(manualAssets);
                List<Map<String, Object>> dynamicAssets = getDynamicInputAssets(ctx, model, paramsResolver);
                assets.addAll(dynamicAssets);
            }

            model.put(ASSETS_KEY, assets);
        }
    }

    private List<Map<String, Object>> getDynamicInputAssets(RequestContext ctx, ValueMap model,
            ParamsResolver paramsResolver) {
        List<Map<String, Object>> assets;
        List<NgsObject> stories = NgsPickerQueryComponent.getResults(ctx.getResourceResolver(), model);
        assets = new ArrayList<Map<String, Object>>(stories.size());
        for (NgsObject story : stories) {
            Map<String, Object> resolvedAsset = resolveAsset(story, null, paramsResolver);
            assets.add(resolvedAsset);
        }
        return assets;
    }

    private List<Map<String, Object>> getManualInputAssets(RequestContext ctx, ParamsResolver paramsResolver,
            List<Map<String, Object>> assets) {
        List<ValueMap> assetsPaths = MultiCompositeFieldUtility.getValues(ctx, "assetsPaths");
        if (assetsPaths != null && assetsPaths.size() > 0) {

            assets = new ArrayList<Map<String, Object>>(assetsPaths.size());
            for (ValueMap asset : assetsPaths) {
                if (asset != null) {

                    Resource resource = paramsResolver.getResource((String) asset.get(ASSET_PATH));
                    if (resource != null) {
                        NgsObject ngsObject = resource.adaptTo(NgsObject.class);
                        Map<String, Object> resolvedAsset = resolveAsset(ngsObject, asset, paramsResolver);
                        assets.add(resolvedAsset);
                    }
                }
            }

        }
        return assets;
    }

    protected Map<String, Object> resolveAsset(NgsObject ngsObject, ValueMap asset, ParamsResolver paramsResolver) {
        Map<String, Object> ret = new HashMap<String, Object>();

        ret.put(ASSET_URL, ngsObject.getAbsoluteUrl());

        if (asset != null && asset.get(LEAD_IMAGE_URL) != null) {
            ret.put(IMAGE_URL_KEY, asset.get(LEAD_IMAGE_URL));
            ret.put(ALT_TEXT_KEY, paramsResolver.getImageAltText(ngsObject));
        } else {
            ret.put(IMAGE_URL_KEY, paramsResolver.getImagePath(ngsObject));
            ret.put(ALT_TEXT_KEY, paramsResolver.getImageAltText(ngsObject));
        }

        if (asset != null && asset.get(TEXT_1) != null) {
            ret.put(TEXT_1_KEY, asset.get(TEXT_1));
        } else {
            ret.put(TEXT_1_KEY, paramsResolver.getText1(ngsObject));
        }

        if (asset != null && asset.get(TEXT_2) != null) {
            ret.put(TEXT_2_KEY, asset.get(TEXT_2));
        } else {
            ret.put(TEXT_2_KEY, paramsResolver.getText2(ngsObject));
        }

        if (asset != null && asset.get(TEXT_3) != null) {
            ret.put(TEXT_3_KEY, asset.get(TEXT_3));
        } else {
            ret.put(TEXT_3_KEY, paramsResolver.getText3(ngsObject));
        }

        if (asset != null && asset.get(TEXT_4) != null) {
            ret.put(TEXT_4_KEY, asset.get(TEXT_4));
        } else {
            ret.put(TEXT_4_KEY, paramsResolver.getText4(ngsObject));
        }

        if (asset != null && asset.get(TEXT_4) != null) {
            ret.put(TEXT_4_KEY, asset.get(TEXT_4));
        } else {
            ret.put(TEXT_4_KEY, paramsResolver.getText4(ngsObject));
        }

        ret.put(SLIDER_CSS_SELECTOR_KEY, paramsResolver.getSliderCssSelector(ngsObject));

        return ret;
    }

    protected ParamsResolver getAssetResolver(RequestContext ctx) {
        return new DesignerDlgParamsResolver(ctx);
    }
}