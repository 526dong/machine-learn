<%--
  Created by IntelliJ IDEA.
  User: zhaotm
  Date: 2017/11/21
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>实验室-项目库 </title>
    <link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/comet4j.js"></script>
    <script type="text/javascript">
        var logCache = [];
        var stop = false;
        var timingTimer = logsTimer = timedTask = null;
        var str ='==================================================================';
        $(function () {
            pushTradeMsg();
            findProgram();

            //页数跳转
            $("#currentNum").blur(function(){
                var currentNum=  $("#currentNum").val();
                $("#currentPage").val(currentNum);
                findProgram();
            })

            /*打开日志弹窗 */
            /* $('.project-state-icon').click(function(){
             getProgressInfo('项目id');
             });*/

            /*关闭日志弹窗 */
            $('.typing-close').click(function(){
                $('.typing').fadeOut(200,function(){

                    // 清除定时器及日志、时间信息
                   /* clearInterval(timingTimer);
                    clearInterval(timedTask);
                    window.cancelAnimationFrame(logsTimer);

                    $('#logs').html('正在读取日志信息......');
                    $('.timing').text('查询中');*/
                });
            });

        });
        function clearLog() {
            // 清除定时器及日志、时间信息
             clearInterval(timingTimer);
             clearInterval(timedTask);
             window.cancelAnimationFrame(logsTimer);

             $('#logs').html('正在读取日志信息......');
             $('.timing').text('查询中');
             j("清空成功");
        }

        /* 处理日志字符串 */
        function fommat_log(str){
            var i = 0;
            var arr = str.split(/\n|\r/);
            while(i < arr.length){
                logCache.push(arr[i++]);
            }
        }
        //消息推送
        function pushTradeMsg(){
            JS.Engine.start('${ctx}/message/conn');
            JS.Engine.on({
                model : function(info){//侦听一个channel

                  if(info.iflog){
                      console.log(info.data);
                      fommat_log(info.data);
                      if(!stop && !logsTimer){
                          typing();
                      }
                      return;
                  }
                 if(info.iftimer!=null&&info.iftimer){
                      console.info("time::::"+info.timestart);
                      var ti=new Date().getTime()-info.timestart;
                      console.info("time::::"+ti);
                     timingTimer= timing(ti);
                     return;
                 }
                 if (info.code){
                     jAlert('项目"'+info.name+'",'+info.sf+'算法计算完成');
                 }else {
                     jAlert('项目"'+info.name+'",'+info.sf+'算法计算失败');
                 }
                 if (info.ifend){
                     var li=$("#id"+info.id).parent().parent().parent().parent();
                     if (info.status==0){
                         jAlert('项目算法全部完成，部分算法计算失败,详细信息：'+info.msg);
                         $("#id"+info.id).text('完成');
                         $("#time_"+info.id).text(info.time);
                         li.children().attr("style","cursor:hand");
                         li.children().attr("onclick","toModelReport("+info.id+")");
                     }else if (info.status==1){
                         clearInterval(timingTimer);
                         $("#id"+info.id).text('完成');
                         $("#time_"+info.id).text(info.time);
                         li.children().attr("style","cursor:hand");
                         li.children().attr("onclick","toModelReport("+info.id+")");
                         jAlert('项目算法全部完成，所有算法计算成功,详细信息：'+info.msg);
                     }else {
                         $("#id"+info.id).text('失败');
                         jAlert('项目算法全部完成，所有算法计算失败,详细信息：'+info.msg);
                     }
                 }
                }
            });

        }

        //分页跳转
        function jumpToPage(curPage){
            if(typeof(curPage) != "undefined"){
                $("#currentPage").val(curPage);
            }else{
                $("#currentPage").val(1);
            }
            //查询
            findProgram();
        }

        //
        function enterSearch() {
            if (event.keyCode == 13){
                event.returnValue=false;
                event.cancel = true;
                findProgram();
            }
        }

        //查modelajax
        function findProgram() {
            $.ajax({
                url:"${ctx}/program/indexList",
                type:"POST",
                dataType:"json",
                data: {
                    "pageSize":5,//一页显示的条数
                    "pageNum" : $("#currentPage").val(),//当前页
                    "keyWord": $("#keyWord").val()
                },
                success:function (data) {
                    if (200 == data.code) {
                        var datas = data.data;
                        createTable(datas.list);
                        var pageStr = createPage(datas.total, datas.pageNum,datas.pages);
                        $("#pageDiv").html(pageStr);
                    } else {
                        jAlert("加载失败！")
                    }
                }
            });
        }

        //创建用户列表
        function createTable(rows) {
            var liHtml = "<ul class='clearfix'>";

            for (var i=0; i<rows.length; i++) {
                var row = rows[i];
                var status = row.status;
                var statusShow = status == 0? '未执行' : (status == 1? '计算中' : (status==2?'完成':'失败'));


                if (2 == status) {
                    liHtml += "<li class='project-lib-item clearfix'>";
                    liHtml += "<div class='project-cell project-name' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p>"+row.name+"</p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-data' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p>数据表<br>";
                    liHtml += "<span>"+row.dataFileName+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-sample' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p>样本量/维度<br>";
                    liHtml += "<span>"+row.dataSampleNum+"/"+row.indexName+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-algorithm' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p>算法/模型配置<br>";
                    liHtml += "<span>"+row.arithmeticNames+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-createtiem' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p>创建时间<br>";
                    liHtml += "<span>"+row.createTimeStr+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-time-cost' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p>用时<br>";
                    liHtml += "<span>"+(row.castTime == null ? '': row.castTime)+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-state' style='cursor:hand' onclick='toModelReport("+row.id+");'>";
                    liHtml += "<p><span class='project-state-icon' id='id"+row.id+"'>"+statusShow+"</p></span>";
                } else if (0 == status) {
                    liHtml += "<li class='project-lib-item clearfix'>";
                    liHtml += "<div class='project-cell project-name' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p>"+row.name+"</p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-data' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p>数据表<br>";
                    liHtml += "<span>"+row.dataFileName+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-sample' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p>样本量/维度<br>";
                    liHtml += "<span>"+row.dataSampleNum+"/"+row.indexName+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-algorithm' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p>算法/模型配置<br>";
                    liHtml += "<span>"+row.arithmeticNames+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-createtiem' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p>创建时间<br>";
                    liHtml += "<span>"+row.createTimeStr+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-time-cost' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p>用时<br>";
                    liHtml += "<span>"+(row.castTime == null ? '': row.castTime)+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-state' style='cursor:hand' onclick='edit("+row.id+");'>";
                    liHtml += "<p><span class='project-state-icon' >"+statusShow+"</p></span>";
                }else{
                    liHtml += "<li class='project-lib-item clearfix'>";
                    liHtml += "<div class='project-cell project-name'>";
                    liHtml += "<p>"+row.name+"</p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-data'>";
                    liHtml += "<p>数据表<br>";
                    liHtml += "<span>"+row.dataFileName+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-sample'>";
                    liHtml += "<p>样本量/维度<br>";
                    liHtml += "<span>"+row.dataSampleNum+"/"+row.indexName+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-algorithm'>";
                    liHtml += "<p>算法/模型配置<br>";
                    liHtml += "<span>"+row.arithmeticNames+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-createtiem'>";
                    liHtml += "<p>创建时间<br>";
                    liHtml += "<span>"+row.createTimeStr+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-time-cost'>";
                    liHtml += "<p>用时<br>";
                    liHtml += "<span id='time_"+row.id+"'>"+(row.castTime == null ? '': row.castTime)+"</span></p>";
                    liHtml += "</div>";

                    liHtml += "<div class='project-cell project-state'>";
                    liHtml += "<p><span class='project-state-icon' id='id"+row.id+"' onclick='showJsOn("+row.id+")'>"+statusShow+"</p></span>";
                }
                liHtml += "</div>";

                liHtml += "<div class='project-del' onclick='del("+row.id+");'></div>";
                liHtml += "</li>";
            }
            if (0 ==rows.length) {
                liHtml += "暂无数据";
            }
            liHtml += "</ul>";
            $("#programContent").html(liHtml);
        }

        function toModelReport(programId) {
            window.location.href="${ctx}/modelsReport/toModelsReportPage?programId="+programId;
        }

        //删除
        function del(id) {
            jConfirm("你真的要删除项目项目么？", function () {
                $.ajax({
                    url: "${ctx}/program/del",
                    type: "POST",
                    dataType: "json",
                    data: {"id": id},
                    success: function (data) {
                        if (200 == data.code) {
                            jAlert("删除成功！", "${ctx}/program/index")
                        } else {
                            jAlert("删除失败！")
                        }
                    }
                })
            });
        }

        function edit(id) {
            window.location.href = "${ctx}/program/toEditProgramPage?id="+id;
        }

        function showJsOn(id) {
            console.info(1);
            getProgressInfo(id);
        }

        function typing(){
            var i = 0,tmp,
                $wrap = $('.logs-wrap'),
                $logs = $('#logs'),
                diff;

            if( i < logCache.length ){

                tmp = logCache.splice(i,1);
                $logs.append('<p>'+tmp+'</p>');

                diff = $logs.height() - $wrap.height();

                if( diff > 0 ){
                    $wrap.scrollTop(diff+200);
                }
                return logsTimer = window.requestAnimationFrame(typing);
                //timer= setTimeout(typing,30);
            }else{
                window.cancelAnimationFrame(logsTimer);
                logsTimer = null;
                // clearTimeout(timer);
                return ;
            };
        }

        /* 计时器 */
        function timing(time){
            time = parseInt(time);
            var timer = null;
            var t = $('.timing');
            var h = Math.floor(time/3600000);
            var m =  Math.floor((time%3600000)/60000);
            var s = Math.floor(time/1000%60);

            var addTime = function(){
                if(s >= 60){
                    m++;
                    s = 0;
                }
                if(m >= 60){
                    h++;
                    m = 0;
                }
                t.html( (h < 10 ? ('0'+h) : h) +':'+ (m < 10 ? ('0'+m) : m) +':'+ (s < 10 ? ('0'+s) : s));
                s++;
            };

            timer = setInterval(addTime,1000);

            return timer;
        }


        /* 暂停/开始 */
        function setPause (){
            var $wrap = $('.logs-wrap');
            $wrap.hover(function(){
                window.cancelAnimationFrame(logsTimer);
                logsTimer = null;
                stop = true;
            },function(){
                typing();
                stop = false;
            });

        };
        function typing(){
            var i = 0,tmp,
                $wrap = $('.logs-wrap'),
                $logs = $('#logs'),
                diff;

            if( i < logCache.length ){

                tmp = logCache.splice(i,1);
                $logs.append('<p>'+tmp+'</p>');

                diff = $logs.height() - $wrap.height();

                if( diff > 0 ){
                    $wrap.scrollTop(diff+200);
                }
                return logsTimer = window.requestAnimationFrame(typing);
                //timer= setTimeout(typing,30);
            }else{
                window.cancelAnimationFrame(logsTimer);
                logsTimer = null;
                // clearTimeout(timer);
                return ;
            };
        }

        /* 获取进度信息 */
        function getProgressInfo(id){
            $('.typing').fadeIn(200,function(){

                // 开始计时
               // timingTimer = timing(12250556);
                setPause();

               /* //  每间隔20秒追加日志信息
                timedTask = setInterval(function(){
                    if(new Date().getTime() >= new Date('2017/12/22 18:08:06').getTime()){
                        clearInterval(timingTimer);
                    }else{
                        fommat_log(str);
                        if(!stop && !logsTimer){
                            typing();
                        }
                    }
                },50);*/
            });
        }
    </script>
