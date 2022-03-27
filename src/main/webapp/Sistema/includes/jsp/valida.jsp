<% 
	try {
		if(session.getAttribute("nome") == null) {
%>
			<jsp:forward page="/Sistema/view/login.jsp" />
<%
		}
	} catch (Exception e) {
		System.out.println("Erro na sessâo: " + e);
	}

%>