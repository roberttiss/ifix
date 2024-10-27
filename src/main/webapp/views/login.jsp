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
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <title>Login</title>
  </head>
  <body>
  	<div class="container">
		<div class="col-lg-4 offset-lg-4 col-sm-12">
			<c:choose>
				<c:when test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Usuário cadastrado com sucesso. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${result == 'loginError'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						Cliente inexistente.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>
			
            <section class="py-3 py-md-5 min-vh-100 d-flex align-items-center">
              <div class="container">
                <div class="row justify-content-center">
                  <div class="col-10 col-md-8 col-lg-12">
                    <div class="card shadow-sm border-light rounded-3 bg-white p-3 p-md-4">
                      <div class="card-body p-3 p-md-4 p-xl-6">
                        <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Faça login na sua conta</h2>

                        <form action="../login" method="post" id="login-form">
                          <div class="row gy-2">

                            <div class="col-12">
                              <div class="form-floating mb-3">
                                <input type="text" class="form-control" name="cpf" id="cpf" maxlength="14" required>
                                <label for="cpf" class="form-label">CPF</label>
                              </div>
                            </div>

                            <div class="col-12">
                              <div class="d-flex gap-2 justify-content-between">
                                <div class="form-check">
                                  <input class="form-check-input" type="checkbox" value="" name="lembrarMe" id="lembrarMe">
                                  <label class="form-check-label text-secondary" for="lembrarMe">
                                    Manter-me conectado
                                  </label>
                                </div>
                              </div>
                            </div>

                            <div class="col-12">
                              <div class="d-grid my-3">
                                <button class="btn btn-primary btn-lg" type="submit">Entrar</button>
                              </div>
                            </div>

                            <div class="col-12">
                              <p class="m-0 text-secondary text-center">Não tem uma conta? <a href="${pageContext.request.contextPath}/views/customer/createCustomer.jsp" class="link-primary text-decoration-none">Inscreva-se</a></p>
                            </div>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
  		</div>
  	</div>
    <script src="../js/customer/login-validation.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
