<%!
	HttpServletRequest httpservletrequest;
	String request(String parametro) {
		String valor = httpservletrequest.getParameter(parametro);
		if(valor == null) {
			return null;
		}
		return valor;
	}
%>
<%
	httpservletrequest = request;
%>