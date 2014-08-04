package com.nationalgeographic.sites.travel.lodges.cq.object;

import java.util.List;

import com.nationalgeographic.cq.pagetypes.util.NgsObject;
import org.apache.sling.commons.json.JSONObject;

public interface LodgesObject extends NgsObject{
    public String getExperiences();
    public String getNearBy();
    public String getLatitude();
    public String getLongitude();
    public String getRegion();
    public List<String> getActivities();
    public boolean getNew();
    public String getFeaturedText();
	public String getCustomizeText();
	public String getCustomizeImg();
	public String getItinerariesText();
	public String getItinerariesImg();
	public String getExpeditionsText();
	public String getExpeditionsImg();
	public String getExpeditionsLink();
	public String getItinerariesLink();
	public String getCustomizeLink();
}
