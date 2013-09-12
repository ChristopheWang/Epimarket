<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Mon historique d'achats</title>
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
	        <h:form id="InfoForm">  
	        <h:outputText value="Mon historique d'achat :"/>
	        <x:htmlTag value="br"/>
	        <x:htmlTag value="br"/>
          	<x:dataTable    border="1" id="recordList" 
                            value='#{clientBean.allRecord}' 
                            var='record' 
                            binding="#{clientBean.dataTableBasket}"
                            sortColumn="#{clientBean.sortColumnBasket}" 
                            sortAscending="#{clientBean.sortAscending}" 
                            rows="#{clientBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
          	<h:column>
           	<f:facet name="header">
         	<x:commandSortHeader columnName="productId" arrow="#{true}" >
           		<h:outputText value='Désignation' style="font-weight: bold" /> 
            </x:commandSortHeader>
          	</f:facet>
         		<h:outputText value='#{record.product.designation}' />                          
           	</h:column>
          	<h:column>
           	<f:facet name="header">
           	<x:commandSortHeader columnName="clientId" arrow="#{true}" >
             	<h:outputText value='Prix unitaire' style="font-weight: bold" /> 
          	</x:commandSortHeader>
           	</f:facet>
          		<h:outputText value='#{record.product.price}' />                          
           	</h:column>
           	<h:column>
          	<f:facet name="header">
          	<x:commandSortHeader columnName="quantity" arrow="#{true}" >
              	<h:outputText value='Quantité' style="font-weight: bold" /> 
         	</x:commandSortHeader>
           	</f:facet>
          		<h:outputText value='#{record.quantity}' />                          
         	</h:column>
         	<h:column>
           	<f:facet name="header">
         	<x:commandSortHeader columnName="date" arrow="#{true}" >
             	<h:outputText value='Date' style="font-weight: bold" /> 
           	</x:commandSortHeader>
          	</f:facet>
            	<h:outputText value='#{record.date}' />                          
        	</h:column>
          	</x:dataTable>
           	<x:htmlTag value="br"/>
       		<h:commandButton value="Retourner au magasin" action="main" />
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