package com.nationalgeographic.sites.travel.lodges.componentry.components.commons.genericSlider;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;

import junit.framework.Assert;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.designer.Style;
import com.nationalgeographic.componentry.components.util.mcf.MultiCompositeFieldUtility;
import com.nationalgeographic.cq.pagetypes.util.NgsObject;
import com.nationalgeographic.cq.util.RequestContext;

/**
 * Test for GenericSlider. Insert two items, one with properties overwritten
 * (image path), and verify if the output model is correct.
 * 
 * @author Globant
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ MultiCompositeFieldUtility.class })
public class GenericSliderTest extends GenericSliderTestBase {

    RequestContext ctx;
    ValueMap model;
    GenericSlider genericSlider;

    @Before
    public void doBeforeEachTestCase() {
        // Mocking.
        genericSlider = new GenericSlider();
        model = new ValueMapDecorator(new HashMap<String, Object>());
        ctx = mock(RequestContext.class);
        PowerMockito.mockStatic(MultiCompositeFieldUtility.class);
    }

    @Test
    public void prepareModelManualMode() {
        try {
            /** Mocking **/
            Style currentStyle = mock(Style.class);
            ValueMap properties = mock(ValueMap.class);
            String imagePath = ".";
            String text1path = "jcr:title";
            String text2path = "abstract";
            String text3path = "Text3";
            String text4path = "Text4";
            Resource resource = mock(Resource.class);
            Node node = mock(Node.class);
            List<ValueMap> mockedAssets = new ArrayList<ValueMap>();
            // Add an asset and override the image path
            mockedAssets.add(createRandomValueMap(true, true, true));
            String assetImageOverridePath = mockedAssets.get(0).get(LEAD_IMAGE_URL_OV).toString();
            String assetPath1 = mockedAssets.get(0).get(ASSET_PATH).toString();
            // Add an asset without override the image path
            mockedAssets.add(createRandomValueMap(false, false, false));
            String assetPath2 = mockedAssets.get(1).get(ASSET_PATH).toString();

            ResourceResolver resourceResolver = mock(ResourceResolver.class);
            Externalizer externalizer = mock(Externalizer.class);
            // Mock resourceItem in order to simulate an object from repository
            Resource resourceItem1 = mock(Resource.class);
            Resource resourceItem2 = mock(Resource.class);
            // Mock a ngsObject for each asset
            NgsObject ngsObject1 = mock(NgsObject.class);
            NgsObject ngsObject2 = mock(NgsObject.class);
            ValueMap properitesItem2 = mock(ValueMap.class);

            /** Stubbing **/
            // Complete style properties for DesignerDlgParamsResolver
            when(ctx.getCurrentStyle()).thenReturn(currentStyle);
            when(currentStyle.get("imagepath")).thenReturn(imagePath);
            when(currentStyle.get("text1path")).thenReturn(text1path);
            when(currentStyle.get("text2path")).thenReturn(text2path);
            when(currentStyle.get("text3path")).thenReturn(text3path);
            when(currentStyle.get("text4path")).thenReturn(text4path);
            when(currentStyle.get("titlefield")).thenReturn("titleField");

            when(ctx.getProperties()).thenReturn(properties);
            when(ctx.getResource()).thenReturn(resource);
            when(resource.adaptTo(Node.class)).thenReturn(node);
            when(properties.get("heading")).thenReturn("Heading Title");
            when(properties.get("configuration", String.class)).thenReturn("manual");
            when(node.getIdentifier()).thenReturn("nodeIdentifier");
            // Return the list of assets for the slider
            when(MultiCompositeFieldUtility.getValues(ctx, "assetsPaths")).thenReturn(mockedAssets);
            when(ctx.getResourceResolver()).thenReturn(resourceResolver);
            when(resourceResolver.adaptTo(Externalizer.class)).thenReturn(externalizer);
            // Return a valid resource for each asset
            when(resourceResolver.getResource(assetPath1)).thenReturn(resourceItem1);
            when(resourceResolver.getResource(assetPath2)).thenReturn(resourceItem2);
            // AdaptTo resource to ngsObject
            when(resourceItem1.adaptTo(NgsObject.class)).thenReturn(ngsObject1);
            when(resourceItem2.adaptTo(NgsObject.class)).thenReturn(ngsObject2);
            // For the second item the contentResource is itself.
            when(ngsObject2.getContentResource()).thenReturn(resourceItem2);
            // Return the original path for the item that wasn't overwritten.
            when(resourceItem2.getPath()).thenReturn(assetPath2);
            when(externalizer.externalLink(null, "lodges", assetPath2)).thenReturn(assetPath2);
            when(resourceItem2.adaptTo(ValueMap.class)).thenReturn(properitesItem2);
            when(properitesItem2.get(text1path, (String) null)).thenReturn("OriginalTitle");
            when(properitesItem2.get(text2path, (String) null)).thenReturn("OriginalAbstract");

            /** Verify result **/
            genericSlider.prepareModel(ctx, model);
            Assert.assertEquals("nodeIdentifier", model.get("componentId"));
            // Verify model output.
            List<Map<String, Object>> assets = model.get(ASSETS_KEY, List.class);
            assertNotNull(assets);
            assertNotNull(assets.get(0));
            // Check if the item which has been overridden have the correct
            // asset's path
            Assert.assertEquals(assetImageOverridePath, assets.get(0).get(IMAGE_URL_KEY));
            // Check if the item without an override have the original
            // properties
            Assert.assertEquals(assetPath2, assets.get(1).get(IMAGE_URL_KEY));
            Assert.assertEquals("OriginalTitle", assets.get(1).get(TEXT_1_KEY));
            Assert.assertEquals("OriginalAbstract", assets.get(1).get(TEXT_2_KEY));
            // Check if the css selector is the expected one.
            Assert.assertEquals(model.get("cssselector"), "generic");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
