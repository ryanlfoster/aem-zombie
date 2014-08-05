package com.nationalgeographic.sites.travel.@@project.componentry.components.commons.genericSlider.heroCarousel;

import org.apache.sling.api.resource.ValueMap;

import com.nationalgeographic.cq.util.RequestContext;
import com.nationalgeographic.sites.travel.@@project.componentry.components.commons.genericSlider.GenericSlider;
import com.nationalgeographic.sites.travel.@@project.componentry.components.commons.genericSlider.heroCarousel.paramsResolver.impl.HeroCarouselParamsResolver;
import com.nationalgeographic.sites.travel.@@project.componentry.components.commons.genericSlider.paramsResolver.ParamsResolver;
import com.day.cq.i18n.I18n;

public class HeroCarousel extends GenericSlider {

    private static final long serialVersionUID = 1L;

    @Override
    protected void prepareModel(RequestContext ctx, ValueMap model) {
        super.prepareModel(ctx, model);

        I18n i18n = new I18n(ctx.getRequest());
        model.put("pageSlideCssSelector", HeroCarouselParamsResolver.PAGE_SLIDE_CSS_SELECTOR);
        model.put("readMoreBtnText", i18n.get("Read More"));
    }

    @Override
    protected ParamsResolver getAssetResolver(RequestContext ctx) {
        return new HeroCarouselParamsResolver(ctx);
    }
}