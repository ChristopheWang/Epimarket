<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Nouveau compte client</title>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <link href="includes/css/MainPage.css" rel="stylesheet" type="text/css" />  
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
	    	<h3><h:outputText value="#{message.editProfil}"/></h3>
	        <h:form id="InfoForm">
       		<h:outputText value="Vos informations personnelles :"/>
       		<x:htmlTag value="br"/>
       		<x:htmlTag value="br"/>
       		<h:panelGrid columns="3">
       			<h:outputText value="Votre identifiant :"/><h:outputText value="#{clientBean.clientEditBean.currentClient.login}"/>
       			<h:column/>
       			<h:outputText value="Votre mot de passe :"/>
       			<x:inputSecret value="#{clientBean.clientEditBean.currentClient.password}" required="true" redisplay="false" id="MotdePasse"/><h:message for="MotdePasse" errorStyle="color:red"/>
       			<h:outputText value="Votre nom :"/>
       			<x:inputText value="#{clientBean.clientEditBean.currentClient.lastName}" required="true"  id="Nom"/><h:message for="Nom" errorStyle="color:red;"/>
	       		<h:outputText value="Votre prénom :"/>
	       		<x:inputText value="#{clientBean.clientEditBean.currentClient.firstName}" required="true"  id="Prenom"/><h:message for="Prenom" errorStyle="color:red;"/>
	        	<h:outputText value="Votre sexe :"/>
	        	<h:selectOneRadio value="#{clientBean.clientEditBean.currentClient.sexe}" required="true" id="Sexe">
                	<f:selectItem itemLabel="Homme" itemValue="homme"/>
               		<f:selectItem itemLabel="Femme" itemValue="femme"/>
                </h:selectOneRadio>
          		<h:message for="Sexe" errorStyle="color:red;"/>
          		<h:outputText value="Votre email :"/>
          		<x:inputText value="#{clientBean.clientEditBean.currentClient.email}" required="true" id="Email">
          			<x:validateEmail message="Email invalide"/>
          		</x:inputText>
          		<h:message for="Email" errorStyle="color:red"/>
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
       		<h:outputText value="Votre adresse de livraison :"/>
       		<x:htmlTag value="br"/>
       		<x:htmlTag value="br"/>
       		<h:panelGrid columns="3">
      			<h:outputText value="Téléphone :"/>
      			<x:inputText value="#{clientBean.clientEditBean.currentClient.phone}" required="true" id="Telephone"/><h:message for="Telephone" errorStyle="color:red"/>
       			<h:outputText value="Numéro de rue:"/>
       			<x:inputText value="#{clientBean.clientEditBean.currentClient.streetNumber}" required="true" id="Numero"/><h:message for="Numero" errorStyle="color:red"/> 
         		<h:outputText value="Adresse :"/>
         		<x:inputText value="#{clientBean.clientEditBean.currentClient.streetName}" required="true" id="Adresse"/><h:message for="Adresse" errorStyle="color:red"/> 
          		<h:outputText value="Code Postal :"/>
          		<x:inputText value="#{clientBean.clientEditBean.currentClient.zipCode}" required="true" id="CodePostal"/><h:message for="CodePostal" errorStyle="color:red"/> 
           		<h:outputText value="Ville :"/>
				<x:inputText value="#{clientBean.clientEditBean.currentClient.city}" required="true" id="Ville"/><h:message for="Ville" errorStyle="color:red"/>
               	<h:outputText value="Pays :"/>
              	<x:selectOneCountry value="#{clientBean.clientEditBean.currentClient.country}"/>
                <h:column/> 
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
       		<h:commandButton value="Valider" action="#{clientBean.clientEditBean.confirmEditProfile}" />
       		<h:commandButton value="Annuler" action="main" immediate="true"/>
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