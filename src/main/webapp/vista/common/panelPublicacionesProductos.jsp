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
          Listado de Publicaciones
        </div>
    </div>
</div>