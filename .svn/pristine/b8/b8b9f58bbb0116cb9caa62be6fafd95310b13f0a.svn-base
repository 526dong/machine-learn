/*created by xzd*/
/**
 * myValidate validate form
 * use eg:
     myValidate(
         {
             formId: "loginForm",
             items:
                 ["name", "password"],
             rules:
                 ["required", "required"],
             success: function (data) {
                 alert("login");
             },
             errorPlacement: function (error, element) {
                 element.after(error);
             }
         }
     );
 * @type {{required: ruleObj.required}}
 */
//rules obj
var ruleObj = {
    required:function(ele){
        return $.trim($(ele).val()).length > 0;
    },
}
//message obj
var msgObj = {
    required: "必填",
    email: "Please enter a valid email address.",
    number: "请输入数字.",
}
/*var msgMap = new Array();
msgMap["required"] = "不能空";*/
/*msgMap["mobile"] = "格式不正确";
msgMap["email"] = "格式不正确";
msgMap["weight"] = "只能为小数";*/
//main entrance
function myValidate(obj) {
    var pass = true;
    var formId = obj.formId;
    var items = obj.items;
    var rules = obj.rules;
    var passFun = obj.success;
    var errEleFun = obj.errorPlacement;
    //clear formId errtry
    $("#"+formId+" errtry").each(function () {
        $(this).remove();
    });
    //form element
    for (var i = 0;i < items.length;i++) {
        //element
        var element = $("#"+formId+" #"+items[i]);
        //rules
        var rule = rules[i];
        //rule array
        if (rule instanceof Array) {
            for (var j = 0;j < rule.length;j++) {
                if (!getRuleVal(rule[j], element)) {
                    pass = false;
                    var errEle = "<errtry class='error3' for='"+rule[j]+"'>"+msgObj[rule[j]]+"</errtry>";
                    errEleFun($(errEle), element);
                    break;
                }
            }
        } else {
            if (!getRuleVal(rule, element)) {
                pass = false;
                var errEle = "<errtry class='error3' for='"+rule[j]+"'>"+msgObj[rule]+"</errtry>";
                errEleFun($(errEle), element);
            }
        }
    }
    //通过回掉成功函数
    if (pass) {
        passFun();
    }
}
//get rule value
function getRuleVal(rule, element){
    for (var key in ruleObj){
        if (key == rule) {
            return ruleObj[rule](element);
        }
    }
}


