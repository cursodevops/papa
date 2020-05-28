<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>
<html>
<head>
<title>Home</title>
</head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<body>
	<div class="container">



		<h1>Comisiones de ventassss con modulosss</h1>

		<div>
			<sp:form action="calcular" method="POST" modelAttribute="comisiones">
				<div class="form-group">
					<label for="email">Tipo:</label>
					<sp:select id="tipo" path="tipoSeleccionado">
						<sp:option value="0">Selecciona tipo de vendedor:</sp:option>
						<sp:options items="${comisiones.tiposVendedor}"></sp:options>
					</sp:select>
				</div>
				<div class="form-group ">
					<div class="row">
						<div class="col-lg-6 ">
							<label for="pwd">Importe ventas:</label>
							<sp:input type="text" class="form-control"
								placeholder="Introduce importe ventas mes" id="importe"
								path="ventasMes" />
						</div>
					</div>
				</div>

				<div class="form-group ">
					<div class="row">
						<div class="col-lg-6 ">
							<label for="pwd">Horas extras:</label>
							<sp:input type="text" class="form-control"
								placeholder="Introduce horas extras realizadas" id="horas"
								path="horasExtras" />
						</div>
					</div>
				</div>



				<button type="submit" class="btn btn-primary">Calcular</button>
			</sp:form>
			<h4>Tipo:${criterios.tipoSeleccionado }</h4>
			<h4>Ventas mes:${criterios.ventasMes }</h4>
			<h4>Horas extras:${criterios.horasExtras }</h4>
			<h1 class="text-success">Salario :${salario}</h1>
		</div>

	</div>
</body>
</html>
