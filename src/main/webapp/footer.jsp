<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" />

<jsp:useBean id="date" class="java.util.Date" />
<format:formatDate var="now" value="${date}" pattern="y" />

		</div>
		<div id="footer" class="noprint"><a href="${contextRoot}">Home</a> | &copy;${now} Aashayein</div>
	</div>
</body>
</html>