<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	<head>           
    	<title>Ligne de commande : Edition</title>
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
	<h:outputText value="Edition de la ligne de commande de : #{orderlineEditBean.currentOrderline.client.login}, produit : #{orderlineEditBean.currentOrderline.product.designation}" style="font-weight: bold" styleClass="GenericTable" />
   	<x:htmlTag value="br"/>
   	<x:htmlTag value="br"/>
   	<h:form id="InfoForm">
      	<h:panelGrid columns="3">
      	<h:outputText value="Id :"/>
    	<x:inputText value="#{orderlineEditBean.currentOrderline.id}" required="true"  id="id" displayValueOnly="true"/><h:message for="id" errorStyle="color:red;"/>      		
       	<h:outputText value="Client :" />
       	<h:selectOneMenu value="#{orderlineEditBean.currentOrderline.client.login}" disabled="#{orderlineEditBean.stateDisplayValueOnly}">
			<f:selectItems value="#{orderlineEditBean.allClient}"/>
		</h:selectOneMenu> 
		<x:htmlTag value="br"/>	
		<h:outputText value="Produit :" />
       	<h:selectOneMenu value="#{orderlineEditBean.currentOrderline.product.designation}" disabled="#{orderlineEditBean.stateDisplayValueOnly}">
			<f:selectItems value="#{orderlineEditBean.allProduct}"/>
		</h:selectOneMenu> 
		<x:htmlTag value="br"/>	
       	<h:outputText value="Quantité :" />
       	<x:inputText value="#{orderlineEditBean.currentOrderline.quantity}" required="true"  id="quantity" displayValueOnly="#{orderlineEditBean.stateDisplayValueOnly}"/><h:message for="quantity" errorStyle="color:red;"/>
       	<h:outputText value="Status :" />
       	<h:selectOneMenu value="#{orderlineEditBean.currentOrderline.status}" disabled="#{orderlineEditBean.stateDisplayValueOnly}">
			<f:selectItem itemLabel="bought" itemValue="bought" />
			<f:selectItem itemLabel="basket" itemValue="basket" />
		</h:selectOneMenu> 
		<x:htmlTag value="br"/>	
       	<h:outputText value="Date :" />
       	<x:inputText value="#{orderlineEditBean.currentOrderline.date}" required="true"  id="date" displayValueOnly="#{orderlineEditBean.stateDisplayValueOnly}"/><h:message for="date" errorStyle="color:red;"/>
       	</h:panelGrid>
       	<x:htmlTag value="br"/>
      	<h:commandButton id="butEditorderline" action="#{orderlineEditBean.editOrderline}" value="Editer" rendered="#{orderlineEditBean.stateDisplayValueOnly}" />
       	<h:commandButton id="lienWelcome" action="adminSuccess" value="Retour à la liste des produits" rendered="#{orderlineEditBean.stateDisplayValueOnly}" />
       	<h:commandButton id="butSaveorderline" action="#{orderlineEditBean.saveOrderline}" value="Enregistrer" rendered="#{!orderlineEditBean.stateDisplayValueOnly}" />
      	<h:commandButton id="lienAnnuler" action="adminSuccess" value="Annuler" rendered="#{!orderlineEditBean.stateDisplayValueOnly}" /> 
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
    
        