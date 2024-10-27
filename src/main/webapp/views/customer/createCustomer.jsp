<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="pt-BR">
	<head>	
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${pageContext.request.contextPath}/css/errors.css" rel="stylesheet">
		<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
		<title>Cadastrar-se</title>
	</head>
<body>
	<section class="py-3 py-md-5 min-vh-100 d-flex align-items-center justify-content-center">
		<div class="container">
			<div class="col-lg-4 offset-lg-4 col-sm-12">
				<c:choose>
					<c:when test="${result == 'registered'}">
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							Cliente cadastrado com sucesso.
							<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>
					</c:when>
					<c:when test="${result == 'loginError'}">
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							Erro ao cadastrar cliente.
							<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>
					</c:when>
				</c:choose>
			</div>

			<div class="row justify-content-center">
				<div class="col-12 col-md-12 col-lg-12">
					<div class="card shadow-sm border-light rounded-3 bg-white p-3 p-md-4">
						<div class="card-body p-2 p-md-2 p-xl-4 align-items-center">
							<h2 class="fs-6 fw-normal text-center text-secondary mb-4">Registrar Cliente</h2>

			            <form action="${pageContext.request.contextPath}/create-customer" method="post" id="customer-form" novalidate>
							<div id="registerSection">
								
								<div class="mb-3 row justify-content-center">
									<div class="col-md-6 col-sm-12">
										<label for="name">Nome*</label>
										<input type="text" name="name" id="name" class="form-control" minlength="3" required>
										<div class="invalid-feedback">O nome deve ter no mínimo 3 caracteres.</div>
									</div>
								</div>

								<div class="mb-3 row justify-content-center">
									<div class="col-md-6 col-sm-12">
										<label for="email">Email*</label>
										<input type="email" name="email" id="email" class="form-control" required>
										<div class="invalid-feedback">Por favor, insira um email válido.</div>
									</div>
								</div>

								<div class="mb-3 row justify-content-center">
									<div class="col-md-6 col-sm-12">
										<label for="cpf">CPF*</label>
										<input type="text" name="cpf" id="cpf" class="form-control" maxlength="14" required>
										<div class="invalid-feedback">Por favor, insira um CPF válido.</div>
									</div>
								</div>

								<div class="mb-3 row justify-content-center">
									<div class="col-md-6 col-sm-12">
										<label for="phone">Telefone*</label>
										<input type="text" name="phone" id="phone" class="form-control" maxlength="15" required>
										<div class="invalid-feedback">Por favor, insira um telefone válido.</div>
									</div>
								</div>


								<div class="mb-3 row justify-content-center">
									<div class="col-md-6 col-sm-12">
										<button type="submit" class="btn btn-primary w-100" id="continue-button">Continuar</button>
									</div>
								</div>

							</div>

                            <div id="streetSection" style="display: none;">

								<div class="mb-2 row justify-content-center">
									<div class="col-md-8 col-sm-12">
										<label for="street">Endereço*</label>
										<input type="text" name="street" id="street" class="form-control" readonly required>
									</div>
								</div>
								<div class="mb-2 row justify-content-center">
									<div class="col-md-8 col-sm-12">
										<label for="number">Número*</label>
										<input type="number" name="number" id="number" class="form-control" required>
									</div>
								</div>
								
								<div class="mb-1 row justify-content-center">
									<div class="col-md-4 col-sm-12">
										<label for="complement">Complemento*</label>
										<input type="text" name="complement" id="complement" class="form-control" maxlength="14" required>
									</div>
									<div class="col-md-4 col-sm-12">
										<label for="zipCode">CEP*</label>
										<input type="text" name="zipCode" id="zipCode" class="form-control" maxlength="9" required>
									</div>
								</div>
								
								<div class="mb-1 row justify-content-center">
									<div class="col-md-4 col-sm-12">
										<label for="city">Cidade*</label>
										<input type="text" name="city" id="city" class="form-control" readonly required>
									</div>
									<div class="col-md-4 col-sm-12">
										<label for="neighborhood">Bairro*</label>
										<input type="text" name="neighborhood" id="neighborhood" class="form-control" readonly required>
									</div>
									<div class="col-md-4 col-sm-12">
										<label for="state">Estado*</label>
										<input type="text" name="state" id="state" class="form-control" readonly required>
									</div>
								</div>

								<div class="mb-3 row justify-content-center">
									<div class="col-md-6 col-sm-12">
										<button type="submit" class="btn btn-primary w-100" id="register-button">Registrar</button>
									</div>
								</div>

                            </div>
						</form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
	<script src="${pageContext.request.contextPath}/js/customer/address-validation.js"></script>
	<script src="${pageContext.request.contextPath}/js/customer/register-validation.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>







