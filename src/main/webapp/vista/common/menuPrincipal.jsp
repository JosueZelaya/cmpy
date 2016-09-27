<c:url var="rootURL" value="/"/>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="navCollapsed = !navCollapsed">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <button id="botonMenuCatagorias" class="navbar-toggle navbar-brand" ng-click="panelCategorias = !panelCategorias">
                <span class="sr-only">Toggle navigation</span>
                <span class="glyphicon glyphicon-list-alt"></span>
            </button>
            <a href="/cmpy" class="pull-left">
                <img src="${pageContext.request.contextPath}/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="$ComprameYa!">
            </a>
        </div>            
        <div class="navbar-right navbar-collapse collapse" uib-collapse="navCollapsed">

            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>                
                <li class="active"><a href="/cmpy/angular">Angular</a></li> 
                <li>                    
                    <a href="#" ng-click="open('lg')">Login <span class="glyphicon glyphicon-user"></a>                      
                </li>                           

            </ul>                    
        </div>
        <form class="navbar-form navbar-left" style="width: 100%" role="search">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Buscar Productos..." autofocus="autofocus">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">BuscarYa!</button>
                </span>
            </div>                    
        </form>
    </div>
</nav>
