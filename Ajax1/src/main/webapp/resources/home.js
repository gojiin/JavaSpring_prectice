$(document).on('click', '.info', function(event){
	$.ajax({
        url:'/ajax1/info',
        success:function(data){
        	console.log(data);
            $('p').html(JSON.stringify(data));
        }
    });
});