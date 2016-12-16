<script type="text/ng-template" id="loginModal.html" ng-app="cmpy.autenticacion" ng-controller="autenticacionController">
    <div class="modal-header">
        <h3 class="modal-title">Iniciar Sesion!</h3>
    </div>
    <div class="modal-body" ng-app="cmpy.autenticacion" ng-controller="autenticacionController">
        <div>

            <div class="alert alert-danger" ng-show="error">
                ¡Usuario y contraseña incorrectos!
            </div>
            <form id="loginForm" role="form" ng-submit="login()">
                <div class="row form-group">
                    <div class="col-lg-12" style="margin-bottom:5px">
                        Usa tus credenciales o ingresa con tu cuenta de facebook en el enlace de abajo.
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-lg-12">
                        <label for="username" class="col-sm-2 control-label">Usuario*</label>
                        <input type="text" id="username" name="username" ng-model="credentials.username" class="form-control" placeholder="Usuario" />
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-lg-12">
                        <label for="password" class="col-sm-2 control-label">Clave*</label>
                        <input type="password" id="password" name="password" ng-model="credentials.password" class="form-control" placeholder="Clave" />
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-lg-6" style="text-align:center;border: 1px solid #eee;height:44px">
                        <input type="submit" class="btn btn-primary" style="width:50%" value="Entrar">
                    </div>
                    <div class="col-lg-6" style="border: 1px solid #eee">
                        <a class="btn btn-block btn-social btn-facebook" href="${pageContext.request.contextPath}/auth/facebook">
                            <img height="28" width="28" src="${pageContext.request.contextPath}/resources/images/facebook-icon.png" title="Entrar con Facebook">
                            Usa tu cuenta de Facebook
                        </a>         
                    </div>
                </div>
                <a href="${pageContext.request.contextPath}/user/register">¿Prefieres crear una cuenta nueva?</a>
                <a href="#" ng-click="open('resetPassModal.html')">¿Olvidaste tu contraseña?</a>

            </form>

        </div>
    </div>
    <div class="modal-footer">
        <!--<button class="btn btn-primary" type="button" ng-click="ok()">OK</button>-->
        <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
    </div>
</script>