<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
<html>
	<head>         
    	<title>Produit : Edition</title>
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
        <h:outputText value="Edition de la catégorie : #{productEditBean.currentProduct.designation}" style="font-weight: bold" styleClass="GenericTable" />
        <x:htmlTag value="br"/>
        <x:htmlTag value="br"/>
      	<h:form id="InfoForm">
       		<h:panelGrid columns="3">
        		<h:outputText value="Id :"/>
       			<x:inputText value="#{productEditBean.currentProduct.id}" required="true"  id="id" displayValueOnly="true"/><h:message for="id" errorStyle="color:red;"/>      		
       			<h:outputText value="Catégorie :" />
       			<h:selectOneMenu value="#{productEditBean.currentProduct.category.name}" disabled="#{productEditBean.stateDisplayValueOnly}">
					<f:selectItems value="#{productEditBean.allCategory}"/>
				</h:selectOneMenu> 
				<x:htmlTag value="br"/>	
       			<h:outputText value="Désignation :" />
       			<x:inputText value="#{productEditBean.currentProduct.designation}" required="true"  id="designation" displayValueOnly="#{productEditBean.stateDisplayValueOnly}"/><h:message for="designation" errorStyle="color:red;"/>
       			<h:outputText value="Prix :" />
       			<x:inputText value="#{productEditBean.currentProduct.price}" required="true"  id="price" displayValueOnly="#{productEditBean.stateDisplayValueOnly}"/><h:message for="price" errorStyle="color:red;"/>
       			<h:outputText value="Description :" />
       			<x:inputText value="#{productEditBean.currentProduct.description}" required="true"  id="description" displayValueOnly="#{productEditBean.stateDisplayValueOnly}"/><h:message for="description" errorStyle="color:red;"/>
       		</h:panelGrid>
       		<x:htmlTag value="br"/>
      		<h:commandButton id="butEditproduct" action="#{productEditBean.editProduct}" value="Editer" rendered="#{productEditBean.stateDisplayValueOnly}" />
      		<h:commandButton id="lienWelcome" action="adminSuccess" value="Retour à la liste des produits" rendered="#{productEditBean.stateDisplayValueOnly}" />
      		<h:commandButton id="butSaveproduct" action="#{productEditBean.saveProduct}" value="Enregistrer" rendered="#{!productEditBean.stateDisplayValueOnly}" />
      		<h:commandButton id="lienAnnuler" action="adminSuccess" value="Annuler" rendered="#{!productEditBean.stateDisplayValueOnly}" /> 
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
    
        