</head>
<body>
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象">
<!-- //website-bg -->
<div class="j-container">
    <%--头文件====开始--%>
    <jsp:include page="../commons/topHead.jsp"/>
    <!-- //header -->
    <div class="content">
        <%--导航栏====开始--%>
        <jsp:include page="../commons/leftNavigation.jsp"/>
        <%--导航栏====结束--%>
        <!-- //side-nav -->
            <div class="main">
                <div class="main-header clearfix">
                    <div class="page-title">
                        <h3>实验室</h3>
                        <p>项目列表</p>
                    </div>

                    <div class="query-box">
                        <a class="j-button import-file" href="javascript:;" onclick="showJsOn(1);">
                        <span>&nbsp;日志控制台</span>
                        </a>&nbsp;
                        <a class="j-button import-file" href="javascript:;" onclick="clearLog();">
                        <span>&nbsp;日志清空</span>
                        </a>&nbsp;
                        <div class="srch-box">
                            <i class="input-enter-icon" onclick="findProgram()"></i>
                            <input class="j-input srch-ipt" type="text" id="keyWord" name="keyWord" placeholder="输入项目名/数据文件名搜索" onkeyup="enterSearch(this);" />
                        </div>

                    </div>
                    <!-- //query-box -->
                </div>

                <!-- //main-header -->
                <div class="project-lib" id="programContent">

                </div>
                <!-- file-list -->
                <!-- 分页.html start -->
                <!-- 分页.html start -->
                <input type="hidden" id="currentPage" name="currentPage" value="1"/>
                <%@ include file="../commons/tabPage.jsp"%>
                <!-- 分页.html end -->
                <!-- 分页.html end -->
            </div>
            <!-- //main -->
    </div>
</div>

<div class="typing">
    <div class="typing-bg"></div>
    <div class="typing-close">xx</div>
    <div class="typing-content">
        <div class="typing-logs">
            <div class="logs-wrap">
                <div id="logs">正在读取日志信息......</div>
            </div>
        </div>
        <!-- //typing-logs -->
        <div class="typing-time">
            <div class="timer">
                <img src="${ctx}/resources/img/timedown_circle.gif" alt="">
                <span class="timer-data">
                    <span class="time-spend">已用时</span>
                    <span class="timing">查询中</span>
                </span>
            </div>
        </div>
        <!-- //typing-time -->
    </div>
</div>
<!-- //typing -->

</body>
</html>