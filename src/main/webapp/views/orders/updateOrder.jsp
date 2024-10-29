<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/errors.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    <title>Atualizar Ordem</title>
</head>
<body>
    <section class="py-3 py-md-5 min-vh-100 d-flex align-items-center justify-content-center">
        <div class="container">
            <div class="col-lg-4 offset-lg-4 col-sm-12">
                <c:choose>
                    <c:when test="${result == 'update'}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            Order updated successfully.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:when>
                    <c:when test="${result == 'error'}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            Error updating order.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:when>
                </c:choose>
            </div>
    
            <div class="row justify-content-center">
                <div class="col-12 col-md-12 col-lg-12">
                    <div class="card shadow-sm border-light rounded-3 bg-white p-3 p-md-4">
                        <div class="card-body p-2 p-md-2 p-xl-4 align-items-center">
                            <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Registrar ordem</h2>
            
                            <form action="${pageContext.request.contextPath}/update-order" method="post" id="updateOrder" novalidate>
                                <input type="hidden" name="id" value="${param.id}">
                                <div class="mb-4 row justify-content-center">
                                    <div class="col-md-8 col-sm-12">
                                        <label for="description">Description*</label>
                                        <input type="text" name="description" id="description" class="form-control" minlength="10" required value="${description}">
                                        <div class="invalid-feedback">A descrição deve ter no minímo 10 caracteres.</div>
                                    </div>
                                </div>
                                <div class="mb-4 row justify-content-center">
                                    <div class="col-md-8 col-sm-12">
                                        <label for="observation">Observation*</label>
                                        <input type="text" name="observation" id="observation" class="form-control" minlength="10" required value="${observation}">
                                        <div class="invalid-feedback">A observação deve ter no minímo 10 caracteres.</div>
                                    </div>
                                </div>
                                <div class="mb-4 row justify-content-center">
                                    <div class="col-md-6 col-sm-12">
                                        <button type="submit" class="btn btn-primary w-100">Update</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="${pageContext.request.contextPath}/js/orders/update-validation.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>