<div class="jumbotron"  ng-app="cmpy.autenticacion" ng-controller="autenticacionController">                        
    <c:if test="${param.error != null}">
        <div class="alert alert-danger"> 
            ¡Usuario y Clave incorrectas!
        </div>
    </c:if>

    <c:if test="${param.logout != null}">
        <div class="alert alert-success"> 
            ¡Se ha cerrado la sessión!
        </div>
    </c:if>

    <form:form id="loginForm" method="post" action="${rootURL}login">
        <label for="username" class="col-sm-2 control-label">Usuario*</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Usuario" />
        <label for="password" class="col-sm-2 control-label">Clave*</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Clave" />
        <input type="submit" class="btn btn-primary" value="Login">
    </form:form>

</div>