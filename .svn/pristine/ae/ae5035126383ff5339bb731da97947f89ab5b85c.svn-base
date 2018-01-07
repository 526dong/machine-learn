package com.ccx.models.util;

public enum EnumSysManage {
	
	SUCCESS("处理成功","0000"),
	FAILE("处理异常","1111"),
	UNIQUEDATA("查询数据唯一性","2222"),
	NOPERMISSION("无权限访问","3333"),
	SYSFAILE("系统异常","9999"),
	
	ACCOUNTERR("账号名或密码不正确", "0"), 
	ACCOUNTNO("账号不存在，请核对账号名", "1"), 
	ACCOUNTFORZEN("账号名停用，请联系管理员启用","2"), 
	ACCOUNTCANCEL("账号名已注销", "3"),
	ACCOUNTLOCK("账号名已锁定，请联系管理员解锁","4"),
	
	ROLELIVING("角色下存在正常账号，无法删除！","5"),
	
    
    RC_CHECKSIGN_FAIL("系统错误","1001"),
    RC_ACCOUNT_NULL("您的账号已被禁用","1002"),
    RC_AppHistory_FAIL("系统错误","1003"),
    RC_TOKEN_FAIL("请重新登录","1004"),
    
//  注册
    RC_REG_PHONE_ERROR("手机号错误","1201"),
    RC_REG_PHONE_EXISTD("手机号已注册","1202"),
    RC_REG_PHONE_NULL("手机号未注册","1203"),
    RC_REG_PWD_SIMPLE("密码应由6~16位字母、数字或符号两种或三种组成","1204"),
	RC_REG_CODE_ERROR("验证码错误，请重新输入","1205"),
	RC_REG_CODE_TIMEOUT("验证码已失效，请重新获取","1206"),
	RC_REG_CODE_NULL("还未获取验证码，请重新获取","1207"),
	RC_REG_CODE_ERROR_MORE("验证码多次错误，请重新获取","1208"),//验证第五次错误之后返回
	RC_REG_CODE_COUNT_MORE("验证码发送次数过多，请明天重试","1209"),//发送第6次之后，第7次请求时返回
	RC_REG_CODE_SEND_ERROR("验证码发送失败，请重新获取","12010"),//发送第6次之后，第7次请求时返回
	RC_REG_ERROR("注册信贷员失败，请重试","1211"),
	
//	登录
    RC_LOGIN_PWD_ERROR("手机号或密码错误，请重新输入","1220"),
    RC_LOGIN_PHONE_STOP("账号被禁用，请联系客服","1221"),
    RC_LOGIN_NO_SESSION("请重新登录","1222"),
    RC_LOGIN_MODIFY_PWD_ERROR("原始密码错误","1223"),
    RC_LOGIN_MODIFY_PWD2_ERROR("新密码不一致","1224"),
    RC_LOGIN_MODIFY_PWD_SYSERR("修改密码失败","1225"),

    
    RC_SMS_LIMIT("抱歉…验证码下发次数已超过今日上限","1005"),
    RC_SMS_ERROR("验证码发送失败，请稍后重试","1006"),
    RC_SMS_NOCODEERROR("验证码不正确","1007"),
    RC_SMS_GETPHONESMS("请重新获取验证码","1008"),
    RC_SMS_NUMSERROR("短信输入错误次数过多，请重新获取验证码","1009"),
    RC_SMS_SMSCODEEERROR("","1010"),
    RC_SMS_PHONEERROR("手机号已更换，请重新获取验证码","1011"),
    RC_ACCOUNT_NOMONEY("API账号账号余额不足","1012"),
    RC_NO_RESOURCE("请求的资源不存在","1013"),
    RC_PARAMETER_NULL("参数为空或格式错误","1014"),
    RC_NO_RESULT("抱歉，没有查询到结果","1015"),
    RC_NO_USER("抱歉，没有该账号","1016"),
    
  
    RC_AF_FAILED("抱歉…验证失败了，您还有x次免费核验机会，是否重新核验？","2000"),
    RC_NOT_SUPPORT ("暂不支持该数据验证","2001"),
    RC_ID_ERR_FORMAT("抱歉…您输入的信息格式不正确","2002"),
   
    RC_SYSTEM_BUSY("系统繁忙，稍后请在我的订单里查看报告","9901"),
    RC_SYSTEM_MAINTAIN("系统维护中，请稍后再试","9902"),
    RC_INTERNAL_TIMEOUT("网络繁忙，请稍后重试","9003"),
    RC_IP_Black("ip被加入黑名单了","9004"),
    
    RC_TIMEOUT("请求超时，请重试","9997"),
    RC_ACCOUNT_TIMEOUT("请重新登陆","9998"),
	
	
    RC_FAILED("系统出错了,请联系客服人员","9999"),

	//文件导入
    DATA_FILE_IMPORT_ERR_TYPE_PARAM("参数为空", "101"),
    DATA_FILE_IMPORT_ERR_TYPE_FILE_CODE("文件编码不一致", "102"),
    DATA_FILE_IMPORT_ERR_TYPE_SPLIT_CODE("文件分隔符为中文格式", "103"),
    DATA_FILE_IMPORT_ERR_TYPE_SPLIT_CODE_NOT_MATCH("填写的文件分隔符与文件中的不相符", "103"),
    DATA_FILE_IMPORT_ERR_TYPE_COLUMN("文件中列名称不符合规范", "104"),
    DATA_FILE_IMPORT_ERR_TYPE_ROW_FIRST_NULL("文件中某一行最后一个值为空", "105"),
    //106有三种情况：标志位2 1-index列/target列中有空值，2-index列中有相同的值，3-target列中有不是0和1的值
    DATA_FILE_IMPORT_ERR_TYPE_NOT_NUMBER("indexName或者targetName为空，或者校验未通过","106"),
    DATA_FILE_IMPORT_ERR_TYPE_INDEX_NOT_IN("indexName不是文件中的列名", "107"),
    DATA_FILE_IMPORT_ERR_TYPE_TARGET_NOT_IN("targetName不是文件中的列名", "108");


	
    private String name ;
    private String code ;
     
    private EnumSysManage( String name , String code ){
        this.name = name ;
        this.code = code ;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
     * 通过code 得到namemsg
     * @param code
     * @return
     */
    public static String getMsg(String code)
    {
        for (EnumSysManage c : EnumSysManage.values())
        {
            if (c.getCode().equals(code))
            {
                return c.name;
            }
        }
        return null;
    }
}
