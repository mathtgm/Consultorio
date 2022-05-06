<div class="container-fluid">
			<div class="row flex-nowrap">
				<div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
					<div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
            			<a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
	                		<span class="fs-5 d-none d-sm-inline">Menu</span>
		                </a>
						<ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
							<li class="nav-item">
								<a href="/SistemaConsultorio/Sistema/view/index.jsp" class="nav-link align-middle px-0">
									<i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline text-white">Home</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/SistemaConsultorio/Sistema/view/paciente/listaPaciente.jsp" class="nav-link align-middle px-0">
									<i class="fs-4 bi-person-lines-fill"></i><span class="ms-1 d-none d-sm-inline text-white">Paciente</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/SistemaConsultorio/Sistema/view/convenio/listaConvenio.jsp" class="nav-link align-middle px-0">
									<i class="fs-4 bi bi-building"></i><span class="ms-1 d-none d-sm-inline text-white">Conv�nio</span>
                        		</a>
	                    	</li>
							<li class="nav-item">
								<a href="/SistemaConsultorio/Sistema/view/funcionario/listaFuncionario.jsp" class="nav-link align-middle px-0">
									<i class="fs-4 bi-person-badge"></i><span class="ms-1 d-none d-sm-inline text-white">Funcin�rio</span>
								</a>
							</li>
							
							<li class="nav-item">
								<a class="nav-link align-middle px-0" href="/SistemaConsultorio/Sistema/view/consulta/listaConsulta.jsp">
									<i class="fs-4 bi bi-card-list"></i><span class="ms-1 d-none d-sm-inline text-white">Lista de consultas</span>
								</a>
							</li>
						</ul>
						<hr>
						<div class="dropdown pb-4">
							<a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
								<img src="/SistemaConsultorio/Sistema/view/images/profile-default.png" width="30" height="30" class="rounded-circle">
								<span class="d-none d-sm-inline mx-1"><%= session.getAttribute("nome") %></span>
							</a>
							<ul class="dropdown-menu dropdown-menu-dark text-small shadow">
								<li><a class="dropdown-item" href="#">Perfil</a></li>
								<li><a class="dropdown-item" href="/SistemaConsultorio/Sistema/includes/jsp/logout.jsp">Sair</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col py-3" id="content">