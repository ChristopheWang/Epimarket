<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Votre Commande</title>
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
			<h:form id="OrderForm">
			<h:outputText value="Résumé de votre commande :"/>
	        <x:htmlTag value="br"/>
	        <x:htmlTag value="br"/>
         <x:dataTable    	border="1" id="basketList" 
                       		value='#{clientBean.allOrderline}' 
                            var='orderline' 
                            binding="#{clientBean.dataTableBasket}"
                            sortColumn="#{clientBean.sortColumnBasket}" 
                            sortAscending="#{clientBean.sortAscending}" 
                            rows="#{clientBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable">
              <f:facet name="header"> 
              	<h:outputText value="Mon panier"/> 
               </f:facet>
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="productId" arrow="#{true}" >
                            <h:outputText value="Désignation"/> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.product.designation}' />                          
                </h:column>
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="clientId" arrow="#{true}" >
                            <h:outputText value="Prix unitaire"/> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.product.price}' />                          
                </h:column>
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="quantity" arrow="#{true}" >
                            <h:outputText value="Quantité"/> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.quantity}' />                          
                </h:column>
             </x:dataTable>
             <x:htmlTag value="br"/>
             <h:outputText value="Total : #{clientBean.totalBasket} euros"/>
       		<x:htmlTag value="br"/>
       		<x:htmlTag value="br"/>
           	<h:commandButton value="Valider" action="#{clientBean.validateOrder}"/>
           	<h:commandButton value="Continuer mes achats" action="main"/>
           	<x:htmlTag value="br"/>
           	<x:htmlTag value="br"/>
			<h:commandButton action="#{clientBean.getPdf}" value="Exporter le détail de votre commande sous pdf" />
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