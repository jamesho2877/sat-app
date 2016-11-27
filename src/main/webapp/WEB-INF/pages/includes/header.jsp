<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Up and running with Spring Framework quickly</title>
<link href="<c:url value="/public/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<div class="container-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
									class="icon-bar"></span> <span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">HOT CURRY</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
								<li><a href="#">Link</a></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
									role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li role="separator" class="divider"></li>
										<li><a href="#">Separated link</a></li>
										<li role="separator" class="divider"></li>
										<li><a href="#">One more separated link</a></li>
									</ul></li>
							</ul>
							<form class="navbar-form navbar-left">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>
							<ul class="nav navbar-nav navbar-right">
								<sec:authorize access="isAnonymous()">
									<li><a href="<c:url value="signup" />"><spring:message code="homepage.signup" /></a></li>
									<li><a href="<c:url value="login" />">Sign in <span class="glyphicon glyphicon-log-in"></span></a></li>
								</sec:authorize>
								<sec:authorize access="isAuthenticated()">
									<li class="dropdown">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										    <span class="glyphicon glyphicon-user"></span>
										    <sec:authentication property="principal.user.name" /> <b class="caret"></b>
										</a>
										<ul class="dropdown-menu">
										   	<li><a href="/users/<sec:authentication property='principal.user.id' />"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
											<li>
												<c:url var="logoutUrl" value="/logout" />
												<form:form	id="logoutForm" action="${logoutUrl}" method="post"></form:form>
												<a href="javascript:document.getElementById('logoutForm').submit()"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
									        </li>
									    </ul>
									</li>
								</sec:authorize>
								<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
									role="button" aria-haspopup="true" aria-expanded="false"><spring:message
											code="lang.lang" /><span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="?language=en"><spring:message code="lang.en" /></a></li>
										<li role="separator" class="divider"></li>
										<li><a href="?language=fi"><spring:message code="lang.fi" /></a></li>
										<li><a href="?language=vi"><spring:message code="lang.vi" /></a></li>
									</ul></li>
							</ul>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
				
				<sec:authorize access="hasRole('ROLE_UNVERIFIED')">
					<div class="alert alert-warning alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						Your email id is unverified. <a href="/users/resend-verification-mail">Click here</a> to get
						the verification mail again.
					</div>
				</sec:authorize>

				<c:if test="${ not empty flashMessage }">
					<div class="alert alert-${flashType} alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						${flashMessage}
					</div>
				</c:if>
			</div>
		</div>
		<div id="body">
			<div class="container">