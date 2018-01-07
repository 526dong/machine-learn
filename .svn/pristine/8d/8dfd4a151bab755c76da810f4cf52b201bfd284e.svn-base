$(function(){
fnLeftsideBar();//左侧导航
//fnMinHeight();//页面最小高度
fnComment();//点击填写备注
activeTr();//表格移入移出
//fnSelectx();//其他页面下拉菜单框
//fnSelect1();//进阶中心下拉菜单框
//fnAllCheck(); //全选操作
//fnSelectxInput(); //自定义select 输入匹配
//xxmore();//信息像是更多
fnAddMode('#add_btn','#mode_box .mode_box_row','#mode_box #add_btn');//模型分区间
fnAddMode('#add_btn1','#mode_box1 .mode_box_row','#mode_box1 #add_btn1');
fnDelMode('#add_btn','#mode_box .mode_box_row');
fnDelMode('#add_btn1','#mode_box1 .mode_box_row');
});
//左侧导航
function fnLeftsideBar(){
	$('.leftsideBar_h2').click(function(){
		if($(this).parent().find('.subNav_ul').css('display')=='none'){
			$('.subNav_ul').slideUp(200).children('li');
			$('.leftsideBar_li').removeClass('active');
			$(this).parent().find('.subNav_ul').slideDown(200).children('li');
			$(this).parent().addClass('active');
		
		}else{
			$(this).parent().find('.subNav_ul').slideUp(200).children('li');
			$(this).parent().removeClass('active');
		}
	});
	//最小高度
	//$('.leftsideBar').css('min-height',$(document).height()-$('.header_parent').height());
}
//页面最小高度
function fnMinHeight(){

	var iH =$(document).height()-$('.header_parent').height()-$('.right_title').height();
	$('.modules').css('min-height',iH);

}
//配置选择
function fnSwitchover(obj){
	var dataId =$(obj).attr('data-id');
	if(dataId==0){
		$(obj).addClass('abtn_bg');
		$(obj).parent().parent().find('i').addClass('active');
		$(obj).parent().parent().find('.scene_layer').show();
		$(obj).attr('data-id',1);
	}else{
		$(obj).removeClass('abtn_bg');
		$(obj).parent().parent().find('i').removeClass('active');
		$(obj).parent().parent().find('.scene_layer').hide();
		$(obj).attr('data-id',0);
	}	
	
}
//必填选填
function fnSwitchover1(obj){
	
	var dataId =$(obj).attr('data-id');
	if(dataId==0){
		$(obj).find('strong').addClass('active2');
		$(obj).find('span').addClass('active1');
		$(obj).find('i').animate({left:37},200,'linear');
		$(obj).find('i').css('background','#6de5e9')
		$(obj).attr('data-id',1);
	}else{
		$(obj).find('strong').removeClass('active2');
		$(obj).find('span').removeClass('active1');
		$(obj).find('i').animate({left:0},200,'linear')
		$(obj).find('i').css('background','#745df3')
		$(obj).attr('data-id',0);
	}

}
//全部选择全部取消
function fnAllSelect(obj){
	var s =$(obj).parent().parent().parent();
	$(obj).addClass('active');
	$(obj).parent().find('a:eq(1)').removeClass('active');
	$(s).find('.scene_list a').removeClass('abtn_bg');
	$(s).find('.scene_list a').attr('data-id',0);
	$(s).find('.scene_list i').removeClass('active');
	$(s).find('.scene_list .scene_layer').hide();
	$('.span_layer').hide();

}
function fnAllDelete(obj){
	var s =$(obj).parent().parent().parent();
	$(obj).addClass('active');
	$(obj).parent().find('a:eq(0)').removeClass('active');
	$(s).find('.scene_list a').addClass('abtn_bg');
	$(s).find('.scene_list a').attr('data-id',1);
	$(s).find('.scene_list i').addClass('active');
	$(s).find('.scene_list .scene_layer').show();
	$('.span_layer').show();
	
}
//备注弹窗
function fnPopup(obj){
	var dis =$(obj).parent().parent().find('.popup1').css('display');
	$(obj).parent().parent().find('.popup1 input').val('');
	$(obj).parent().parent().find('.popup2').hide();
	$('.popup1').hide();
	if(dis=='block'){
		$(obj).parent().parent().find('.popup1').hide();
	}else{
		$(obj).parent().parent().find('.popup1').show();
	}
}
function fnPopup1(obj){

	var h =$(obj).parent().parent().find('.popup2 p').html();	
	if(h!=''){
		$(obj).parent().parent().find('.popup1').hide();
		$(obj).parent().parent().find('.popup2').show();	
	}
}
function fnPopup2(obj){
	var h =$(obj).parent().parent().find('.popup2 p').html();
	
	$(obj).parent().parent().find('.popup2').hide();	
	
}
//回车填写备注
function fnComment(){
	
	$('.scene_input').on("keyup",function(ev){
		if(ev.keyCode == 13){
			var val =$(this).val();
			$(this).parent().hide();
			if(val!=''){
				$(this).parent().parent().find('.scene_list_l em').show();
				$(this).parent().parent().find('.popup2 p').html($(this).val());
			}
			$(this).val('');
		}
	})
}
//表格移入移出
function activeTr(){
	$('.audit_table tbody tr').hover(function(){
		$(this).addClass('active_tr')
	},function(){
        $(this).removeClass('active_tr')
	})
}
//其他页面下拉菜单框
function fnSelectx(){
    $('.select_nota').click(function(){
        return false;
    })
//    for(var i=0;i<$('.next_select').size();i++){
//        fnSelect1('#next1_select'+i,'#select1_box'+i);
//    }
    $("div[id^='next1_select']").each(function(index, element) {
		 var id = $(this).attr("id");
		 fnSelect1('#'+id,'#select1_box'+id.substring(12));
	 });
    function fnSelect1(oDiv,oLi){
        $(oDiv).parent().on("click",function(ev){
        	$('.input_box').hide()
			$('.input_box').parent().removeClass('down_input_box_click');
            var dis =$(oLi).css('display');
            if($(oLi).find('p').size()>5){
                $(oLi).height(150);
				$(oLi).css('overflow-y','auto');
            }
            $('.select_box').hide();
			$('.select_box1').hide();
            $('.down_select_box').removeClass('down_select_click')
            $(this).addClass('down_select_click')
            if(dis=='block'){
                $(oLi).hide();
                $('.down_select_box').removeClass('down_select_click')
            }else{
                $(oLi).show();
            }
			$(oLi).on("mouseover","p",function(){
                $(oLi).find('p').css('color','#3a3f52');
                $(this).css('color','#8348ff');
            });
            ev.stopPropagation();

        })
        $(oLi).on("click","p",function(){
            $(oDiv).find('span').html($(this).html());
            $(oDiv).find('span').attr('data-id',$(this).attr('data-id'));
            $('.down_select_box').removeClass('down_select_click')
            $(oDiv).parent().find('strong').css('display','none');
            $(oDiv).find('span').addClass('select_span_click')
        });
        $(document).click(function(){
			$(oLi).hide();
            $('.down_select_box').removeClass('down_select_click')
        });


    }
}
//进阶中心下拉菜单框
function fnSelect1(){
//	for(var i=0;i<$('.next_select1').size();i++){
//		fnSelect2('#next_select'+i,'#select_box'+i);
//	}
	$("div[id^='next_select']").each(function(index, element) {
		 var id = $(this).attr("id");
		 fnSelect2('#'+id,'#select_box'+id.substring(11));
	 });
	function fnSelect2(oDiv,oLi){
		$(oDiv).on("click",function(ev){
			var dis =$(oLi).css('display');
			var oDiv1 =parseInt($(oDiv).css('width'));
			
			if($(oLi).find('p').size()>5){
				$(oLi).height(150);
				$(oLi).css('overflow-y','auto');
			}
			$('.select_box').hide();
			$('.select_box1').hide();
			$('.next_select1').removeClass('next_active');
			$('.next_select1').removeClass('next_active1');
	
			if(dis=='block'){
				$(oLi).hide();
				if(oDiv1<200){
					$(oDiv).removeClass('next_active');
				}else{
					$(oDiv).removeClass('next_active1');
				}
			}else{
				$(oLi).show();
				if(oDiv1<200){
					$(oDiv).addClass('next_active');
				}else{
					$(oDiv).addClass('next_active1');
				}
				
			}
			$(oLi).on("mouseover","p",function(){
                $(oLi).find('p').css('color','#3a3f52');
                $(this).css('color','#8348ff');
            });
			ev.stopPropagation();
	
		})
		$(oLi).on("click","p",function(){
			$(oDiv).find('i').html($(this).html());
			$(oDiv).find('i').attr('data-id',$(this).attr('data-id'));
			$(oLi).hide();
			$(oDiv).removeClass('next_active');
			$(oDiv).removeClass('next_active1');
			
		});		
		$(document).click(function(){
			
			$(oLi).hide();
			$('.next_select1').removeClass('next_active');
			$('.next_select1').removeClass('next_active1');
			
		});
		
	
	}
}
//全选操作
function fnAllCheck(){
    $('#audit_check').click(function(){
        var aClass =$(this).attr('class');
        if(aClass=='null'){
            $(this).attr('class','active');
            $('#tbody_tr').find('strong').attr('class','active');
        }else{
            $(this).attr('class','null');
            $('#tbody_tr').find('strong').attr('class','null');
        }
    });
    $('#tbody_tr').on('click','strong',function(){
        if($(this).attr('class')=='null'){
            $(this).attr('class','active');
            for(var i=0;i<$('#tbody_tr strong').size();i++){
                if($('#tbody_tr strong').eq(i).attr('class')=='active'){
                    $('#audit_check').attr('class','active');
                }else{
                    $('#audit_check').attr('class','null');
                    break;
                }
            }
        }else{
            $(this).attr('class','null');
            for(var i=0;i<$('#tbody_tr strong').size();i++){
                if($('#tbody_tr strong').eq(i).attr('class')=='null'){
                    $('#audit_check').attr('class','null');
                }
            }
        }
    });
}
//自定义select 输入匹配
function fnSelectxInput(){
    for(var i=0;i<$('.next_input').size();i++){
        fninput('#next_input'+i,'#input_box'+i);
    }
    function fninput(oDiv,oLi){
		var This_val;
        $(oDiv).parent().on("click",function(ev){
			This_val =$(this).find('input').val();
            var dis =$(oLi).css('display');
            if($(oLi).find('li').size()>8){
                $(oLi).height(150);
            }
            $('.input_box').hide();
            $('.down_select_box').removeClass('down_input_box_click')
            $(this).addClass('down_input_box_click')
            if(dis=='block'){
                $(oLi).hide();
                $('.down_input_box').removeClass('down_input_box_click')
            }else{
                $(oLi).show();
            }

            $(oLi).on("mouseover","li",function(){
                $(oLi).find('li').attr('class','');
                $(this).attr('class','curr');
            });
            ev.stopPropagation();
        });

        $('.next_input input').keydown(function(){

            if($(this).val()==This_val){
                $(this).val('');
            }
        });
        $(oLi).on("click","li",function(){
            $(oDiv).find('input').val($(this).text())
            $(oDiv).find('input').attr('data-id',$(this).attr('data-id'));
            $(oDiv).parent().removeClass('down_input_box_click')
        });
        $(document).click(function(){
            $(oLi).hide();
            $(oDiv).parent().removeClass('down_input_box_click');
			if($(oDiv).find('input').val()==''){
				$(oDiv).find('input').val(This_val);
			}
			
        });
    }
}

