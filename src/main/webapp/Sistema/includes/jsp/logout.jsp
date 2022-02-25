<%
	try {
		session.removeAttribute("user");
		session.removeAttribute("userName");	
	} catch (Exception e){
		System.out.println(e);		
	} finally {
%>
	<jsp:forward page="/Sistema/view/login.jsp" />
<%
	}

%>