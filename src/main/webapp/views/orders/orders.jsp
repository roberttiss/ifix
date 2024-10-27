<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ordens de serviço</title>
    <style>
        body {
            padding-top: 100px; /* Adjust this value based on the height of your navbar */
        }
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<section class="py-3 py-md-5 min-vh-100 d-flex align-items-center">
    <div class="container">
		<div class="col-lg-4 offset-lg-4 col-sm-12">
			<c:choose>
				<c:when test="${result == 'paid'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Pagamento realizado com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${result == 'error'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						Erro ao processar pagamento.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>
        </div>
    </div>
</section>

<div class="relative overflow-x-auto shadow-md sm:rounded-lg" style="margin: 0 auto; max-width: 90%; padding: 0 20px;">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">ID</th>
                <th scope="col" class="px-6 py-3">Descrição</th>
                <th scope="col" class="px-6 py-3">Data de abertura</th>
                <th scope="col" class="px-6 py-3">Observação</th>
                <th scope="col" class="px-6 py-3">Status</th>
                <th scope="col" class="px-6 py-3">Pagar</th>
                <th scope="col" class="px-6 py-3">Editar</th>
                <th scope="col" class="px-6 py-3">Excluir</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
				<c:when test="${fn:length(orders) > 0}">
                <c:forEach var="order" items="${orders}">
                        <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                            <td class="px-6 py-4">${order.id}</td>
                            <td class="px-6 py-4">${order.description}</td>
                            <td class="px-6 py-4">${order.issueDate}</td>
                            <td class="px-6 py-4">${order.observation}</td>
                            <td class="px-6 py-4">${order.status}</td>
                            <td class="px-6 py-4">
                                <a href="pay-order?id=${order.id}" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Pay</a>
                            </td>
                            <td class="px-6 py-4">
                                <form action="${pageContext.request.contextPath}/update-order" method="get">
                                    <input type="hidden" name="id" value="${order.id}">
                                    <button type="submit" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Edit</button>
                                </form>
                            </td>
                            <td class="px-6 py-4">
                                <a href="delete-order?id=${order.id}" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6" class="px-6 py-4">No orders found</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>