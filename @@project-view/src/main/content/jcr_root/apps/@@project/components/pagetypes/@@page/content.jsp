<!-- Overwrite of the content.jsp of lodges/components/pagetypes/home/content.jsp -->
<%@include file="/apps/lodges/global.jsp" %>
<cq:includeClientLib categories="about.lodges.clientlib"/>

<div class="main" role="main">
	<!-- mainContent -->
		<div class="aboutnavigation">
			<cq:include path="lodgessubnavigation" resourceType="lodges/components/modules/LodgesSubNavigation"/>
		</div>			
		<cq:include path="content" resourceType="foundation/components/parsys"/>
</div>