<c:url var="rootURL" value="/"/>
<nav class="navbar navbar-default navbar-static-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <button id="botonMenuCatagorias" class="navbar-toggle navbar-brand" data-toggle="collapse" data-target=".panelCategorias">                
           <span class="glyphicon glyphicon-list-alt"></span>
      </button>
      <a href="/cmpy" class="pull-left">
          <img src="${pageContext.request.contextPath}/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="$ComprameYa!">
      </a>
    </div>
    <div id="navbar" class="navbar-right navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>                
        <c:choose>
            <c:when test="${username=='anonymousUser' || username==''}">
                <li>                    
                    <a href="#" data-toggle="modal" data-target="#loginModal">Login <span class="glyphicon glyphicon-user"></a>                      
                </li>                           
            </c:when>    
            <c:otherwise>    
                <li class="dropdown">                            
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Bienvenido <c:out value='${username}'/> <span class="glyphicon glyphicon-user"></span><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Cambiar Clave</a></li>
                        <li><a href="#">Edita Perfil</a></li>
                        <li><a href="#">Configuracion</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Nav header</li>                                     
                        <li><a href="${rootURL}logout">Salir <span class="glyphicon glyphicon-off" aria-hidden="true"></span></a></li>                                
                    </ul>
                </li>                        
            </c:otherwise>
        </c:choose>    

      </ul>
    </div>
    <form class="navbar-form navbar-left" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Buscar Productos...">
            <span class="input-group-btn">
                <button class="btn btn-default" type="submit">BuscarYa!</button>
            </span>
        </div>
        <!--<button type="submit" class="btn btn-default">Submit</button>-->                
      </form>
  </div>
</nav>