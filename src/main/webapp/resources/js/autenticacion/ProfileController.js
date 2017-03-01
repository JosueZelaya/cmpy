autenticacion.controller('profileController', function ($scope, profileService, $state, toastr) {
    
    $scope.nombre = "";
    $scope.apellido = "";
    $scope.telefono = "";
    $scope.sexo = "";
    $scope.dia = "0";
    $scope.mes = "0";
    $scope.anio = "0";
    $scope.anios = [];
    
    $scope.estado_nombre = true;
    $scope.estado_apellido = true;
    $scope.estado_telefono = true;
    
    $scope.etiqueta_nombre = "Editar";
    $scope.etiqueta_apellido = "Editar";
    $scope.etiqueta_telefono = "Editar";
    
    
    $scope.nombre_original = "";
    $scope.apellido_original = "";
    $scope.telefono_original = "";
    
    $scope.val_datos = true;
    $scope.error = "";
    
    $scope.resetNombre=function(){
        if($scope.estado_nombre)
        {
            $scope.nombre=$scope.nombre_original; 
            $scope.etiqueta_nombre = "Editar";
        }
        else
        {
            $scope.nombre = "";
            $scope.etiqueta_nombre = "Deshacer";
        }
    };
    $scope.resetApellido=function(){
        if($scope.estado_apellido)
        {
            $scope.apellido=$scope.apellido_original;
            $scope.etiqueta_apellido="Editar"; 
        }
        else
        {
            $scope.apellido="";
            $scope.etiqueta_apellido="Deshacer"; 
        }
    };
    $scope.resetTelefono=function(){
        if($scope.estado_telefono){
            $scope.telefono=$scope.telefono_original; 
            $scope.etiqueta_telefono="Editar";
        }
        else
        {
          $scope.telefono="";  
          $scope.etiqueta_telefono="Deshacer"; 
            
        }
    };    
    
    
    var valFechaNacimiento = function (dia,mes,anio) {

        var fecha_val_dia = new Date(anio,mes,0);
        var fecha_actual = new Date();

        var dia_diferencia = fecha_val_dia.getDate() - dia;
        var anio_diferencia = fecha_actual.getFullYear() - anio;

        var val_dia = dia_diferencia >= 0 && dia > 0 ? true : false;
        var val_mes = mes > 0 && mes <= 12 ? true : false;
        var val_anio = anio_diferencia >= 10 && anio_diferencia <= 100 ? true : false;


        return val_dia && val_mes && val_anio;        
        

    };
    
    var valTelefono = function(tel){
        
        var t = tel.length;
        var i = 0;
        var val = true;
        for(i=0;i<t;i++){
            if(!(!isNaN(parseFloat(tel[i])) && isFinite(tel[i]))) 
            val = false;
        }
        
        return val;
        
    };
    
    
    $scope.getProfile = function(){
        profileService.getProfile()
        .success(function (response)
        {
            console.log(response);
            
            if(response.persona.nombre !== null)
                $scope.nombre = response.persona.nombre;
            if(response.persona.apellido !== null)
                $scope.apellido = response.persona.apellido;
            if(response.persona.telefono !== null)
                $scope.telefono = response.persona.telefono;
            if(response.persona.genero !== null)
                $scope.sexo = response.persona.genero ? "1" : "0";
            if(response.persona.fechaNacimiento !== null)
            {
               var partes = response.persona.fechaNacimiento.split("-");
                
               $scope.dia = String(parseInt(partes[2]));
               $scope.mes = String(parseInt(partes[1]));
               $scope.anio = partes[0];
                
            }
            
            $scope.nombre_original = $scope.nombre;
            $scope.apellido_original = $scope.apellido;
            $scope.telefono_original = $scope.telefono;
            
             
        });       
        
        
    };
    
    $scope.updateProfile = function(){
        
        var tmpnombre = $scope.nombre;
        var tmpapellido = $scope.apellido;
        var tmptelefono = $scope.telefono;
        var tmpsexo = $scope.sexo === "1" ? true : false;
        var tmpdia = parseInt($scope.dia);
        var tmpmes = parseInt($scope.mes);
        var tmpanio = parseInt($scope.anio);
        
        var val_fecha = valFechaNacimiento(tmpdia,tmpmes,tmpanio);
        var val_telefono = valTelefono(tmptelefono);
        
        if(val_fecha && val_telefono)
        {
            
            profileService.updateProfile
            (
                tmpnombre,
                tmpapellido,
                tmptelefono,
                tmpsexo,
                tmpdia,
                tmpmes,
                tmpanio
            );
    
            $scope.cancel();
            
        }
        else{
            $scope.val_datos=false;
            if(!val_fecha)
            $scope.error = "Fecha Invalida";
            if(!val_telefono)
            $scope.error = "Telefono Invalido";
        }
                
 
    };
    
    
    
    var generarAnios = function(){
        var fecha_actual = new Date();
        var anio_actual = fecha_actual.getFullYear();
        var anio_inicial = anio_actual - 100;
        var anio_final = anio_actual - 10;
        var i;
        for(i=anio_inicial;i<=anio_final;i++){
            $scope.anios.push(i);            
        }
        
    };
    
    var init = function(){
        generarAnios();
        $scope.getProfile();
    };
    
    init();


});


