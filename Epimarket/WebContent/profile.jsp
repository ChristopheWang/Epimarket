<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Modifications r�ussies</title>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <link href="includes/css/MainPage.css" rel="stylesheet" type="text/css"/>   
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
	    	<h3 id="OrderDone"><h:outputText value="#{message.modificationDone}"/></h3>    
	        <h:form id="InfoForm">  
	        <h:outputText value="Vos nouvelles informations personnelles :"/>
	        <x:htmlTag value="br"/>
	        <x:htmlTag value="br"/>
	       	<h:panelGrid columns="2">
	       		<h:outputText value="Votre identifiant : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.login}"/>
	        	<h:outputText value="Votre mot de passe : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.password}"/>
	      		<h:outputText value="Votre nom : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.lastName}"/>
	      		<h:outputText value="Votre prenom : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.firstName}"/>
	      		<h:outputText value="Votre sexe : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.sexe}"/>
	      		<h:outputText value="Votre email : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.email}"/>
	       	</h:panelGrid>
       		<x:htmlTag value="br"/>
       		<h:outputText value="Votre adresse de livraison :"/>
       		<x:htmlTag value="br"/>
       		<x:htmlTag value="br"/>
       		<h:panelGrid columns="2">
      		<h:outputText value="Votre t�l�phone : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.phone}"/>
       		<h:outputText value="Num�ro de rue : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.streetNumber}"/>
       		<h:outputText value="Votre adresse:"/><h:outputText value="#{clientBean.clientEditBean.currentClient.streetName}"/>
       		<h:outputText value="Votre code postal : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.zipCode}"/>
       		<h:outputText value="Votre ville : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.city}"/>
       		<h:outputText value="Votre pays : "/><h:outputText value="#{clientBean.clientEditBean.currentClient.country}"/>
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
           	<h:commandButton value="Modifier � nouveau" action="editProfile"/>
           	<h:commandButton value="Retourner � la page principale" action="main"/>
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