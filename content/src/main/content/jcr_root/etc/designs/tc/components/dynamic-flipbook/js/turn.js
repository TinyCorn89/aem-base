$(window).ready(function() {
    $("#magazine").turn();
    
});


$(window).bind('keydown', function(e){
    
    if (e.keyCode==37)
        $('#magazine').turn('previous');
    else if (e.keyCode==39)
        $('#magazine').turn('next');
    
});