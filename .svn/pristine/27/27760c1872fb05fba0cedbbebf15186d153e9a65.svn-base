$(function(){
	//动态获取屏幕高度
	function layer(){
		$('.screen').css('width',$(document).width());
		$('.screen').css('height',$(document).height());
		$(window).resize(function(){
			$('.screen').css('width',$(document).width());
			$('.screen').css('height',$(document).height());
		})
	}
	layer();
	//新增用户弹框
//	$('.add_new').click(function(){
//		$('.add_user').css('display','block');
//		$('.screen').css('display','block');
//		layer();
//	})
	$('.cha').click(function(){
        $('.screen').css('z-index','999');
		$('.rpass_all').hide();
		$('.add_edit_user').hide();
		$('.lock_none').hide();
		$('.deblocking').css('display','none');
		$('.del_frost').css('display','none');
		$('.delete_chell').css('display','none');
		$('.delete').css('display','none');
		$('.add_user').css('display','none');
		$('.show_detail_one').css('display','none');
		$('.screen').css('display','none');
		$('.pop_policy').hide();
        $('.pop_registration').hide();
        $('.pop_registration_true').hide();
        $('.pop_uploading').hide();
        $('.pop_first_load').hide();
        $('.pop_del_policy').hide();
        $('.pop_del').hide();
        $('.pop_policy_show').hide();
        //贷后管理查看详情
        $('.pop_first_detail').hide();
     //   贷中监控新增
		$('.pop_first_add').hide();
	//	贷中监控设置
		$('.pop_first_set').hide();
	})
	$('.show_all_one').click(function(){
		$('.show_detail_one').css('display','block');
		$('.screen').css('display','block');
	})
	$('.cancel').click(function(){
		$('.delete').show();
		$('.screen').show();
	})
	$('.frost').click(function(){
		$('.delete').show();
		$('.screen').show();
	});
    $('.redact_user').click(function(){
        $('.add_edit_user').show();
        $('.screen').show();
    });
	$('.redact').click(function(){
		$('.add_edit').show();
		$('.screen').show();
	});
	$('.rpass').click(function(){
		$('.rpass_all').show();
		$('.screen').show();
	});
	$('.add_new').click(function(){
    $('.add_edit').show();
    $('.screen').show();
	});
	$('.cha').click(function(){
		$('.add_edit').hide();
		$('.screen').hide();
		$('.del').hide();
	})
	$('.cancel').click(function(){
		$('.screen').show();
		$('.del').show();
	});
	//决策配置弹框js
    $('.decision_detail').click(function(){
        $('.screen').show();
        $('.pop_policy_show').show();
    });
	$('.decision_revision').click(function(){
		$('.screen').show();
		$('.pop_policy').show();
	});
	$('.save_true_false').click(function(){
		$('.pop_del_policy').show();
		$('.pop_registration_true').show();
		$('.screen').css('z-index','1001');
	})
    $('.decision_del').click(function(){
        $('.screen').show();
        $('.pop_del').show();
    });
    //贷后管理
    $('.registration_up').click(function(){
        $('.screen').show();
        $('.pop_uploading').show();
    });
    $('.later_registration').click(function(){
        $('.screen').show();
        $('.pop_registration').show();
    });
    //查看详情
    $('.pagination_show_detail').click(function(){
        $('.screen').show();
        $('.pop_first_detail').show();
    });
    //查看上次批量上传结果
//	$('.eary_show').click(function(){
//        $('.pop_first_load').show();
//        $('.screen').show();
//	})
	//贷中监控新增
//	$('.add_new_see').click(function(){
//        $('.pop_first_add').show();
//        $('.screen').show();
//	})
    //贷中监控设置
//    $('.pop_set_center').click(function(){
//        $('.pop_first_set').show();
//        $('.screen').show();
//    })
    //贷中监控查看详情
//	$('.show_detail_center').click(function(){
//        $('.pop_first_detail').show();
//        $('.screen').show();
//	})
    //自定义下拉框
    $('.all_small').each(function(){
        $(this).click(function(e){
            if( $(this).find('ul.chose_all').css('display') == 'none'){
                $(this).find('ul.chose_all').show();
                $(this).find('div.select_jiao').addClass('select_jiao_show')

            }else{
                $(this).find('ul.chose_all').hide();
                $(this).find('div.select_jiao').removeClass('select_jiao_show')
            }
            e.stopPropagation();
            $(document).on('click',function ()
            {
                $('ul.chose_all').hide();
                $('div.select_jiao').removeClass('select_jiao_show');
            });
			$(this).find('li').on('mouseover',function(){
            	$('.chose_all li').css('color','#3a3f52');
                $(this).css('color','#8348ff');
            })
            $(this).find('li').click(function(){
            	$(this).parent().parent().find('p.chose_in').text($(this).html().replace(/&nbsp;/ig, ""));
            	$(this).parent().parent().find('p.chose_in').attr("data-id",$(this).attr("data-id"));
            })
        });

    })
//	决策配置中修改弹窗
	$('.weight').click(function(){
		$(this).addClass('three_check_active')
        $('.strategy,.scale').removeClass('three_check_active')
        if($(this).parents('.form_x_switch').size()>0){
        	$(this).parents('.form_x_switch').find('.weight_all').show();
    		$(this).parents('.form_x_switch').find('.strategy_all,.scale_all').hide();
        }else{
        	$('.weight_all').show();
    		$('.strategy_all,.scale_all').hide();
        }
		
	});
    $('.strategy').click(function(){
        $(this).addClass('three_check_active')
		$('.weight,.scale').removeClass('three_check_active')
	    if($(this).parents('.form_x_switch').size()>0){
	    	$(this).parents('.form_x_switch').find('.weight_all,.scale_all').hide();
	        $(this).parents('.form_x_switch').find('.strategy_all').show();
        }else{
        	$('.weight_all,.scale_all').hide();
            $('.strategy_all').show();
        }
        
    });
    $('.scale').click(function(){
        $(this).addClass('three_check_active')
        $('.weight,.strategy').removeClass('three_check_active')
        if($(this).parents('.form_x_switch').size()>0){
        	 $(this).parents('.form_x_switch').find('.weight_all,.strategy_all').hide();
             $(this).parents('.form_x_switch').find('.scale_all').show();;
        }else{
        	 $('.weight_all,.strategy_all').hide();
             $('.scale_all').show();
        }
       
    });
    $('.control_num').each(function(){
    	$(this).click(function(){
    		if($(this).hasClass('control_active')){
    			$(this).removeClass('control_active')
			}else{
                $(this).addClass('control_active')
			}
		})
	})
    
     //授信配置
    $(".cover").css("height",$(document).height());
	$(".cover2").css("height",$(document).height());
	//新增授信配置单选框
	$(".sx_method input").on("click",function(){
		if($(this).parent().find("span").html() == "信用分"){
			$("input[name=cType]:eq(0)").val("1"); 
			$("input[name=cType]:eq(1)").val("1");
		}else{
			$("input[name=cType]:eq(0)").val("2"); 
			$("input[name=cType]:eq(1)").val("2");
		}
		
		$(this).parents("from").find(".radio_bg").css("background","url(../resources/image/nochecked.png)");
		$(this).parents("from").find(".method_tit").css("color","#B1B1C0");
		if($(this).attr("checked")=="checked"){
			$(this).parents(".method_box").children(".radio_bg").css("background","url(../resources/image/checked.png)");
			$(this).parents(".method_box").children(".method_tit").css("color","#7A53FC");
		}
		console.log($(this).attr("data-id"));
		if($(this).attr("data-id")=="1"){			
			if($(this).parents(".form_x_switch").size()>0){
				$(this).parents(".form_x_switch").find(".tab_box1").css("display","block");
				$(this).parents(".form_x_switch").find(".tab_box2").css("display","none");
			}else{
				$(this).parents(".add_sxUser_box").find(".tab_box1").css("display","block");
				$(this).parents(".add_sxUser_box").find(".tab_box2").css("display","none");
			}
			if($(this).parents(".add_sxUser")){
				$(this).parents(".add_sxUser").css("height","650px");
			}
			if($(this).parents(".add_sxUser1")){
				$(this).parents(".add_sxUser1").css("height","650px");
			}
		}else{
			if($(this).parents(".form_x_switch").size()>0){
				$(this).parents(".form_x_switch").find(".tab_box2").css("display","block");		
				$(this).parents(".form_x_switch").find(".tab_box1").css("display","none");
			}else{
				$(this).parents(".add_sxUser_box").find(".tab_box2").css("display","block")
				$(this).parents(".add_sxUser_box").find(".tab_box1").css("display","none")
			}
			if($(this).parents(".add_sxUser")){
				$(this).parents(".add_sxUser").css("height","503px");
			}
			if($(this).parents(".add_sxUser1")){
				$(this).parents(".add_sxUser1").css("height","503px");
			}
		}
	});
	//查看详情
	/*$(".detail_btn").on("click",function(){
		$(".sxpz_detail").css("display","block");
		$(".cover").css("display","block");
	});
	$(".sxpz_detail_close").on("click",function(){
		$(".sxpz_detail").css("display","none");
		$(".cover").css("display","none");
	});
	$(".close_box").on("click",function(){
		$(".sxpz_detail").css("display","none");
		$(".cover").css("display","none");
		$(".add_sxUser1").css("display","none");
		$(".add_sxUser").css("display","none");
	});
	//新增授信配置
	$(".jctop_box_xz").children("a").on("click",function(){
		$(".add_sxUser").css("display","block");
		$(".cover").css("display","block");
	});
	$(".add_sxUser_close").on("click",function(){
		$(".add_sxUser").css("display","none");
		$(".add_sxUser1").css("display","none");
		$(".cover").css("display","none");
	});
	//修改
	$(".detail_btn").next().on("click",function(){
		$(".add_sxUser1").css("display","block");
		$(".cover").css("display","block");
	});
	//确认修改
	$(".close_box1").on("click",function(){
		$(".xiugai_tan").css("display","none");
		$(".cover2").css("display","none")
	});
	$(".xiugai_tan_no").on("click",function(){
		$(".xiugai_tan").css("display","none");
		$(".cover2").css("display","none")
	});
	//删除
	$(".detail_btn").next().next().on("click",function(){
		$(".del_tan").css("display","block");
		$(".cover").css("display","block");
	});
	$(".del_close").on("click",function(){
		$(".del_tan").css("display","none");
		$(".cover").css("display","none");
	});*/

});
//按模型分修改
function mode_xiugai(){
	$(".xiugai_tan").css("display","block");
	$(".cover2").css("display","block")
}