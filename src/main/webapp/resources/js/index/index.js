/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){    
    
    $('#publicacionesPager').bootpag({
        total: $('#publicacionesPager').attr("totalPages"),        
        maxVisible: 10
    }).on("page", function(event, num){
        var tipo = $('#publicacionesPager').attr("tipo");
        $.ajax({
            url: '/cmpy/publicacion/getAnuncios/'+tipo+'/'+num,
            success: function(respuesta){
                $('#publicacionesProductos').html(respuesta);                    
            }
        });
        $(this).bootpag({total: $('#publicacionesPager').attr("totalPages"), maxVisible: 10});
    });   
   
});

