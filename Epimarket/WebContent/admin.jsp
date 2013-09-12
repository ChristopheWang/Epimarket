<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Admin Epimarket</title>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <link href="includes/css/AdminPage.css" rel="stylesheet" type="text/css" />   
	    </head>   
	    <body>
	    	<h:panelGrid id="HeaderGrid" columns="2" columnClasses="Logo, Header">
	    	<h:graphicImage value="includes/css/images/java.png" />
			<h:outputText value="Epimarket"/>
			</h:panelGrid>
			<x:htmlTag value="br"/>
			<h:graphicImage value="includes/css/images/separateur.png" />
	    	<x:htmlTag value="br"/>
	    	<x:htmlTag value="br"/>
	    	<h:form id="LoginForm">
	    	<h:outputText value="Zone d'authentification reservée à l'administrateur"/>
	    	<x:htmlTag value="br"/>
	    	<x:htmlTag value="br"/>
			<h:panelGrid columns="3">
				<h:outputText value="Identifiant:"/>
				<x:inputText value="#{clientBean.clientEditBean.currentClient.login}" required="true"  id="Identifiant"/><h:message for="Identifiant" errorStyle="color:red;"/>
				<h:outputText value="Mot de passe:"/>
				<x:inputSecret value="#{clientBean.clientEditBean.currentClient.password}" required="true" redisplay="false" id="MotdePasse"/><h:message for="MotdePasse" errorStyle="color:red"/>
			</h:panelGrid>
				<x:htmlTag value="br"/>
  				<h:commandButton value="Connexion" action="#{clientBean.logAdmin}"></h:commandButton>
			</h:form>
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:graphicImage value="includes/css/images/separateur.png" />
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:outputText id="Footer" value="#{message.copyright}"/>
	    </body>
	</html>
</f:view>