<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
<html>
	<head>           
    	<title>Categorie : Edition</title>
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
        <h:outputText value="Edition de la catégorie : #{categoryEditBean.currentCategory.name}" style="font-weight: bold" styleClass="GenericTable" />
        <x:htmlTag value="br"/>
        <x:htmlTag value="br"/>
     	<h:form id="InfoForm">
       		<h:panelGrid columns="3">
        		<h:outputText value="Id :"/>
       			<x:inputText value="#{categoryEditBean.currentCategory.id}" required="true"  id="id" displayValueOnly="true"/><h:message for="id" errorStyle="color:red;"/>      		
       			<h:outputText value="Nom :" />
       			<x:inputText value="#{categoryEditBean.currentCategory.name}" required="true"  id="name" displayValueOnly="#{categoryEditBean.stateDisplayValueOnly}"/><h:message for="name" errorStyle="color:red;"/>
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
            <h:commandButton id="butEditcategory" action="#{categoryEditBean.editCategory}" value="Editer" rendered="#{categoryEditBean.stateDisplayValueOnly}" />
            <h:commandButton id="lienWelcome" action="adminSuccess" value="Retour à la liste des categories" rendered="#{categoryEditBean.stateDisplayValueOnly}" />
            <h:commandButton id="butSavecategory" action="#{categoryEditBean.saveCategory}" value="Enregistrer" rendered="#{!categoryEditBean.stateDisplayValueOnly}" />
       		<h:commandButton id="lienAnnuler" action="adminSuccess" value="Annuler" rendered="#{!categoryEditBean.stateDisplayValueOnly}" /> 
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
    
        