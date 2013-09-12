<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://myfaces.apache.org/sandbox" prefix="s"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>

<f:view>
<f:loadBundle basename="message" var="message"/>
	<html>
	    <head>           
	        <title>Admin Epimarket</title>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <link href="includes/css/AdminPage.css" rel="stylesheet" type="text/css" />   
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
				<h:outputText value="Interface d'administration"/>
				<x:htmlTag value="br"/>
				<x:htmlTag value="br"/>
				<h:commandLink value="Se Déconnecter" action="index"/>
			</h:form>
			<h:graphicImage value="includes/css/images/separateur.png" />
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
        	<s:accordionPanel layout="toggling">
           	<x:panelTab label="Categories">
             <h:form>
            <x:dataTable    border="1" id="categoryList" 
                            value='#{categoryBean.allCategory}' 
                            var='category' 
                            binding="#{categoryBean.dataTable}"
                            sortColumn="#{categoryBean.sortColumn}" 
                            sortAscending="#{categoryBean.sortAscending}" 
                            rows="#{categoryBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
         	<f:facet name="header"> 
        		<h:outputText value="Gestion des categories"/>   
          	</f:facet>
          	<h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="id" arrow="#{true}" >
                            <h:outputText value='id' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{category.id}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="name" arrow="#{true}" >
                            <h:outputText value='nom' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{category.name}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='éditer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="editCategory" action="#{categoryBean.edit}" ><h:outputText value='éditer' /></h:commandLink>
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='supprimer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="delCategory" action="#{categoryBean.delete}" ><h:outputText value='supprimer' /></h:commandLink>
                </h:column>
            </x:dataTable>
            <h:column/>
            <x:dataScroller id="scroll_1" for="categoryList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
                <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.gif" border="1" />    </f:facet>
                <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.gif" border="1" />     </f:facet>
                <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.gif" border="1" /> </f:facet>
                <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.gif" border="1" />     </f:facet>
                <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.gif" border="1" />       </f:facet>
                <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.gif" border="1" />       </f:facet>
            </x:dataScroller>
            <x:dataScroller id="scroll_2" for="categoryList" rowsCountVar="rowsCount" displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true" pageIndexVar="pageIndex" >
                <h:outputFormat value="#{message['info.scroller.main']}" styleClass="standard" > 
                    <f:param value="#{rowsCount}" />
                    <f:param value="#{displayedRowsCountVar}" />
                    <f:param value="#{firstRowIndex}" />
                    <f:param value="#{lastRowIndex}" />
                    <f:param value="#{pageIndex}" />
                    <f:param value="#{pageCount}" />
                </h:outputFormat>
            </x:dataScroller>
			</h:form>
			<h:form>  
               		<x:inputText value="#{categoryBean.categoryEditBean.currentCategory.name}" required="true"  id="category"/><h:message for="category" errorStyle="color:red;"/>
       				<h:column/>
       				<h:commandButton value="Ajouter" action="#{categoryBean.createCategory}" />
       		 </h:form>
            </x:panelTab>
            
            
            
            
           	<x:panelTab label="Produits">
             <h:form>
            <x:dataTable    border="1" id="productList" 
                            value='#{productBean.allProduct}' 
                            var='product' 
                            binding="#{productBean.dataTable}"
                            sortColumn="#{productBean.sortColumn}" 
                            sortAscending="#{productBean.sortAscending}" 
                            rows="#{productBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
               <f:facet name="header"> 
               	<h:outputText value="Gestion des produits"/>   
               </f:facet>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="id" arrow="#{true}" >
                            <h:outputText value='id' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{product.id}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="categoryId" arrow="#{true}" >
                            <h:outputText value='catégorie' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{product.category.name}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="designation" arrow="#{true}" >
                            <h:outputText value='désignation' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{product.designation}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="price" arrow="#{true}" >
                            <h:outputText value='prix' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{product.price}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="description" arrow="#{true}" >
                            <h:outputText value='description' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{product.description}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='éditer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="editProduct" action="#{productBean.edit}" ><h:outputText value='éditer' /></h:commandLink>
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='supprimer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="delProduct" action="#{productBean.delete}" ><h:outputText value='supprimer' /></h:commandLink>
                </h:column>
            </x:dataTable>
            <h:column/>
            <x:dataScroller id="scroll_1" for="productList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
                <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.gif" border="1" />    </f:facet>
                <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.gif" border="1" />     </f:facet>
                <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.gif" border="1" /> </f:facet>
                <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.gif" border="1" />     </f:facet>
                <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.gif" border="1" />       </f:facet>
                <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.gif" border="1" />       </f:facet>
            </x:dataScroller>
            <x:dataScroller id="scroll_2" for="productList" rowsCountVar="rowsCount" displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true" pageIndexVar="pageIndex" >
                <h:outputFormat value="#{message['info.scroller.main']}" styleClass="standard" > 
                    <f:param value="#{rowsCount}" />
                    <f:param value="#{displayedRowsCountVar}" />
                    <f:param value="#{firstRowIndex}" />
                    <f:param value="#{lastRowIndex}" />
                    <f:param value="#{pageIndex}" />
                    <f:param value="#{pageCount}" />
                </h:outputFormat>
            </x:dataScroller>
			</h:form>
			<h:form>  
			<h:panelGrid columns="3">
       			<h:outputText value="Catégorie :" />
       			<h:selectOneMenu value="#{productBean.productEditBean.currentProduct.category.name}" required="true">
					<f:selectItems value="#{productBean.productEditBean.allCategory}"/>
				</h:selectOneMenu>
				<x:htmlTag value="br"/>	
       			<h:outputText value="Désignation :" />
       			<x:inputText value="#{productBean.productEditBean.currentProduct.designation}" required="true"  id="designation"/><h:message for="designation" errorStyle="color:red;"/>
       			<h:outputText value="Prix :" />
       			<x:inputText value="#{productBean.productEditBean.currentProduct.price}" required="true"  id="price"/><h:message for="price" errorStyle="color:red;"/>
       			<h:outputText value="Description :" />
       			<x:inputText value="#{productBean.productEditBean.currentProduct.description}" required="true"  id="description"/><h:message for="description" errorStyle="color:red;"/>
       				<h:column/>
       				<h:commandButton value="Ajouter" action="#{productBean.createProduct}" />
       		</h:panelGrid>
       		 </h:form>
            </x:panelTab>
            
            
            
           	<x:panelTab label="Clients">
           	<h:form id="ClientForm">
            <x:dataTable    border="1" id="clientList" 
                            value='#{clientBean.allClient}' 
                            var='client' 
                            binding="#{clientBean.dataTable}"
                            sortColumn="#{clientBean.sortColumn}" 
                            sortAscending="#{clientBean.sortAscending}" 
                            rows="#{clientBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
               <f:facet name="header"> 
               	<h:outputText value="Gestion des clients"/>   
               </f:facet>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="id" arrow="#{true}" >
                            <h:outputText value='id' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{client.id}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="login" arrow="#{true}" >
                            <h:outputText value='identifiant' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{client.login}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="password" arrow="#{true}" >
                            <h:outputText value='mot de passe' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.password}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="lastName" arrow="#{true}" >
                            <h:outputText value='nom' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.lastName}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="firstName" arrow="#{true}" >
                            <h:outputText value='prénom' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.firstName}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="sexe" arrow="#{true}" >
                            <h:outputText value='sexe' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.sexe}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="email" arrow="#{true}" >
                            <h:outputText value='email' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.email}' />
                </h:column>   
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="phone" arrow="#{true}" >
                            <h:outputText value='téléphone' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.phone}' />
                </h:column>   
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="streetNumber" arrow="#{true}" >
                            <x:outputText  value='numéro de rue' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.streetNumber}' />
                </h:column>
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="streetName" arrow="#{true}" >
                            <x:outputText  value='adresse' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.streetName}' />
                </h:column>      
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="city" arrow="#{true}" >
                            <x:outputText  value='ville' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.city}' />
                </h:column>
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="zipCode" arrow="#{true}" >
                            <x:outputText  value='code postal' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.zipCode}' />
                </h:column>
               <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="country" arrow="#{true}" >
                            <x:outputText  value='Pays' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{client.country}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='éditer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="editClient" action="#{clientBean.edit}" ><h:outputText value='éditer' /></h:commandLink>
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='supprimer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="delClient" action="#{clientBean.delete}" ><h:outputText value='supprimer' /></h:commandLink>
                </h:column>
            </x:dataTable>
            <x:dataScroller id="scroll_1" for="clientList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
                <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.gif" border="1" />    </f:facet>
                <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.gif" border="1" />     </f:facet>
                <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.gif" border="1" /> </f:facet>
                <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.gif" border="1" />     </f:facet>
                <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.gif" border="1" />       </f:facet>
                <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.gif" border="1" />       </f:facet>
            </x:dataScroller>
            <x:dataScroller id="scroll_2" for="clientList" rowsCountVar="rowsCount" displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true" pageIndexVar="pageIndex" >
                <h:outputFormat value="#{message['info.scroller.main']}" styleClass="standard" > 
                    <f:param value="#{rowsCount}" />
                    <f:param value="#{displayedRowsCountVar}" />
                    <f:param value="#{firstRowIndex}" />
                    <f:param value="#{lastRowIndex}" />
                    <f:param value="#{pageIndex}" />
                    <f:param value="#{pageCount}" />
                </h:outputFormat>
            </x:dataScroller>
			</h:form>
            </x:panelTab>
            
            
            
             <x:panelTab label="Liste des commandes">
             <h:form>
            <x:dataTable    border="1" id="orderlineList" 
                            value='#{orderlineBean.allOrderline}' 
                            var='orderline' 
                            binding="#{orderlineBean.dataTable}"
                            sortColumn="#{orderlineBean.sortColumn}" 
                            sortAscending="#{orderlineBean.sortAscending}" 
                            rows="#{orderlineBean.rowCount}"
                            rowClasses="ligne1,ligne2"
                            styleClass="GenericTable"  >
               <f:facet name="header"> 
               	<h:outputText value="Gestion des lignes de commandes"/>   
               </f:facet>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="id" arrow="#{true}" >
                            <h:outputText value='id' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.id}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="clientId" arrow="#{true}" >
                            <h:outputText value='client' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.client.login}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="productId" arrow="#{true}" >
                            <h:outputText value='produit' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.product.designation}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="quantity" arrow="#{true}" >
                            <h:outputText value='quantité' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.quantity}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="status" arrow="#{true}" >
                            <h:outputText value='status' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderlineBean.statusOrderline}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="date" arrow="#{true}" >
                            <h:outputText value='date' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{orderline.date}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='éditer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="editOrderline" action="#{orderlineBean.edit}" ><h:outputText value='éditer' /></h:commandLink>
                </h:column>
                <h:column>
                    <f:facet name="header">
                            <h:outputText value='supprimer' style="font-weight: bold" /> 
                    </f:facet>
                    <h:commandLink id="delOrderline" action="#{orderlineBean.delete}" ><h:outputText value='supprimer' /></h:commandLink>
                </h:column>
            </x:dataTable>
            <h:column/>
            <x:dataScroller id="scroll_1" for="orderlineList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
                <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.gif" border="1" />    </f:facet>
                <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.gif" border="1" />     </f:facet>
                <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.gif" border="1" /> </f:facet>
                <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.gif" border="1" />     </f:facet>
                <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.gif" border="1" />       </f:facet>
                <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.gif" border="1" />       </f:facet>
            </x:dataScroller>
            <x:dataScroller id="scroll_2" for="orderlineList" rowsCountVar="rowsCount" displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true" pageIndexVar="pageIndex" >
                <h:outputFormat value="#{message['info.scroller.main']}" styleClass="standard" > 
                    <f:param value="#{rowsCount}" />
                    <f:param value="#{displayedRowsCountVar}" />
                    <f:param value="#{firstRowIndex}" />
                    <f:param value="#{lastRowIndex}" />
                    <f:param value="#{pageIndex}" />
                    <f:param value="#{pageCount}" />
                </h:outputFormat>
            </x:dataScroller>
			</h:form>
            </x:panelTab>
            
            
            
           	<x:panelTab label="Statistique">
           	<h:form id="stats">
				<h:outputText value="Informations complémentaires - Statistiques"/>
				<x:htmlTag value="br"/>
				<x:htmlTag value="br"/>
            <c:chart id="chart1" datasource="#{chartHelper.locationClient}" type="pie" title="Répartition géographique des clients" is3d="true" alpha="75" startAngle="90" legend="true" height="200" width="300"></c:chart>
            <c:chart id="chart2" datasource="#{chartHelper.priceProduct}" type="pie" title="Prix des produits" is3d="true" alpha="75" startAngle="90" legend="true" height="200" width="300"></c:chart>
			</h:form>
            </x:panelTab>
        	</s:accordionPanel>
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:graphicImage value="includes/css/images/separateur.png" />
			<x:htmlTag value="br"/>
			<x:htmlTag value="br"/>
			<h:outputText id="Footer" value="#{message.copyright}"/>
	    </body>
	</html>
</f:view>