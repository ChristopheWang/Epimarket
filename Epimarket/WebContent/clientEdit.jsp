<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	<head>           
    	<title>Client : Edition</title>
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
        <h:outputText value="Edition du client : #{clientEditBean.currentClient.login}" style="font-weight: bold" styleClass="GenericTable" />
        <x:htmlTag value="br"/>
        <x:htmlTag value="br"/>
     	<h:form id="InfoForm">
       		<h:outputText value="Vos informations personnelles :"/>
       		<x:htmlTag value="br"/>
       		<x:htmlTag value="br"/>
       		<h:panelGrid columns="3">
        		<h:outputText value="Id :"/>
       			<x:inputText value="#{clientEditBean.currentClient.id}" required="true"  id="id" displayValueOnly="true"/><h:message for="id" errorStyle="color:red;"/>      		
       			<h:outputText value="Identifiant :" />
       			<x:inputText value="#{clientEditBean.currentClient.login}" required="true"  id="login" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="login" errorStyle="color:red;"/>
       			<h:outputText value="Nom :"/>
       			<x:inputText value="#{clientEditBean.currentClient.lastName}" required="true"  id="Nom" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="Nom" errorStyle="color:red;"/>
	       		<h:outputText value="Prénom :"/>
	       		<x:inputText value="#{clientEditBean.currentClient.firstName}" required="true"  id="Prenom" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="Prenom" errorStyle="color:red;"/>
	        	<h:outputText value="Sexe :"/>
	        		<h:selectOneRadio value="#{clientEditBean.currentClient.sexe}" required="true" id="Sexe" disabled="#{clientEditBean.stateDisplayValueOnly}">
                	<f:selectItem itemLabel="Homme" itemValue="homme"/>
               		<f:selectItem itemLabel="Femme" itemValue="femme"/>
                </h:selectOneRadio>
          		<h:message for="Sexe" errorStyle="color:red;"/>
          		<h:outputText value="Email :"/>
          		<x:inputText value="#{clientEditBean.currentClient.email}" required="true" id="Email" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}">
          			<x:validateEmail message="Email invalide"/>
          		</x:inputText>
          		<h:message for="Email" errorStyle="color:red"/>
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
       		<h:outputText value="Adresse de livraison :"/>
       		<x:htmlTag value="br"/>
       		<x:htmlTag value="br"/>
       		<h:panelGrid columns="3">
      			<h:outputText value="Téléphone :"/>
      			<x:inputText value="#{clientEditBean.currentClient.phone}" required="true" id="Telephone" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="Telephone" errorStyle="color:red"/>
       			<h:outputText value="Numéro de rue:"/>
       			<x:inputText value="#{clientEditBean.currentClient.streetNumber}" required="true" id="Numero" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="Numero" errorStyle="color:red"/> 
         		<h:outputText value="Adresse :"/>
         		<x:inputText value="#{clientEditBean.currentClient.streetName}" required="true" id="Adresse" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="Adresse" errorStyle="color:red"/> 
          		<h:outputText value="Code Postal :"/>
          		<x:inputText value="#{clientEditBean.currentClient.zipCode}" required="true" id="CodePostal" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="CodePostal" errorStyle="color:red"/> 
           		<h:outputText value="Ville :"/>
				<x:inputText value="#{clientEditBean.currentClient.city}" required="true" id="Ville" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/><h:message for="Ville" errorStyle="color:red"/>
               	<h:outputText value="Pays :"/>
              	<x:selectOneCountry value="#{clientEditBean.currentClient.country}" displayValueOnly="#{clientEditBean.stateDisplayValueOnly}"/>
                <h:column/> 
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
            <h:commandButton id="butEditclient" action="#{clientEditBean.editClient}" value="Editer" rendered="#{clientEditBean.stateDisplayValueOnly}" />
           	<h:commandButton id="lienWelcome" action="adminSuccess" value="Retour à la liste des Clients" rendered="#{clientEditBean.stateDisplayValueOnly}" />
            <h:commandButton id="butSaveclient" action="#{clientEditBean.saveClient}" value="Enregistrer" rendered="#{!clientEditBean.stateDisplayValueOnly}" />
            <h:commandButton id="lienAnnuler" action="adminSuccess" value="Annuler" rendered="#{!clientEditBean.stateDisplayValueOnly}" /> 
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
    
        