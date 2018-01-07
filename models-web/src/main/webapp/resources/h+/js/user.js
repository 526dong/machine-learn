$(function(){
fnAccredit('#user_list'); //权限列表
fnListOper('.user_h2 a');//列表展开操作
fnListOper1('.user_list_h2 a');
fnAllotRole();//角色分配
fnAlterBtn(); //操作按钮列表
})
//列表展开操作
function fnListOper(obj){
	$(obj).click(function(){	
		if($(this).parent().siblings('ul').css('display')=='none'){
		
			$(this).parent().siblings('ul').slideDown(100).children('li');
			$(this).attr('class','');
		
		}else{
			$(this).parent().siblings('ul').slideUp(100);
			$(this).attr('class','a');
		}
	});
}
function fnListOper1(obj){
	$(obj).click(function(){	
		if($(this).parent().parent().siblings('ul').css('display')=='none'){
		
			$(this).parent().parent().siblings('ul').slideDown(100).children('li');
			$(this).removeClass('a');
		
		}else{
			$(this).parent().parent().siblings('ul').slideUp(100);
			$(this).addClass('a');
		}
		fnScroll();
	});
	$('.user_list_content div').hover(function(){
		$(this).attr('class','active');	
	},function(){
		var dataId =$(this).attr('data-id');
		if(dataId!=1){
			$(this).attr('class','');	
		}
	})
	$('.user_list_content div').click(function(){
		$('.user_list_content div').attr('data-id',0);
		$('.user_list_content div').attr('class','');
		$('.user_list_content div').find('em').attr('class','');
		$(this).attr('data-id',1);
		$(this).attr('class','active');
		$(this).find('em').attr('class','em_bg1');
	})
}
//权限列表
function fnAccredit(obj){
	
	$(obj).find('i').eq(0).click(function(){
		var aClass =$(obj).find('i').attr('class');
		if(aClass=='i_bg2'){
			$(obj).find('i').attr('class','');
		}else{
			$(obj).find('i').attr('class','i_bg2');
		}
		
	})
	$('.user_h2_btn i').click(function(){
		var aClass =$(this).attr('class');
		
		if(aClass=='i_bg2'){
			$(this).attr('class','');
			for(var i=0;i<$('.user_h2_btn').size();i++){
				
				if($('.user_h2_btn i').eq(i).attr('class')==''){
					$(obj).find('i').eq(0).attr('class','');	
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;
				}
				
			}
			$(this).parent().next().find('i').attr('class','');

		}else if(aClass==''){
			$(this).attr('class','i_bg2');
			for(var i=0;i<$('.user_h2_btn').size();i++){
				
				if($('.user_h2_btn i').eq(i).attr('class')=='i_bg2'){
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;	
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg2');	
				}
			}
			$(this).parent().next().find('i').attr('class','i_bg2');
			for(var i=1;i<$(obj).find('i').size();i++){
				
				if($(obj).find('i').eq(i).attr('class')!='i_bg2'){
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg2');
					
				}
			
			}
		}else if(aClass=='i_bg1'){
			$(this).attr('class','i_bg2');
			for(var i=0;i<$('.user_h2_btn').size();i++){
				
				if($('.user_h2_btn i').eq(i).attr('class')=='i_bg1'){
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg2');
					
				}
			}
			
			$(this).parent().next().find('i').attr('class','i_bg2');
			
		}
	})
	$('.user_h2_btn1 i').click(function(){
		var aClass =$(this).attr('class');
		var len =$(this).parent().parent().parent().find('.user_h2_btn1').size();
		var son =$(this).parent().parent().parent().find('.user_h2_btn1 i');
		if(aClass=='i_bg2'){
			$(this).attr('class','');
			for(var i=0;i<len;i++){
				if($(son).eq(i).attr('class')!='i_bg2'){
					$(this).parent().parent().parent().prev().find('i').attr('class','');
							
					
				}else{
					$(this).parent().parent().parent().prev().find('i').attr('class','i_bg1');
				break;	
				}
			}
			$(this).parent().next().find('i').attr('class','');
			$(obj).find('i').eq(0).attr('class','i_bg1');
				
		}else if(aClass==''){
			$(this).attr('class','i_bg2');
			for(var i=0;i<len;i++){
				if($(son).eq(i).attr('class')!='i_bg2'){
					$(obj).find('i').eq(0).attr('class','i_bg1');
					$(this).parent().parent().parent().prev().find('i').attr('class','i_bg1');	
					break;	
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg2');
					$(this).parent().parent().parent().prev().find('i').attr('class','i_bg2');	
				}
			}
			$(this).parent().next().find('i').attr('class','i_bg2');
			
			for(var i=1;i<$(obj).find('i').size();i++){
				
				if($(obj).find('i').eq(i).attr('class')!='i_bg2'){
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg2');
					
				}
			
			}
			
				
		}else if(aClass=='i_bg1'){
			$(this).attr('class','i_bg2');
			
			for(var i=0;i<$('.user_h2_btn1 i').size();i++){
				if($('.user_h2_btn1 i').eq(i).attr('class')!='' && $('.user_h2_btn1 i').eq(i).attr('class')!='i_bg1'){
					$(obj).find('i').eq(0).attr('class','i_bg2');
					
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;
					
				}
			}
			
			$(this).parent().next().find('i').attr('class','i_bg2');
			$(this).parent().parent().parent().prev().find('i').attr('class','i_bg2');
				
			
		}
	});
	$('.user_list3 i').click(function(){
		
		var aClass =$(this).attr('class');

		
		if(aClass=='i_bg2'){
			
			$(this).attr('class','');
			$(obj).find('i').eq(0).attr('class','i_bg1');
			
			
			for(var i=0;i<$(this).parent().parent().find('i').size();i++){

				if($(this).parent().parent().find('i').eq(i).attr('class')==''){
					
					$(this).parent().parent().prev().find('i').attr('class','');
					$(this).parent().parent().parent().parent().prev().find('i').attr('class','');
				}else{
					$(this).parent().parent().prev().find('i').attr('class','i_bg1');
					$(this).parent().parent().parent().parent().prev().find('i').attr('class','i_bg1');
				break;
					
				}
			}

		}else{
			$(this).attr('class','i_bg2');
			for(var i=0;i<$(this).parent().parent().find('i').size();i++){

				if($(this).parent().parent().find('i').eq(i).attr('class')==''){
					$(this).parent().parent().prev().find('i').attr('class','i_bg1');				
					break;	
				}else{
					$(this).parent().parent().prev().find('i').attr('class','i_bg2');
				}
			}
			var str =$(this).parent().parent().parent().parent().find('.user_list3 i');
			for(var i=0;i<$(this).parent().parent().parent().parent().find('.user_list3 i').size();i++){

				if($(str).eq(i).attr('class')!='' && $(str).eq(i).attr('class')!='i_bg1'){
					$(this).parent().parent().parent().parent().prev().find('i').attr('class','i_bg2');
				}else{
					$(this).parent().parent().parent().parent().prev().find('i').attr('class','i_bg1');
					break;	
				}
			}
			
			for(var i=0;i<$('.user_list1 i').size();i++){
				if($('.user_list1 i').eq(i).attr('class')!='' && $('.user_list1 i').eq(i).attr('class')!='i_bg1'){
					$(obj).find('i').eq(0).attr('class','i_bg2');
					
				}else{
					$(obj).find('i').eq(0).attr('class','i_bg1');
					break;	
				}
			}
			
		}
	});
}
//角色分配
function fnAllotRole(){
	
	$('#allot_role_par i').eq(0).click(function(){
		$(this).attr('class','i_bg2');
		$('#allot_role_list i').attr('class','i_bg2');
		$('#allot_role_par i').eq(1).attr('class','');
	});
	$('#allot_role_par i').eq(1).click(function(){
		$(this).attr('class','i_bg2');
		$('#allot_role_list i').attr('class','');
		$('#allot_role_par i').eq(0).attr('class','');	
	});
	$('#allot_role_list i').click(function(){
		
		if($(this).attr('class')=='i_bg2'){
			
			$(this).attr('class','');
			$('#allot_role_par i').eq(0).attr('class','');
			$('#allot_role_par i').eq(1).attr('class','i_bg2');
		}else{
			$(this).attr('class','i_bg2');
			for(var i=0;i<$('#allot_role_list i').size();i++){
				if($('#allot_role_list i').eq(i).attr('class')==''){
					
					$('#allot_role_par i').eq(1).attr('class','i_bg2');
					$('#allot_role_par i').eq(0).attr('class','');
					break;
				}else{
					$('#allot_role_par i').eq(0).attr('class','i_bg2');
					$('#allot_role_par i').eq(1).attr('class','');
				}
			}

		}
	})	
}
//操作按钮列表
function fnAlterBtn(){

	$('.user_nav_right li').hover(function(){
		$(this).find('span').show();
	},function(){
		$(this).find('span').hide();
	})


}

