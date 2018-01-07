/**
 * Created by zhaotm on 2017/7/10.
 */

//初始化评级退回和调整信息
function showAdjustInfo(content) {
    var indexAndRule = content.split(",");

    for (var i=0; i<indexAndRule.length; i++) {
        var indexRules = indexAndRule[i].split(":");
        var indexId = indexRules[0];
        var adjustContent = indexRules[1];

        var span = $("#index_"+indexId).parent().parent().find("span");
        span.text("调整理由："+adjustContent);
    }
}

var adjustIndexIds = [];
var adjustRuleContent = [];
var adjustChange = [];
var indexIds = [];
var ruleIds = [];
var indexNameAndValue;

//保存修改指标
function saveAdjust() {
    var errFlag = false;
    indexIds.splice(0, indexIds.length);
    ruleIds.splice(0, ruleIds.length);
    adjustIndexIds.splice(0, adjustIndexIds.length);
    adjustRuleContent.splice(0, adjustRuleContent.length);
    adjustChange.splice(0, adjustChange.length);
    indexNameAndValue = "";

    $("input[name='indexId']").each(function(i, ele) {
        var indexId = ele.value;
        var ruleId = $(ele).parent().find(".select_btn span").attr("data-id");
        var reason = $(ele).parent().next().val();
        var before = $(ele).parent().next().next().val();

        if (-1 == ruleId) {
            $(ele).parent().find(".select_parent").append("<p class='error error2'>请选择指标</p>");
            errFlag = true;
        } else {
            $(ele).parent().find("p").remove();
        }

        if ("" != reason) {
            adjustIndexIds.push(indexId);
            adjustRuleContent.push(reason);
            //调整的变更
            adjustChange.push(before);
        }

        indexIds.push(indexId);
        ruleIds.push(ruleId);
    });

    //保存提交的指标信息
    var idArr = $("input[name='indexId']");
    $("#indexListShow div[data-id='appDiv']").each(function (i) {
        var id = idArr.eq(i).val();
        var name = $(this).find("strong").text();
        var value = $(this).find("span").text();
        if (i ==0) {
            indexNameAndValue += "{\"id\":"+id+", \"name\":\""+name+"\", \"value\":\""+value+"\"}";
        } else {
            indexNameAndValue += ", {\"id\":"+id+", \"name\":\""+name+"\", \"value\":\""+value+"\"}";
        }
    });
    indexNameAndValue = "["+indexNameAndValue+"]";

    if (errFlag) {
        return false;
    } else {
        return true;
    }
}

/* editShowType  4：评级(成功，退回,提交)查看,5:审批查看*/
function showIndex(indexJson, editShowType, adjustReason, indexChangeContent){
    var indexArr = eval(indexJson);
    var indexRuleMap = new Array();
    var indexReasonMap = new Array();
    var indexChangeMap = new Array();

    for (var i=0; i<indexArr.length; i++) {
        var index = indexArr[i];
        var id = index.id;
        var name = index.name;
        var value = index.value;

        //指标和ruleId
        indexRuleMap[id] = value;
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

    var trHtml = "";
    $("#indexListDiv").html("");

    for (var i=0;i<indexArr.length;i++){
        var index = indexArr[i];
        var id = index.id;
        var name = index.name;
        var value = index.value;
        //遍历指标
        var ruleValue = "请选择";

        if (5  == editShowType || 4 == editShowType) {
            //主体，评级查看
            if (5 == editShowType) {
                trHtml += "<div class='col-lg-12 col-md-12 col-sm-12'>";
            } else {
                trHtml += "<div class='col-lg-6 col-md-6 col-sm-6'>";
            }
            trHtml += "<div class='indicator_box indicator_box1 clear'>"+
                "<strong title='"+name+"' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap' class='fl'>"+name;
            if (indexReasonMap[id] && 4 == editShowType) {
                //评级，和退回查看
                trHtml += "<em class='emTip'></em>";
            }
            trHtml += "</strong>"+
                "<div class='select_parent fl'>";
                    trHtml += "<span>"+value+"</span>";
            trHtml += "</div>";//select_parent fl
            if (indexReasonMap[id] && 4 == editShowType) {
                //已评级，和退回查看
                trHtml += " <div class='indicator_tip'>"+
                    "<div></div>" +
                    "<p>原始指标："+indexChangeMap[id]+"</p>" +
                    "<p>调整理由："+indexReasonMap[id]+"</p>" +
                    "</div>";

            }
            trHtml += "</div>"+//indicator_box indicator_box1 clear
                "</div>";//col-lg-6 col-md-6 col-sm-6
        }
    }
    if ("" == trHtml) {
        trHtml = "<div style='height:30px;'><p style='margin-left:50px; margin-top:10px; color:#999;'>暂无数据</p></div>";
    }

    if (5 == editShowType) {
        $("#indexListShow").html(trHtml);
    } else {
        $("#indexListDiv").html(trHtml);
    }
}