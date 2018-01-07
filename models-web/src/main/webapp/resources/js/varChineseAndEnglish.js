//获取中英文对应关系
function getChinsesAndEnglish(fileId,href) {
    var varLanguage = "";
    if("" != fileId && null != fileId && typeof(fileId) != "undefined"){
        $.ajax({
            type : 'POST',
            url : href,
            async:false,
            data : {
                "fileId" : fileId
            },
            success : function(data) {
                if(200 == data.code){
                    varLanguage = data.data;
                }
            }
        })
    }
    return varLanguage;
}
//中英文切换
function languageChange(htmlId,thId,varLanguage){
    if("" != varLanguage && null != varLanguage && typeof(varLanguage) != "undefined"){
        var englishList = varLanguage.englishList;
        var chineseList = varLanguage.chineseList;
        if(null != englishList && englishList.length>0 && null != chineseList && chineseList.length>0){
            var html = $("#"+htmlId).html();
            var flag = $("#"+thId).html();
            if("中文" == flag){
                for(var i=0;i<englishList.length;i++){
                    if(null != englishList[i] && "" != englishList[i] && null != chineseList[i] && "" != chineseList[i]){
                        html = html.replace(new RegExp(englishList[i],"g"),chineseList[i]);//g,表示全部替换
                    }
                }
            }else{
                for(var i=0;i<chineseList.length;i++){
                    if(null != englishList[i] && "" != englishList[i] && null != chineseList[i] && "" != chineseList[i]){
                        html = html.replace(new RegExp(chineseList[i],"g"),englishList[i]);//g,表示全部替换
                    }
                }
            }
            $("#"+htmlId).html(html);
            $("#"+thId).html($("#"+thId).html()=="中文"?"英文":"中文");
        }
    }
}