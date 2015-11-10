<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                <c:forEach items="${anuncios}" var="anuncio">   
                    <c:set var="descripcion" value="${anuncio.descripcion}"/>
                    <c:set var="descripcionCorta" value="${fn:substring(descripcion, 0, 50)}" />
                    <div class="publicacion thumbnail col-lg-12">                     
                        <img src="/images/getThumbnail/${anuncio.recursoList[0].ruta}" alt="imagen aqui">
                        <h3><c:out value='${anuncio.titulo}' /></h3>
                        <p><c:out value='${descripcionCorta}...' /></p>                    
                    </div>
                </c:forEach>            

            </div>
        </div>
    </div> 
</div>