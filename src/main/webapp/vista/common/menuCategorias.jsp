<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-2 min_cont marcar  panelCategorias">
    <div class="panel panel-default min_cont">
        <div class="panel-heading">Categorias</div>
        <div class="panel-body">
            <div id="level1" class="list-group cat level1">
                <c:forEach items="${categorias}" var="categoria">
                    <c:if test="${categoria.fkCategoria == null}">
                        <a id="cat${categoria.categoriaId}" href="#" class="list-group-item" data-toggle="collapse" data-target="#ref${categoria.categoriaId}">
                            ${categoria.nombre}
                        </a>       
                    </c:if>
                    <div id="ref${categoria.categoriaId}" class="list-group cat level2 collapse">
                        <c:forEach items="${categorias}" var="sub_categoria">
                            <c:if test="${sub_categoria.fkCategoria.categoriaId == categoria.categoriaId }">             
                                <c:if test="${sub_categoria.fkCategoria.fkCategoria == null}">
                                    <a id="cat${sub_categoria.categoriaId}" href="#" class="list-group-item" data-toggle="collapse" data-target="#ref${sub_categoria.categoriaId}">
                                        ${sub_categoria.nombre}
                                    </a>               
                                </c:if>
                                <div id="ref${sub_categoria.categoriaId}" class="list-group cat level3 collapse">
                                    <c:forEach items="${categorias}" var="sub_sub_categoria">
                                        <c:if test="${sub_sub_categoria.fkCategoria.categoriaId == sub_categoria.categoriaId }">
                                            <a id="cat${sub_sub_categoria.categoriaId}" href="#" class="list-group-item">
                                                ${sub_sub_categoria.nombre}
                                            </a>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<style>
    .list-group.cat
    {
        margin: 0%;
        margin-left: 3%;
        margin-right: -3%;
        width:97%;

    }
    .level1.cat{
        background-color: #EEE;        
    }
    .level2.cat{
        background-color: #DDD;        
    }
    .level3.cat{
        background-color: #CCC;        
    }
    a.list-group-item
    {
        font-size: 10px;
    }

</style>
