<div class="col-md-2 min_cont marcar">                
    <div class="panel panel-default min_cont">
        <div class="panel-heading">Anuncios Pagados</div>
        <div class="panel-body">
            <div class="row">   
                <div class="thumbnail col-lg-12">                    
                    <!--<img src="/cmpy/images/getImage/${recurso.ruta}" alt="imagen aqui">-->
                    <h3>Empresas</h3>
                    <p>Anunciate en la red mas grande de Negocios.<br/>
                        <!--<a href="#" data-toggle="modal" data-target="#anunciarModal">Anunciate Ya!</a>-->  
                        <c:choose>
                            <c:when test="${username=='anonymousUser'}">
                                <button value="VenderYa!" type="submit" class="btn btn-default"  data-toggle="modal" data-target="#loginModal">
                                    Anunciate Ya!
                                </button>                          
                            </c:when>    
                            <c:otherwise>    
                                <button value="Anunciate Ya!" type="submit" class="btn btn-default"  data-toggle="modal" data-target="#anunciarModal">
                                    Anunciate Ya!
                                </button>
                            </c:otherwise>
                        </c:choose>                         
                    </p>                                          
                </div>
                <c:forEach items="${publicaciones}" var="publicacion">   

                    <div class="thumbnail col-lg-12">
                    <c:forEach items="${publicacion.recursoList}" var="recurso"> 
                    <img src="/cmpy/images/getImage/${recurso.ruta}" alt="imagen aqui">
                    <h3><c:out value='${publicacion.titulo}' /></h3>
                    <p><c:out value='${publicacion.descripcion}' /></p>
                    </c:forEach>                                
                    </div>

                </c:forEach>            

            </div>
        </div>
    </div> 
</div>