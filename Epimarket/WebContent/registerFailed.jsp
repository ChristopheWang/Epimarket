<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Enregistrement échoué</title>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <link href="includes/css/RegisterPage.css" rel="stylesheet" type="text/css"/>   
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
	    	<h3 id="RegisterFailed"><h:outputText value="#{message.registerFailed}"/></h3>
	    	<h:form>
	    	<h:outputText value="Votre identifiant est déjà utilisé, veuillez choisir un autre identifiant pour créer votre compte."/>
	    	<x:htmlTag value="br"/>
	    	<x:htmlTag value="br"/>
       		<h:commandButton value="Retourner à l'accueil" action="index"/>
       		<h:commandButton value="Retourner à la page d'enregistrement" action="registerAccount"/>
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