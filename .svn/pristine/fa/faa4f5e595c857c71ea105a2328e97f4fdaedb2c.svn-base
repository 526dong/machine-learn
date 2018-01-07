/*
 	$$tableDrag({
		el : ,	//元素
		legend : ['收入账','本金账'],	//单个直接写‘字符串’	多个 写成数组
		data : data
	})
	$(元素).tableDrag({
		data : data
	})
 * 	
 * 
 * */
(function(){
	function Drag(options){
		this.isMove = false;	//是否可移动
		this.legend = [];	//标题
		this.series = [];	//数据
		this.el = {
			mirror : null,
			tagsAll : null,
			tags : null,
			tagsBox : null
		}
		this.pos = {
			left : 0,
			top : 0
		}
		this.setOption(options);
		if(typeof this.opts.legend ==  'string'){
			this.legend.push(this.opts.legend);
		}else{
			this.legend = this.opts.legend;
		}
		this.init();
	}
	
	// 设置默认值
	Drag.prototype.setOption = function(options){
		this.opts = {	//默认值
			el : null,	//设置触发对象（必填）
			drag : true,	//是否可以拖拽
			legend : [],	//只能是字符串 和 数组类型
			data : {}
		};
		$.extend(true,this.opts, options || {});
	}
	//初始化
	Drag.prototype.init = function(){
		var This = this;
		this.setSeriesData(this.opts.data.list);
		this.createBox();
		this.createTags();
		
		//设置  添加  删除   事件  
		$(This.opts.el).find('.table-drag-con').each(function(index,ele){
			This.setBtn($(ele));
			
			$(ele).on('click','.table-drag-add',function(){
				This.addItem($(this));
				This.setBtn($(ele));
			})
			
			$(ele).on('click','.table-drag-del',function(){
//				if(confirm('确定要删除吗？')){
//					This.delItem($(this));
//					This.setBtn($(ele));
//				}
				var id = $(this).parents('tr').attr('data-id');
				var layerMask = $('<div class="layer-mask" id="layer-mask" data-id="'+id+'"></div>');
				var layerDialog = $('<div class="layer-dialog" id="layer-dialog">\
					<div class="layer-body">\
						<div class="layer-content" style="height: 24px;">确定要删除吗?</div>\
						<div class="layer-btn-group">\
							<a href="javascript:;" class="layer-btn layer-btn-affirm">删除</a>\
							<a href="javascript:;" class="layer-btn layer-btn-cancel">取消</a>\
						</div>\
					</div>\
				</div>');
				$('body').append(layerMask);
				$('body').append(layerDialog);
				
				//取消
				$('.layer-btn-cancel').off('click').on('click',function(){
					$(layerMask).remove();
					$(layerDialog).remove();
				})
				
				//确认				
				$('.layer-btn-affirm').off('click').on('click',function(){
					$(layerMask).remove();
					$(layerDialog).remove();
					
					var id = $(layerMask).attr('data-id');					
					var el = $('.table-drag-con tr[data-id='+id+']');										
					This.delItem($(el).find('.table-drag-del'));					
				})
				
			})
			
			
			//展开 
		
			$(ele).on('click','.table-drag-tags-box-more',function(){
				if($(this).hasClass('hide1')){	//show
					$(this).siblings('.table-drag-tags-box-main').css('height','auto');
					$(this).removeClass('hide1');
				}else{  //hide
					$(this).addClass('hide1');
					$(this).siblings('.table-drag-tags-box-main').css('height','138');					
				}
			})
			
		})
		if(This.opts.drag === true){
			$(This.opts.el).find('.table-drag-tags').on('mousedown',function(e){
				This.el.tags = $(this);
				This.el.tagsBox = $(this).parents('.table-drag-tags-box').attr('data-id');
				This.mousedown(e);
				return false;
			})	
		}
		//目标点
		function moveTarget(obj,left,top,callback){
			$(obj).animate({left:left,top:top},100,function(){
				callback && callback();
			})
		}
		
		
	}
	
	//设置数据
	Drag.prototype.setSeriesData = function(data){
		var This = this;
		var t = {};
		This.series.length = This.legend.length;
		for(var i=0,len=data.length;i<len;i++){
			(function(d){
				if(t[d.groupId] == undefined){
					t[d.groupId] = {
						id : d.groupId,
						data : []
					}
					if(This.legend.length == 0){
						This.legend.push();
					}
				}
				t[d.groupId].data.push(d);
			})(data[i]);
		}
		for(x in t){
			var index = 0;			
			(function(name){
				for(var i=0;i<This.legend.length;i++){
					if(name == This.legend[i])index = i;
				}
			})(t[x].name);	
			This.series[index]=t[x];
		}
	}
	
	//创建元素
	Drag.prototype.createBox = function(){
		var This = this;
		
		
		var defalutItem = $('<div class="table-drag-item table-drag-item-default">\
			<div class="table-drag-tit-default"><span>资产名称</span><span>主体</span></div>\
			<div class="drag-item-box"><div class="table-drag-tags-box table-drag-con-default" data-id="-1"><div class="table-drag-tags-box-main"></div></div></div>\
		</div>');
		$(This.opts.el).append(defalutItem);
		
		This.series.length = 1;
		for(var i=0,len=This.series.length;i<len;i++){
			if(This.series[i]){
				(function(d){
					var data = d.data;
					var trHTML = '';	//tr 			
					
					for(var x=0,len2=data.length;x<len2;x++){
						var tips = data[x].level == 1  ? '请将有关联关系的资产拖到同一个关系框内' : '与其他任何资产均无相关性的资请拖到此框内';
						trHTML+= '<tr data-id="'+data[x].id+'" data-level="'+data[x].level+'">\
								<td>\
									<div class="table-drag-tags-box" data-id="'+data[x].id+'">\
										<div class="table-drag-tags-box-main"></div>\
										<div class="table-drag-tags-box-tips">'+tips+'</div>\
										<div class="table-drag-tags-box-more"></div>\
									<div>\
								</td>\
								<td><a href="javascript:;" class="table-drag-add"></a><a href="javascript:;" class="table-drag-del"></a></td>\
							</tr>';
					}
					
					var item = '<div class="table-drag-item-box"><div class="table-drag-item" data-id="'+d.id+'">\
						<div class="table-drag-tit"></div>\
						<div class="drag-item-box"><table class="table-drag-con">\
							<thead></thead>\
							<tbody>'+trHTML+'</tbody>\
						</table></div>\
					</div></div>';
					$(This.opts.el).append(item);
				})(This.series[i]);
			}
		}		
	}
	
	
	
	
	//创建标签
	Drag.prototype.createTags = function(){
		var This = this;
		var tags = this.opts.data.tags;
		for(var i=0,len=tags.length;i<len;i++){
			var elTags = $('<span class="table-drag-tags" data-id="'+tags[i].id+'" data-name="'+tags[i].data.name+'" data-content="'+tags[i].data.content+'"><i><em>'+tags[i].data.name+'</em><em>'+tags[i].data.content+'</em></i></span>');
			$(This.opts.el).find('.table-drag-tags-box[data-id='+tags[i].pId+']').find('.table-drag-tags-box-main').append(elTags);
		}
		
		$('.table-drag-con .table-drag-tags-box-main').each(function(index,ele){			
			This.tips(ele);
		})
	}
	
	
	Drag.prototype.tips = function(obj){
		var len = $(obj).children().length;
		if(len == 0){
			$(obj).siblings('.table-drag-tags-box-tips').show();
			$(obj).siblings('.table-drag-tags-box-more').hide();
		}else{					
			$(obj).siblings('.table-drag-tags-box-tips').hide();
			if(len >3){
				$(obj).siblings('.table-drag-tags-box-more').show().addClass('hide1');
				$(obj).css('height','138');				
			}else{
				$(obj).siblings('.table-drag-tags-box-more').hide().removeClass('hide1');
				$(obj).css('height','auto');
			}
		}
	}
	
	// down
	Drag.prototype.mousedown = function(e){
		var This = this;
		This.isMove = true;
		var x = e.pageX;
		var y = e.pageY;
		var top = $(This.el.tags).offset().top;
		var left = $(This.el.tags).offset().left;
		var iW = $(This.el.tags).find('i').outerWidth();
		var iH = $(This.el.tags).find('i').outerHeight();
		This.pos.left = x - left;
		This.pos.top = y - top;
		
		var id = $(This.el.tags).attr("data-id");
		var name = $(This.el.tags).attr("data-name");
		var content = $(This.el.tags).attr("data-content");
		This.el.mirror = $('<span class="table-drag-tags-mirror" data-id="'+id+'" data-name="'+name+'" data-content="'+content+'"><i><em>'+name+'</em><em>'+content+'</em></i></span>');			
		$(This.el.mirror).css({'left':left,'top':top,'width':iW});	
		$('body').append(This.el.mirror);
		$(This.el.tags).addClass('active');
		
		$(document).on('mousemove',function(ev){
			This.mousemove(ev);
		})
		
		$(document).on('mouseup',function(){
			This.mouseup();
		})
	}
	
	// move
	Drag.prototype.mousemove = function(ev){
		var This = this;
		if(This.isMove == false){return}
		var nowSquare = 0;	//当前平方
		var nowEl = null;	//当前值
		var moveX = ev.pageX;
		var moveY = ev.pageY;
		var left = moveX-This.pos.left;
		var top = moveY-This.pos.top;	
		var iW = $(This.el.tags).find('i').outerWidth();
		var iH = $(This.el.tags).find('i').outerHeight();
		var a = {	//需要移动元素的值
			l : left,
			t :	top,
			r : left+iW,
			b : top+iH
		}
		$(This.opts.el).find('.table-drag-tags-box').removeAttr('data-box').removeClass('active');		
		$(This.opts.el).find('.table-drag-tags-box').each(function(index,ele){
			var b = {
				l : $(ele).offset().left,
				t : $(ele).offset().top,
				r : $(ele).offset().left+$(ele).outerWidth(),
				b : $(ele).offset().top+$(ele).outerHeight()
			}
			This.collide(a,b,function(flag,square){
				if(flag){	//进入
					if($(ele).find('span.active').length == 0){
						if(square > nowSquare){
							nowSquare = square;
							nowEl = $(ele)
						}
					}
				}
			})
		});		
		$(nowEl).attr('data-box','true').addClass('active');		
		$(This.el.mirror).css({'left':left,'top':top});		
	}
	//碰撞点	a:当前,b:目标,c:回调
	Drag.prototype.collide = function(a,b,c){
		var flag = false;
		var square = 0;
		if(a.b < b.t || a.l > b.r || a.t > b.b || a.r < b.l){
			flag = false;
		}else{
			var w = 0;
			var h = 0
			//计算width
			if(a.l < b.r){
				w = Math.abs(b.r-a.l);
				if(a.r < b.r){
					w = Math.abs(a.r-a.l);
				}
				if(a.l < b.l){
					w = w - Math.abs(b.l - a.l);
				}
			}else if(a.r > b.l){
				w = Math.abs(a.r-b.l);
				if(a.l > b.l){
					w = Math.abs(a.r-a.l);
				}
				if(a.r < b.r){
					w = w - Math.abs(a.r - b.r);
				}
			}			
			//计算height
			if(a.t < b.b){
				h = Math.abs(b.b-a.t);
				if(a.b < b.b){
					h = Math.abs(a.b-a.t);
				}
				if(a.t < b.t){
					h = h - Math.abs(b.t-a.t);
				}				
			}else if(a.b > b.t){
				h = Math.abs(a.b-b.t);
				if(a.t > b.t){
					h = Math.abs(a.b-a.t);
				}
				if(a.b > b.b){
					h = h - Math.abs(a.b-b.b);
				}
			}			
			flag = true;			
			square = w*h;
		}
		c && c(flag,square);
	}
	
	// 鼠标up事件
	Drag.prototype.mouseup = function(){
		var This = this;
		if(This.isMove == true){				
			This.isMove = false;
			var isCreate = $(This.opts.el).find('.table-drag-tags-box[data-box=true]').length ? true : false;			
			if(isCreate == true){
				var tagsBox = $(This.opts.el).find('.table-drag-tags-box[data-box=true]').find('.table-drag-tags-box-main');
				var id = $(This.el.mirror).attr('data-id');
				var name = $(This.el.mirror).attr('data-name');
				var content = $(This.el.mirror).attr('data-content');
				var span = $('<span class="table-drag-tags" data-id="'+id+'" data-name="'+name+'" data-content="'+content+'"><i><em>'+name+'</em><em>'+content+'</em></i></span>');
				$(tagsBox).prepend(span);
				$(This.el.tags).remove();
				$(span).on('mousedown',function(e){
					This.el.tags = $(this);
					This.mousedown(e);
					return false;
				});
				$(This.opts.el).find('.table-drag-tags-box').removeAttr('data-box').removeClass('active');				
			}else{
				$(This.el.tags).removeClass('active');
			}
			$(This.el.mirror).remove();
			This.el.mirror = null;
			This.el.tags = null;
			This.el.tagsBox = null;
			$(document).off('mouseup');
			$(document).off('mousemove');
			
			$('.table-drag-con .table-drag-tags-box-main').each(function(index,ele){			
				This.tips(ele);
			})
			
		}
	}
	
	// 设置  添加 删除 按钮 显示隐藏
	Drag.prototype.setBtn = function(obj){
		var len = $(obj).find('tr').length;
		$(obj).find('.table-drag-add').css('display','inline-block');
		$(obj).find('.table-drag-del').css('display','inline-block');
		$(obj).find('.table-drag-del').eq(0).hide();
//		if(len == 2){
//			$(obj).find('.table-drag-add').css('display','inline-block');
//			$(obj).find('.table-drag-del').hide();
//		}else{
//			$(obj).find('.table-drag-add').hide();
//			$(obj).find('.table-drag-del').css('display','inline-block');
//			$(obj).find('tr').eq(len-1).find('.table-drag-add').css('display','inline-block');
//		}
	}
	
	
	// 添加  新增 ID 使用 temp- 加数字  , 数字使用时间戳
	Drag.prototype.addItem = function(obj){
		var This = this;
		var tbody = $(obj).parents('tbody');
		var now = $(obj).parents('tr').attr('data-level');
		var tr = $(obj).parents('tr').clone();
		var id = 'temp-'+new Date().getTime();
		$(tr).attr({'data-id':id,'data-level':Number(now)+1});
		$(tr).find('.table-drag-tags-box').attr('data-id',id).find('.table-drag-tags-box-main').empty();
		
		$(tr).find('.table-drag-tags-box-tips').html('请将有关联关系的资产拖到同一个关系框内').show();
		$(tr).find('.table-drag-tags-box-more').hide();
		
		if($(tr).find('.table-drag-checkbox').length > 0){
			$(tr).find('.table-drag-checkbox').removeAttr('checked').attr('data-id','');
		}		
		This.setLevel(now,1);
		$(tbody).append(tr);
	}
	
	// 删除
	Drag.prototype.delItem = function(obj){
		var This = this;
		var tbody = $(obj).parents('tbody');
		var tr = $(obj).parents('tr');
		var now = $(tr).attr('data-level');
		var tags = $(tr).find('.table-drag-tags');	
		$(This.opts.el).find('.table-drag-con-default').append(tags);		
		This.setLevel(now,-1);
		$(tr).remove();
	}
	
	//设置等级
	Drag.prototype.setLevel = function(now,num){
		var now = Number(now);
		var This = this;
		$(This.opts.el).find('tbody tr').each(function(index,ele){
			var level = Number($(ele).attr('data-level'));
			if(level > now){			
				$(ele).attr('data-level',level+num);
				$(ele).find('.table-drag-list-level').html(level+num);
			}
		})
	}
	
	//获取结果
	Drag.prototype.getResult = function(){
		var This = this;
		var res = {
			list : [],
			tags : []
		}
		
		$(This.opts.el).find('.table-drag-item').each(function(index,ele){
			var tit = $(ele).find('.table-drag-tit').attr('data-name');
			var tr = $(ele).find('tbody tr');
			var groupId = $(ele).attr('data-id');
			var checked = $(ele).find('.table-drag-checkbox').length;
			$(tr).each(function(index2,ele2){
				var id = $(ele2).attr('data-id');
				var level = $(ele2).attr('data-level');
				var tags = $(ele2).find('.table-drag-tags');
				var item = {
						"id":id,
						"level":level,
						"groupId":groupId
					}
				res.list.push(item);	//list
				
				$(tags).each(function(i,el){
					res.tags.push({
						"id":$(el).attr('data-id'),
						"data":{							
							"name":$(el).attr('data-name'),
							"content":$(el).attr('data-content')
						},
						"pId":id
					});
				})
			});
		})
		
		var defaultId = $(This.opts.el).find('.table-drag-con-default').attr('data-id');
		$(This.opts.el).find('.table-drag-con-default .table-drag-tags').each(function(index,el){
			res.tags.push({
				"id":$(el).attr('data-id'),
				"data":{							
					"name":$(el).attr('data-name'),
					"content":$(el).attr('data-content')
				},
				"pId":defaultId
			});
		})		
		return res;
	}
	
	//创建实例
	var _Drag = function(options){
		return new Drag(options);
	}
	window.$$tableDrag = _Drag;
	
	$.fn.tableDrag = function(options){
		options.el = $(this);
		return new Drag(options);
	}
})();