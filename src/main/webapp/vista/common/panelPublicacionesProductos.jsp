<div class="col-md-8 min_cont marcar">
    <div class="panel panel-default min_cont">                    
        <div class="panel-heading">
            <p>                            
               Publicaciones
               <c:choose>
                    <c:when test="${username=='anonymousUser'}">
                        <button value="VenderYa!" type="submit" class="btn btn-success pull-right"  data-toggle="modal" data-target="#loginModal">
                            VenderYa!
                        </button>                          
                    </c:when>    
                    <c:otherwise>    
                        <button value="VenderYa!" type="submit" class="btn btn-success pull-right"  data-toggle="modal" data-target="#venderModal">
                            VenderYa!
                        </button>
                    </c:otherwise>
                </c:choose>                                                   
            </p>
        </div>  

        <div class="panel-body">
            
            <div class="row">
                
                
                <c:forEach items="${publicaciones}" var="publicacion">   
                    <c:set var="descripcion" value="${publicacion.descripcion}"/>
                    <c:set var="descripcionCorta" value="${fn:substring(descripcion, 0, 55)}" />

                    <div class="col-sm-6 col-md-4">
                    <div style="height: 400px;" class="thumbnail">                                             
                      <img src="/cmpy/images/getThumbnail/${publicacion.recursoList[0].ruta}" alt="imagen aqui">                                            
                      <div class="caption">
                        <h3>${publicacion.titulo}</h3>
                        <p>${descripcionCorta}...</p>
                        <h4>$ ${publicacion.productoList[0].precio}</h5>
                        <p><a href="#" class="btn btn-success" role="button"><span class="glyphicon glyphicon-shopping-cart"></span> Agregar</a> 
                           <a href="#" class="btn btn-default" role="button">Ver Mas...</a>
                        </p>
                      </div>
                    </div>
                  </div>

                </c:forEach>                                                  
                
            </div>
                        
            <div id="publicacionesPager" class="col-sm-12 col-md-12" totalPages="2"></div>
            <div id="totalPages" hidden>2</div>
            
        </div>
    </div>
</div>