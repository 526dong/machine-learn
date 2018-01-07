// 判断时候在Iframe框架内,在则刷新父页面
if (self != top) {
    parent.location.reload(true);
    if (!!(window.attachEvent && !window.opera)) {
        document.execCommand("stop");
    } else {
        window.stop();
    }
}

$(function() {
	
	// 回车登录
	$(document).keyup(function(event){  
		if(event.keyCode ==13){  
			$("#loginSubmit").click();  
		}  
	});

	// 登录系统
	$("#loginSubmit").on('click',function(){
    	var username=$("#username").val();
    	var password=$("#password").val();
    	$.ajax({
      		url :"${ctx}/login",
			data : {
				"username":username,
				"password":password
				},
			type : "POST",
			dataType : 'json',
			success : function(data) {
				if ('0000'==data.code) {
				 window.location.href = "${ctx}/"+data.resultURL;
				}else{
					$("#error").html(data.msg);
				}
			}
		});
	});
});
