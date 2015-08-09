/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){    
    
    $('#publicacionesPager').bootpag({
        total: $('#publicacionesPager').attr("totalPages")
    }).on("page", function(event, num){
//        $(".content").html("Page " + num); // or some ajax content loading...

        // ... after content load -> change total to 10
        $(this).bootpag({total: 10, maxVisible: 10});

    });   
   
});

