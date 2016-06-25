<div class="jumbotron"  ng-app="cmpy.autenticacion" ng-controller="autenticacionController">    
    
    
     <!-- Social Sign In Buttons -->
            <div class="panel panel-default">
                <div class="panel-body">
                    <h2>Social Login</h2>
                    <div class="row">
                        <div class="col-lg-4">
                            <!-- Add Facebook sign in button -->                            
                            <a class="btn btn-block btn-social btn-facebook" href="${pageContext.request.contextPath}/auth/facebook"><span class="fa fa-facebook-official"></span>Entrar con facebook</a>                            
                        </div>
                    </div>
<!--                    <div class="row social-button-row">
                        <div class="col-lg-4">
                             Add Twitter sign in Button 
                            <a href="${pageContext.request.contextPath}/auth/twitter"><button class="btn btn-twitter"><i class="icon-twitter"></i> | Entrar con twitter</button></a>
                        </div>
                    </div>-->
                </div>
            </div>
    
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

    <form:form id="loginForm" method="post" action="${rootURL}login/authenticate">
        <label for="username" class="col-sm-2 control-label">Usuario*</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Usuario" />
        <label for="password" class="col-sm-2 control-label">Clave*</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Clave" />
        <input type="submit" class="btn btn-primary" value="Login">
    </form:form>
        
    <div class="row">
        <div class="form-group col-lg-4">
            <!-- Add create user account link -->
            <a href="${pageContext.request.contextPath}/user/register">Crear una Cuenta</a>
        </div>
    </div>

</div>