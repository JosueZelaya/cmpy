<script type="text/ng-template" id="loginModal.html">
    
    <div ng-app="cmpy.autenticacion" ng-controller="autenticacionController">
        <div class="modal-header">
            <a class="btn btn-block btn-default" href="#" ng-click="facebookAuth()">
                <img height="28" width="28" src="${pageContext.request.contextPath}/resources/images/facebook-icon.png" title="Entrar con Facebook">
                Ingresa con tu cuenta de Facebook
            </a>        
        </div>
        <div class="modal-body">
            <div>

                <div class="alert alert-danger" ng-show="error">
                    ¡Usuario y contraseña incorrectos!
                </div>
                <form id="loginForm" role="form" ng-submit="login()">
                    <div class="row form-group">
                        <div class="col-lg-12" style="margin-bottom:5px">
                            Usa tus credenciales o ingresa con tu cuenta de facebook.<br/>
                            <a href="#" ng-click="register()">¿Prefieres crear una cuenta nueva?</a>
                        </div>
                    </div>
                    <div class="row form-group ">
                        <div class="col-lg-12">
                            <label for="username" class="col-sm-2 control-label">Email*</label>
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
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <input type="submit" class="btn btn-block btn-primary" value="Entrar"><br/>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <a href="#" ng-click="open('resetPassModal.html')">¿Olvidaste tu contraseña?</a>
                        </div>
                    </div>


                </form>

            </div>
        </div>
        <div class="modal-footer">
            <!--<button class="btn btn-primary" type="button" ng-click="ok()">OK</button>-->
            <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
        </div>
    </div>
    
</script>