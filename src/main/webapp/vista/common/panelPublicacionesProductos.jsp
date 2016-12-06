<div class="col-md-8 min_cont marcar">
    <div class="panel panel-default min_cont">                    
        <div class="panel-heading" ng-controller="modalController">
            <p>                            
               Publicaciones
               <c:choose>
                    <c:when test="${username=='anonymousUser'}">
<!--                        <button value="VenderYa!" type="submit" class="btn btn-success pull-right" data-toggle="modal" data-target="#loginModal">                            
                            VenderYa!
                        </button>                          -->
                        <button value="VenderYa!" type="button" class="btn btn-success pull-right" ng-click="open('loginModal.jsp','sm')">VenderYa!</button>
                    </c:when>    
                    <c:otherwise>    
                        <button value="VenderYa!" type="submit" class="btn btn-success pull-right" ng-click="open('venderModal.jsp','lg')">
                            VenderYa!
                        </button>
                    </c:otherwise>
                </c:choose>                                                   
            </p>
        </div>  

        <div id="publicacionesProductos" class="panel-body">
            
            <%@include file="productos.jsp" %>  
           
        </div>
    </div>
</div>
