$(document).ready(function(){
    /**
     * 头部下拉菜单
     */
    $('.header-crtl-arrow').click(function(e){
        $(this).toggleClass('active').siblings('.header-crtl-list').slideToggle();
        $(this).siblings('.header-crtl-list').on('click','li',function(){
            $('.header-crtl-list').slideUp()
        })
        $(document).one('click',function(){
            $('.header-crtl-list').slideUp();
        })
        e.stopPropagation();
    })

    /**
     * 侧边栏导航
     */
    $(document).on('click','.side-item',function(e){
        var _this = $(this);
        _this.parent().siblings('li').children('.side-sub-nav').slideUp().siblings('.side-item').removeClass('selected');
        _this.toggleClass('selected').siblings('.side-sub-nav').slideToggle();
        e.stopPropagation();
    });

    /**
     * 自定义下拉
     */
    $(document).on('click','.j-select .j-select-placeholder',function(e){

        var _this = $(this);
        _this.siblings('.j-select-arrow').toggleClass('active').siblings('.j-select-options').slideToggle();
        $(document).one('click',function(){
            $('.j-select-options').slideUp();
        })
        $('.j-select-options').on('click','li',function(){
            _this.html($(this).html());
        })
        e.stopPropagation();
    });

    /**
     * 导入数据文件
     */
    $('.import-file').click(function(){
        $('#select_file').find('form').get(0).reset();
        $('#source_file_name').html('');
        $('#select_file_type').html('');
        $('#select_file_rename').val('');
        $('#select_file').fadeIn(200);
    })
    $('.close-pop-btn').click(function(){
        $('#select_file').fadeOut(200);
    })
    $('.browser-file-btn').click(function(){
        var _this = $(this);
        _this.siblings('input').click();
    })
    $('#browser-file-ipt').on('change',function(){
        var _this = $(this);
        var filename = _this.get(0).files[0].name;
        var type = filename.substr(filename.lastIndexOf(".")+1).toLowerCase();
        $('#source_file_name').html(filename);
    })
})