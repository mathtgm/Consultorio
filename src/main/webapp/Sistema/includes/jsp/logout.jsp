<%
	try {
		session.removeAttribute("nome");
		session.removeAttribute("nivel_acesso");	
		session.removeAttribute("id_usuario");
	} catch (Exception e){
		System.out.println(e);		
	} finally {
%>
	<jsp:forward page="/Sistema/view/login.jsp" />
<%
	}

%>