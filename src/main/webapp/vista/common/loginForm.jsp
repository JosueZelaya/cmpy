<div>

    <div class="alert alert-danger" ng-show="error">
        ¡Usuario y contraseña incorrectos!
    </div>
    <form id="loginForm" role="form" ng-submit="login()">

        <div class="row  col-lg-4">
            Usa tus credenciales o ingresa con tu cuenta de facebook en el enlace de abajo.
        </div>
        <br/>

        <div class="row  col-lg-4">
            <label for="username" class="col-sm-2 control-label">Usuario*</label>
            <input type="text" id="username" name="username" ng-model="credentials.username" class="form-control" placeholder="Usuario" />
        </div>
        <div class="row  col-lg-4">
            <label for="password" class="col-sm-2 control-label">Clave*</label>
            <input type="password" id="password" name="password" ng-model="credentials.password" class="form-control" placeholder="Clave" />
        </div>

        <br/><br/><br/><br/>

        <div class="row">
            <div class="form-group col-lg-8">
                <input type="submit" class="btn btn-primary" value="Entrar">
            </div>
            <div class="form-group col-lg-4">

                <a class="btn btn-block btn-social btn-facebook" href="${pageContext.request.contextPath}/auth/facebook">
                    <img height="28" width="28" src="${pageContext.request.contextPath}/resources/images/facebook-icon.png" title="Entrar con Facebook">
                    Usa tu cuenta de Facebook
                </a>
                <a href="${pageContext.request.contextPath}/user/register">¿Prefieres crear una cuenta nueva?</a><br/>                
            </div>
        </div>

    </form>

</div>