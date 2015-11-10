<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Usando totalmente angular --%>

<div class="col-md-2 min_cont marcar  panelCategorias" ng-controller="catTodoController">
    <div class="panel panel-default min_cont" >
        <div class="panel-heading">Categorias</div>
        <div class="panel-body">
            <div id="refcat_ini" class="list-group cat level1" ng-repeat="categoria in categorias">
                <a id="cat_{{categoria.id}}" href="#" class="list-group-item" ng-click="categoria.collapse = !categoria.collapse">
                    {{categoria.nombre}}
                </a>   
                <div id="refcat_{{categoria.id}}" class="list-group cat level2" collapse="categoria.collapse" ng-repeat="sub_categoria in categoria.hijos">
                    <a id="cat_{{sub_categoria.id}}" href="#" class="list-group-item" ng-click="sub_categoria.collapse = !sub_categoria.collapse">
                        {{sub_categoria.nombre}}
                    </a>
                    <div id="refcat_{{categoria.id}}" class="list-group cat level3" collapse="sub_categoria.collapse" ng-repeat="sub_sub_categoria in sub_categoria.hijos">
                        <a id="cat_{{sub_sub_categoria.id}}" href="#" class="list-group-item">
                            {{sub_sub_categoria.nombre}}
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




