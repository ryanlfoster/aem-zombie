<!-- Overwrite of the content.jsp of @@project/components/pagetypes/home/content.jsp -->
<%@include file="/apps/@@project/global.jsp" %>
<cq:includeClientLib categories="about.@@project.clientlib"/>

<div class="main" role="main">
	<!-- mainContent -->
		<div class="aboutnavigation">
			<cq:include path="@@projectsubnavigation" resourceType="@@project/components/modules/@@projectSubNavigation"/>
		</div>			
		<cq:include path="content" resourceType="foundation/components/parsys"/>
</div>