//信息像是更多
function xxmore(){
		var a=parseInt($('.xxheight1').outerHeight()+$('.xxheight2').outerHeight()+$('.xxheight0').outerHeight()+10);
		$('.jcx_jinjianxx_height').height(a);
    	$('.jcx_jinjianxx_more a').click(function(){
            if($('.jcx_jinjianxx_height').attr('date_ad')==0){
                $('.jcx_jinjianxx_height').css('height','auto');
                $(this).html('收起进件信息 >');
                $('.jcx_jinjianxx_height').attr('date_ad','1')
            }else{
                $('.jcx_jinjianxx_height').css('height',a);
                $(this).html('查看完整进件信息 >');
                $('.jcx_jinjianxx_height').attr('date_ad','0')
            }
        }
    )
}
//权限管理
function role1(obj){
	var dataId =$(obj).attr('data-id');
	var p =$(obj).parent().parent();
	if(dataId==0){
		$(obj).attr('data-id',1);
		$(obj).attr('class','role2');
		$(p).find('dt a').attr('class','role5');
		$(p).find('dd strong').attr('class','active');
		$(p).find('dd a').animate({left:0},200,'linear');
		$(p).find('dt a').attr('data-id',1);
		$(p).find('dd a').attr('data-id',1);
	}else{
		$(obj).attr('data-id',0);
		$(obj).attr('class','role1');
		$(p).find('dt a').attr('class','role4');
		$(p).find('dd strong').attr('class','');
		$(p).find('dd a').animate({left:18},200,'linear');
		$(p).find('dt a').attr('data-id',0);
		$(p).find('dd a').attr('data-id',0);
	}
	

}
function role2(obj){
	
	var dataId =$(obj).attr('data-id');
	var p =$(obj).parent().parent();
	var a =$(p).parent().find('dt a');
	if(dataId==0){
		$(obj).attr('data-id',1);
		$(obj).attr('class','role5');
		$(p).find('dd strong').attr('class','active');
		$(p).find('dd a').animate({left:0},200,'linear');
		$(p).find('dd a').attr('data-id',1);
		for(var i=0;i<$(a).size();i++){
			if($(a).eq(i).attr('data-id')==1){
				$(p).parent().find('h2 a').attr('class','role2');
				$(p).parent().find('h2 a').attr('data-id',1);
			}else if($(a).eq(i).attr('data-id')==0){
				$(p).parent().find('h2 a').attr('class','role3');
				$(p).parent().find('h2 a').attr('data-id',1);
				break;
				
			}
			
		}
	}else if(dataId==1){
		$(obj).attr('data-id',0);
		$(obj).attr('class','role4');
		$(p).find('dd strong').attr('class','');
		$(p).find('dd a').animate({left:18},200,'linear');
		$(p).find('dd a').attr('data-id',0);
		for(var i=0;i<$(a).size();i++){
			if($(a).eq(i).attr('data-id')==1){
				$(p).parent().find('h2 a').attr('class','role3');
				$(p).parent().find('h2 a').attr('data-id',1);
				break;
			}else if($(a).eq(i).attr('data-id')==0){
				$(p).parent().find('h2 a').attr('class','role1');
				$(p).parent().find('h2 a').attr('data-id',0);
				
				
			}
			
		}
	}	
}
function role3(obj){
	var dataId =$(obj).attr('data-id');
	var p =$(obj).parent().parent();
	var p1 =$(obj).parent().parent().parent().parent();
	var a =$(p).parent().find('dt a');
	var a1 =$(p).parent().find('dd a');
	var a2 =$(p1).find('dd a');
	if(dataId==0){
		$(obj).attr('data-id',1);
		$(obj).animate({left:0},200,'linear');
		$(obj).parent().attr('class','active');
		for(var i=0;i<$(a1).size();i++){
			if($(a1).eq(i).attr('data-id')==1){
				$(a).attr('class','role5');
				$(a).attr('data-id',1);
			}else if($(a1).eq(i).attr('data-id')==0){
				$(a).attr('class','role6');
				$(a).attr('data-id',1);
				break;	
			}
			
		}
		for(var i=0;i<$(a2).size();i++){
			if($(a2).eq(i).attr('data-id')==1){
				$(p1).find('h2 a').attr('class','role2');
				$(p1).find('h2 a').attr('data-id',1);
			}else if($(a2).eq(i).attr('data-id')==0){
				$(p1).find('h2 a').attr('class','role3');
				$(p1).find('h2 a').attr('data-id',1);
				break;
					
			}	
		}
		for(var i=0;i<$(p1).find('dt a').size();i++){
			if($(p1).find('dt a').eq(i).attr('class')=='role5'){
				$(p1).find('h2 a').attr('class','role2');
				$(p1).find('h2 a').attr('data-id',1);
			}else{
				$(p1).find('h2 a').attr('class','role3');
				$(p1).find('h2 a').attr('data-id',1);
				break;
			}
		}
		
	}else{
		$(obj).attr('data-id',0);
		$(obj).animate({left:18},200,'linear');
		$(obj).parent().attr('class','');
		for(var i=0;i<$(a1).size();i++){
			if($(a1).eq(i).attr('data-id')==1){
				$(a).attr('class','role6');
				$(a).attr('data-id',1);
				break;
			}else if($(a1).eq(i).attr('data-id')==0){
				$(a).attr('class','role4');
				$(a).attr('data-id',0);
					
			}
			
		}
		for(var i=0;i<$(a2).size();i++){
			if($(a2).eq(i).attr('data-id')==1){
				$(p1).find('h2 a').attr('class','role3');
				$(p1).find('h2 a').attr('data-id',1);
				break;
			}else if($(a2).eq(i).attr('data-id')==0){
				$(p1).find('h2 a').attr('class','role1');
				$(p1).find('h2 a').attr('data-id',0);
				
					
			}	
		}
		for(var i=0;i<$(p1).find('dt a').size();i++){
			if($(p1).find('dt a').eq(i).attr('class')=='role4'){
				$(p1).find('h2 a').attr('class','role1');
				$(p1).find('h2 a').attr('data-id',0);
			}else{
				$(p1).find('h2 a').attr('class','role3');
				$(p1).find('h2 a').attr('data-id',1);
				break;
				
			}
		}
	}
	
}
//模型分区间
function fnAddMode(obj,obj1,obj2){

	$(obj).find('div:eq(0)').click(function(){
		
		var l =$(obj1).size();
		var html ='<div class="mode_box_row"><input name="" class="mode_box_num" id="" value="" type="text">至<input name="" class="mode_box_num mode_box_num2" id="" value="" type="text"><span class="mode_box_xishu">授信系数：</span><input name="" class="mode_box_pre" id="" value="80" type="text"><span class="mode_box_baifen">%</span></div>';
		
		if(l<=996){
			if(l==0){
				$(obj2).before(html);
			}else{
				$(obj1).last().before(html);
			}
			
		}
	});
}
function fnDelMode(obj,obj1){

	$(obj).find('div:eq(1)').click(function(){
		
		var l =$(obj1).size();
		if(l!=0){
			$(obj1).last().prev().remove();
		}
	});
}
//贷中监控新增
function fnCheck(obj){
	var dataId =$(obj).attr('data-id');
	if(dataId==0){
		$(obj).addClass('div_s_curr');
		$(obj).attr('data-id',1);
	}else{
		$(obj).removeClass('div_s_curr');
		$(obj).attr('data-id',0);
	}
}

//全局ajax session 过期 跳转登陆	
jQuery(function($){  
    // 备份jquery的ajax方法    
	$.ajaxSetup({
		dataFilter : function(response) {
			if (response.indexOf('反欺诈云平台登录') !== -1) {
				//如果返回的文本包含"登陆页面"，就跳转到登陆页面  
				window.location.href = "${ctx}/index";
				//一定要返回一个字符串不能不返回或者不给返回值，否则会进入success方法  
				return "";
			} else {
				//如果没有超时直接返回  
				return response;
			}
		}
	});
 
});
//ajax 遮罩层
function loadingblockUI(ctx){
	if(typeof(ctx) == "undefined"){
		ctx = "./";
	}
	$.blockUI({
   	 	message: '<img src="'+ctx+'/resources/image/cloud-bg.gif" alt="" style="width:100px;height:100px;" /><p style="z-index:1011; text-align:center;color:#666;margin-top:10px;">正在努力处理中...</p>',
		css: { backgroundColor: 'none', border:'none', cursor:'wait' },
	 	overlayCSS:  { backgroundColor: '#ccc', opacity: 0.5, cursor: 'wait'}
	 });  
}



