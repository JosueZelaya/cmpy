<div class="jumbotron"  ng-app="cmpy.autenticacion" ng-controller="autenticacionController">    
    
    
     <!-- Social Sign In Buttons -->
            <div class="panel panel-default">
                <div class="panel-body">
                    <h2>Social Login</h2>
                    <div class="row social-button-row">
                        <div class="col-lg-4">
                            <!-- Add Facebook sign in button -->
                            <a href="${pageContext.request.contextPath}/auth/facebook"><button class="btn btn-facebook"><i class="icon-facebook"></i> | sing in with facebook</button></a>
                        </div>
                    </div>
                    <div class="row social-button-row">
                        <div class="col-lg-4">
                            <!-- Add Twitter sign in Button -->
                            <a href="${pageContext.request.contextPath}/auth/twitter"><button class="btn btn-twitter"><i class="icon-twitter"></i> | sing in with twitter</button></a>
                        </div>
                    </div>
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

    <form:form id="loginForm" method="post" action="${rootURL}login">
        <label for="username" class="col-sm-2 control-label">Usuario*</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Usuario" />
        <label for="password" class="col-sm-2 control-label">Clave*</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Clave" />
        <input type="submit" class="btn btn-primary" value="Login">
    </form:form>

</div>