/**
 * Created by zhaotm on 2017/7/7.
 */

//通过一级行业加载二级行业
$(document).on('click',".industry1_change li",function(){
    //二级行业赋值
    $("#industry2").parent().find("span").text("请选择")
    $("#industry2").val("-1");

    findIndustry2();
});

function findIndustry2() {
    var industryId= $("#industry1").val();
    if(industryId != null && industryId != ""){
        $.ajax({
            url:industryUrl,
            type:'POST',
            data: {"pid":industryId},
            success:function(data){
                if(data){
                    var industry = data.industry2;

                    //清空 li
                    $("#industry2List").empty();

                    if(industry != null && industry != ""){
                        var liHtml = '';

                        for(var i=0;i<industry.length;i++){
                            liHtml += '<li data-id="'+industry[i].id+'">'+industry[i].name+'</li>';
                        }
                    }

                    $("#industry2List").append(liHtml);
                }
            }
        });
    }
}

//通过行业加载指标
/*
* industry2Id       行业2Id
*  industry2Id      企业规模
* indexIds          指标ids
* ruleIds           规则ids
* editShowType      编辑标志1：主体编辑指标2：评级编辑，/*3：主体查看 4：评级(成功，退回)查看/*,5:审批查看
* adjustReason      调整理由
* hiddenAdjustFlag  隐藏调整信息
* changeContent     调整记录
*/
function loadIndexById(industry2Id, entType, indexIds, ruleIds, editShowType, adjustReason, indexChangeContent){
    var indexRuleMap = new Array();
    var indexReasonMap = new Array();
    var indexChangeMap = new Array();
    var indexIds = indexIds.split(",");
    var ruleIds = ruleIds.split(",");

    //指标和ruleId
    for (var i=0; i<indexIds.length; i++) {
        indexRuleMap[indexIds[i]] = ruleIds[i];
    }

    //调整理由
    if (adjustReason) {
        var indexReasonArray = adjustReason.split(",");

        for (var i=0; i<indexReasonArray.length; i++) {
            var indexAndReason = indexReasonArray[i].split(":");
            var indexId = indexAndReason[0];
            var adjustReason = indexAndReason[1];

            indexReasonMap[indexId] = adjustReason;
        }
    }
    
    //指标调整记录调整
    if (indexChangeContent) {
        var indexChangeArray = indexChangeContent.split(",");

        for (var i=0; i<indexChangeArray.length; i++) {
            var indexAndChange = indexChangeArray[i].split(":");
            var indexId = indexAndChange[0];
            var change = indexAndChange[1];

            indexChangeMap[indexId] = change;
        }
    }
   
    //查指标
    $.ajax({
        async: false,
        url:indexUrl,
        type:'POST',
        data: {"industry2Id":industry2Id, "entType":entType},
        success:function(data){
            if (200 != data.code) return;

            var trHtml = "";
            var indexList = data.list;
            $("#indexListDiv").html("");


            for (var i=0;i<indexList.length;i++){
                //遍历指标
                var ruleValue = "请选择";
                var ruleList = indexList[i].ruleList;

                if (1 == editShowType || 2  == editShowType) {
                    //审批
                    if (2 == editShowType) {
                        trHtml += "<div class='col-lg-12 col-md-12 col-sm-12 clear'>";
                    } else {
                        1 == editShowType
                        trHtml += "<div class='col-lg-6 col-md-6 col-sm-6'>";
                    }
                    if (2 == editShowType) {
                        trHtml += "<div class='indicator_box indicator_box1 fl'>";
                    } else {
                        trHtml += "<div class='indicator_box indicator_box1 clear'>";
                    }
                    trHtml += "<input type='hidden' name='indexId' value='"+indexList[i].id+"'/>"+
                        "<strong title='"+indexList[i].indexName+"' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap' class='fl'><i>*</i>"+indexList[i].indexName+"</strong>"+
                        "<div class='select_parent fl'>";
                    var j = 0;
                    trHtml += "<div class='main_select select_btn'>";
                    for (j=0; j<ruleList.length; j++) {
                        //遍历规则
                        if (indexRuleMap[indexList[i].id] == ruleList[j].id) {
                            //选中的名字
                            ruleValue = ruleList[j].value;
                            trHtml += "<span data-id='"+ruleList[j].id+"'>"+ruleValue+"</span>";
                            break;
                        }
                    }
                    if (j == ruleList.length) {
                        trHtml += "<span data-id='-1'>"+ruleValue+"</span>";
                    }

                    trHtml +="</div>"+ //main_select select_btn
                    "<ul class='main_down select_list'>"+
                    "<li data-id='-1'>请选择</li>";
                    for (var j=0; j<ruleList.length; j++) {
                        //遍历规则
                        if (indexRuleMap[indexList[i].id] == ruleList[j].id) {
                            trHtml += "<li title='"+ruleList[j].value+"' class='active' data-id='"+ruleList[j].id+"'>"+ruleList[j].value+"</li>";

                        } else {
                            trHtml += "<li title='"+ruleList[j].value+"' data-id='"+ruleList[j].id+"'>"+ruleList[j].value+"</li>";
                        }
                    }
                    trHtml += "</ul>"+
                        "</div>"+//select_parent fl
                        "</div>";//indicator_box indicator_box1 clear
                    if (2 == editShowType) {
                        trHtml += '<input type="text" class="approve_input" placeholder="请输入调整理由" maxlength="20" />';
                        trHtml += "<input type='hidden' name='adjustReason' value='"+ruleValue+"'/>";
                    }
                    trHtml += "</div>";//col-lg-6 col-md-6 col-sm-6
                } else if (5  == editShowType || 3 == editShowType || 4 == editShowType) {
                    //主体，评级查看
                    if (5 == editShowType) {
                        trHtml += "<div class='col-lg-12 col-md-12 col-sm-12'>";
                    } else {
                        trHtml += "<div class='col-lg-6 col-md-6 col-sm-6'>";
                    }
                    trHtml +=    "<div data-id='appDiv' class='indicator_box indicator_box1 clear'>"+
                        "<strong title='"+indexList[i].indexName+"' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap' class='fl'>"+indexList[i].indexName;
                    if (indexReasonMap[indexList[i].id] && 4 == editShowType) {
                        //评级，和退回查看
                        trHtml += "<em class='emTip'></em>";
                    }
                    trHtml += "</strong>"+
                        "<div class='select_parent fl'>";

                    var j = 0;
                    for (j=0; j<ruleList.length; j++) {
                        //遍历规则
                        if (indexRuleMap[indexList[i].id] == ruleList[j].id) {
                            trHtml += "<span>"+ruleList[j].value+"</span>";
                            break;
                        }
                    }
                    if (j == ruleList.length) {
                        trHtml += "<span>未选择</span>";
                    }

                    trHtml += "</div>";//select_parent fl
                    if (indexReasonMap[indexList[i].id] && 4 == editShowType) {
                        //已评级，和退回查看
                        trHtml += " <div class='indicator_tip'>"+
                            "<div></div>" +
                            "<p>原始指标："+indexChangeMap[indexList[i].id]+"</p>" +
                            "<p>调整理由："+indexReasonMap[indexList[i].id]+"</p>" +
                            "</div>";

                    }
                    trHtml += "</div>"+//indicator_box indicator_box1 clear
                    "</div>";//col-lg-6 col-md-6 col-sm-6
                }
            }
            if ("" == trHtml) {
                trHtml = "<div style='height:30px;'><p style='margin-left:50px; margin-top:10px; color:#999;'>暂无数据</p></div>";
            }

            if (2 == editShowType) {
                $("#indexListEdit").html(trHtml);
            } else if (5 == editShowType) {
                $("#indexListShow").html(trHtml);
            } else {
                $("#indexListDiv").html(trHtml);
            }
        }
    });
}