package com.nationalgeographic.sites.travel.@@project.componentry.components.commons.genericSlider;

import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;

/**
 * Util class for Sliders' tests.
 * 
 * @author Pereyra
 *
 */
public class GenericSliderTestBase {

    protected static final String ASSET_PATH = "assetPath";
    protected static final String SLIDER_CSS_SELECTOR_KEY = "slideSelector";
    protected static final String LEAD_IMAGE_URL_OV = "leadImageUrlOverride";
    protected static final String TEXT_1_OV = "text1Override";
    protected static final String TEXT_2_OV = "text2Override";
    protected static final String TEXT_3_OV = "text3Override";
    protected static final String TEXT_4_OV = "text4Override";

    protected static final String ALT_TEXT_KEY = "altText";
    protected static final String IMAGE_URL_KEY = "imageUrl";
    protected static final String ASSETS_KEY = "assets";
    protected static final String TEXT_1_KEY = "text1";
    protected static final String TEXT_2_KEY = "text2";
    protected static final String TEXT_3_KEY = "text3";
    protected static final String TEXT_4_KEY = "text4";

    /**
     * Create a new Item to insert into the simulated Slider.
     * 
     * @param overrideImage
     * @param overrideTitle
     * @param overrideAbstract
     * @return
     */
    protected ValueMap createRandomValueMap(Boolean overrideImage, Boolean overrideTitle, Boolean overrideAbstract) {
        Random random = new Random();
        int randomNumber = random.nextInt() % 100;
        Map<String, Object> map = new LinkedMap();
        if (overrideTitle) {
            map.put(TEXT_1_OV, "Text 1 Override - " + randomNumber);
        }
        if (overrideAbstract) {
            map.put(TEXT_2_OV, "Text 2 Override - " + randomNumber);
        }
        if (overrideImage) {
            map.put(LEAD_IMAGE_URL_OV, "/path/to/asset-" + randomNumber);
        }
        map.put(TEXT_3_OV, "Text 3 Override - " + randomNumber);
        map.put(TEXT_4_OV, "Text 4 Override - " + randomNumber);
        map.put(ASSET_PATH, "/path/to/resource-" + randomNumber);

        return new ValueMapDecorator(map);
    }

}
