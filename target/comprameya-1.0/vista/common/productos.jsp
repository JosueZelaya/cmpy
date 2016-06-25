<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="row">
    <c:forEach items="${publicaciones}" var="publicacion">   
        <c:set var="descripcion" value="${publicacion.descripcion}"/>
        <c:set var="descripcionCorta" value="${fn:substring(descripcion, 0, 55)}" />

        <div class="col-sm-6 col-md-4">
        <div style="height: 400px;" class="thumbnail">                                             
          <img src="/images/getThumbnail/${publicacion.recursoList[0].ruta}" alt="imagen aqui">                                            
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

<div id="publicacionesPager" class="col-sm-12 col-md-12" tipo="${tipoPublicacion}" totalPages="${totalPages}"></div>