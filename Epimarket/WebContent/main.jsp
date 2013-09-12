<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Epimarket</title>
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
			<h:form id="userForm">
				<h:outputText value="Bienvenue #{clientBean.clientEditBean.currentClient.login}"/>
				<x:htmlTag value="br"/>
				<x:htmlTag value="br"/>
				<h:commandLink value="Mon Compte" action="#{clientBean.editProfile}"/>
				<h:commandLink value="Mon Historique" action="record"/>
				<h:commandLink value="Se Déconnecter" action="index"/>
			</h:form>
			<h:graphicImage value="includes/css/images/separateur.png" />
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:panelGrid columns="3" border="1" columnClasses="Menu, Produit, Panier">
			<h:form id="MenuForm">
            <x:dataTable    border="1"
            				id="categoryList" 
                            value='#{clientBean.allCategory}' 
                            var='category' 
                            binding="#{clientBean.dataTable}"
                            styleClass="GenericTable"  >
           	<f:facet name="header">
           	<h:outputText value="Menu"/>
         	</f:facet>
          	<h:column> 
			<h:commandLink id="chooseCategory" action="#{clientBean.selectCategory}" ><h:outputText value='#{category.name}' /></h:commandLink>              
          	</h:column>
          	</x:dataTable>
           	</h:form>
			<h:form id="ProduitForm">
            <x:dataTable    border="1"
            				id="productList" 
                            value='#{clientBean.allProduct}' 
                            var='product' 
                            binding="#{clientBean.dataTableProduct}"
                            sortColumn="#{clientBean.sortColumn}" 
                            sortAscending="#{clientBean.sortAscending}" 
                            rows="#{clientBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
           	<f:facet name="header">
           	<h:outputText value="Liste des produits"/>
           	</f:facet>
          	<h:column>
             	<f:facet name="header">
              	<x:commandSortHeader columnName="designation" arrow="#{true}" >
               	<h:outputText value="Désignation"/> 
               	</x:commandSortHeader>
              	</f:facet>
              	<h:outputText value='#{product.designation}' />                          
         	</h:column>
           	<h:column>
           		<f:facet name="header">
                <x:commandSortHeader columnName="price" arrow="#{true}" >
              	<h:outputText value="Prix"/> 
              	</x:commandSortHeader>
              	</f:facet>
              	<h:outputText value='#{product.price}' />                          
           	</h:column>
          	<h:column>
            	<f:facet name="header">
               	<x:commandSortHeader columnName="description" arrow="#{true}" >
              	<h:outputText value="Description"/> 
              	</x:commandSortHeader>
            	</f:facet>
              	<h:outputText value='#{product.description}' />                          
         	</h:column>
          	<h:column>
              	<f:facet name="header">
              	<h:outputText value="Achat"/> 
             	</f:facet>
				<h:commandLink id="addBasket" action="#{clientBean.addBasket}" ><h:graphicImage value="includes/css/images/panier.png" alt="Ajouter au panier" title="Panier" style="border:0"/></h:commandLink>                                   
           	</h:column>
         	</x:dataTable>
         	<x:htmlTag value="br"/>
         	<x:dataScroller id="scroll_1" for="productList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
                <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.gif" border="1" />    </f:facet>
                <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.gif" border="1" />     </f:facet>
                <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.gif" border="1" /> </f:facet>
                <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.gif" border="1" />     </f:facet>
                <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.gif" border="1" />       </f:facet>
                <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.gif" border="1" />       </f:facet>
            </x:dataScroller>
			</h:form>
			<h:form id="BasketForm">
            <x:dataTable    border="1" id="basketList" 
                            value='#{clientBean.allOrderline}' 
                            var='orderline' 
                            binding="#{clientBean.dataTableBasket}"
                            sortColumn="#{clientBean.sortColumnBasket}" 
                            sortAscending="#{clientBean.sortAscending}" 
                            rows="#{clientBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
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
              	<h:outputText value="Quantité" /> 
             	</x:commandSortHeader>
          	</f:facet>
              	<h:outputText value='#{orderline.quantity}' />                          
        	</h:column>
           	<h:column>
         	<f:facet name="header">
               	<h:outputText value="Retirer"/> 
           	</f:facet>
			<h:commandLink id="delBasket" action="#{clientBean.deleteItemFromBasket}" ><h:graphicImage value="includes/css/images/delete.png" alt="Retire" title="Retire" style="border:0"/></h:commandLink>                      
        	</h:column>
          	</x:dataTable>
          	<x:htmlTag value="br"/>
       		<h:outputText value="Total : #{clientBean.totalBasket} euros"/> 
         	<x:htmlTag value="br"/>
         	<x:htmlTag value="br"/>
     		<h:commandButton value="Valider ma commande" action="#{clientBean.checkout}" />
			</h:form>
			</h:panelGrid>
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:graphicImage value="includes/css/images/separateur.png" />
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:outputText id="Footer" value="#{message.copyright}"/>
	    </body>
	</html>
</f:view>