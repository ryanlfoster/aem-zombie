package com.nationalgeographic.sites.travel.@@project.cq.components.query;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.json.JSONException;

import com.day.cq.commons.TidyJSONWriter;
import com.nationalgeographic.cq.query.OptionsProviderServlet;
import com.nationalgeographic.sites.travel.@@project.cq.util.@@projectPageTypeResourceTypes;

@Component(name = "@@project Query Component Options Provider", immediate = true, metatype = false, enabled = true, description = "Provides a json response for the selection widget populated with valid page types to query")
@Service(value = javax.servlet.Servlet.class)
@Properties({
        @Property(name = "sling.servlet.paths", value = { "/bin/component/query/national-geographic-@@project/optionsProvider" }),
        @Property(name = "sling.servlet.methods", value = "GET"),
        @Property(name="service.ranking", intValue = 1000)
})
public class @@projectOptionsProviderServlet extends OptionsProviderServlet {

    private static final long serialVersionUID = 6651426167142990685L;

     protected void createJson(TidyJSONWriter writer) throws JSONException {

        @@projectPageTypeResourceTypes[] items = @@projectPageTypeResourceTypes.values();

        TidyJSONWriter jsonArray = writer.array();

        for(@@projectPageTypeResourceTypes item : items) {
            if(item.isQueryable()) {
                TidyJSONWriter jsonObj = jsonArray.object();
                jsonObj.key("value");
                jsonObj.value(item.getResourceType());
                jsonObj.key("text");
                jsonObj.value("page - " + item.getFriendlyName());
                jsonObj.key("qtip");
                jsonObj.value("");
                jsonArray.endObject();
            }
        }
        writer.endArray();
    }
}